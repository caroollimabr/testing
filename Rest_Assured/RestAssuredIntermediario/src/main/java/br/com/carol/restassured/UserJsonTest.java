package br.com.carol.restassured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserJsonTest {

    @Test
    public void retornaPrimeiroUsuario(){
        given()
                .when()
                    .get("http://restapi.wcaquino.me/users/1")
                .then()
                    .statusCode(200)
                    .body("id", is(1))
                    .body("name", containsString("Silva"))
                    .body("age", greaterThan(18));
    }

    @Test
    public void retornaPrimeiroUsuario2(){
        Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/users/1");

        // com path
        Assert.assertEquals(new Integer(1), response.path("id"));
        Assert.assertEquals(new Integer(1), response.path("%s", "id"));

        // com jsonpath
        JsonPath jpath = new JsonPath(response.asString());
        Assert.assertEquals(1, jpath.getInt("id"));

        // com from
        int id = JsonPath.from(response.asString()).getInt("id");
        Assert.assertEquals(1, id);
    }

    @Test
    public void retornaSegundoUsuarioObj(){
        given()
                .when()
                    .get("http://restapi.wcaquino.me/users/2")
                .then()
                    .statusCode(200)
                    .body("id", is(2))
                    .body("name", containsString("Joaquina"))
                    .body("endereco.rua", is("Rua dos bobos"));
    }

    @Test
    public void retornaTerceiroUsuarioArray(){
        given()
                .when()
                    .get("http://restapi.wcaquino.me/users/3")
                .then()
                    .statusCode(200)
                    .body("name", containsString("Ana"))
                    .body("filhos", hasSize(2)) // há 2 itens dentro do array
                    .body("filhos[0].name", is("Zezinho"))
                    .body("filhos[1].name", is("Luizinho"))
                    .body("filhos.name", hasItem("Luizinho"))
                    .body("filhos.name", hasItems("Luizinho", "Zezinho"));
    }

    @Test
    public void retornaErroUsuarioInexistente(){
        given()
                .when()
                    .get("http://restapi.wcaquino.me/users/10")
                .then()
                    .statusCode(404)
                    .body("error", is("Usuário inexistente"));
    }

    @Test
    public void retornaListaUsuarios() {
        given()
                .when()
                .get("http://restapi.wcaquino.me/users")
                .then()
                .statusCode(200)
                .body("$", hasSize(3)) // $ procura na raiz, verifica quantos itens o array possui
                .body("name", hasItems("João da Silva", "Maria Joaquina", "Ana Júlia"))
                .body("age[1]", is(25))
                .body("filhos.name", hasItem(Arrays.asList("Zezinho", "Luizinho")))
                .body("salary", contains(1234.5678f, 2500, null))  // ATENÇÃO COM O FLOAT
                .body("age.findAll{it <= 25}.size()", is(2)) // passa por todas as idades menores ou iguais a 25 e verifica se há apenas duas
                .body("age.findAll{it <= 25 && it > 20}.size()", is(1))
                .body("age.collect{it * 2}", hasItems(60, 50, 40))
                .body("findAll{it.age <= 25 && it.age > 20}.name", hasItem("Maria Joaquina"))
                .body("findAll{it.age <= 25}[0].name", is("Maria Joaquina"))
                .body("findAll{it.age <= 25}[-1].name", is("Ana Júlia"))  // -1 verifica o último item, -2 o penúltimo
                .body("findAll{it.name.contains('n')}.name", hasItems("Maria Joaquina", "Ana Júlia"))
                .body("findAll{it.name.length() > 10}.name", hasItems("João da Silva", "Maria Joaquina"))
                .body("name.collect{it.toUpperCase()}", hasItem("MARIA JOAQUINA")) // transforma tudo em upper case para verificar que algo existe
                .body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}.toArray()",
                    allOf(arrayContaining("MARIA JOAQUINA"), arrayWithSize(1)))  // deixar tudo compatível em array para fazer a comparação funcionar
                .body("id.max()", is(3))  // número máximo de ids dentro da lista
                .body("salary.min()", is(1234.5678f))  // menor salário da lista
                .body("salary.findAll{it != null}.sum()", is(closeTo(3734.5678f, 0.001))); // verifica soma  de salário com margem de erro de 0.001
    }

    @Test
    public void retornaListaUsuariosJsonPathComJava() {
        ArrayList<String> names =
                given().
                        when()
                            .get("http://restapi.wcaquino.me/users")
                        .then()
                            .statusCode(200)
                            .extract().path("name.findAll{it.startsWith('Maria')}");
        Assert.assertEquals(1, names.size());
        Assert.assertTrue(names.get(0).equalsIgnoreCase("maria joaquina")); //equals ignore case ignora upper case
        Assert.assertEquals(names.get(0).toUpperCase(), "maria joaquina".toUpperCase());
    }


}
