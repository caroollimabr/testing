package br.com.testes.testeLojaDeGames;

import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class TesteCategoria extends MassaDeDadosCategoria{

    @BeforeClass
    public static void urlBase(){
        baseURI = "http://localhost:8080/categoria/";
    }

    //falta completar objetos
    @Test
    public void getAllCategorias(){
        Response response = given()
                        .when().get()
                        .then()
                            .body("id[0]",is(1))
                            .body("categoria[0]", containsString("tabuleiro"))
                            .body("listaProdutos[0].id[0]",is(6))
                            .body("listaProdutos[0].nomeProduto[0]",containsString("Detetive"))
                            .body("id[1]",is(2))
                            .body("categoria[1]", containsString("RPG"))
                            .body("id[2]",is(4))
                            .body("categoria[2]", containsString("card game"))
                            .statusCode(200).contentType(ContentType.JSON).extract().response();

    }

    @Test
    public void getIdCategoria(){

        Response response = given()
                .contentType("application/json")
                .pathParam("id", 1)
                .when().get("{id}")
                .then()
                    .body("id",is(1))
                    .body("categoria", containsString("tabuleiro"))
                    .statusCode(200).contentType(ContentType.JSON).extract().response();

    }

    @Test
    public void getNomeCategoria(){

        Response response = given()
                .contentType("application/json")
                .when().get(baseURI.concat(urlNomeCategoria)) //outra opção - não precisa de @BeforeClass
                .then()
                    .body("id[0]",is(2))
                    .body("categoria[0]", equalTo("RPG"))
                    .statusCode(200).contentType(ContentType.JSON).extract().response();
    }

    @Test
    public void postCategoria(){
        Response response = given().contentType("application/json").body(corpoCategoria)
                .when().post();
        response.then().body("categoria", equalTo("acao"))
                .statusCode(201);
        System.out.println("Retorno => " + response.body().asString()); //verificar a resposta real para fazer os testes
    }

    @Test
    public void putCategoria(){
        Response response = given().contentType("application/json").body(corpoCategoriaAlteracao)
                .when().put();
        response.then().body("categoria", equalTo("esportes"))
                .statusCode(200);

    }

    @Test
    public void deleteCategoria(){
        Response response = given()
                .contentType("application/json")
                .pathParam("id", 3)
                .when().delete(urlCategoriaDelete);

        response.then()
                .statusCode(200);
    }

}
