package br.com.carol.restassured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.path.xml.NodeImpl;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserXmlTest {

    public static RequestSpecification reqSpec;
    public static ResponseSpecification resSpec;

    @BeforeClass
    public static void setup(){
        RestAssured.baseURI = "http://restapi.wcaquino.me";
        // RestAssured.port = 80;
        // RestAssured.basePath = "/v2"; // parte da url

        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.log(LogDetail.ALL);
        reqSpec = reqBuilder.build();

        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectStatusCode(200);
        resSpec = resBuilder.build();

        RestAssured.requestSpecification = reqSpec;
        RestAssured.responseSpecification = resSpec;
    }

    @Test
    public void verificaUsuario(){

        given()
                .when()
                    .get("/usersXML/3")
                .then()
//                    .statusCode(200)
                    .body("user.name", is("Ana Julia"))
                    .body("user.@id", is("3"))
                    .body("user.filhos.name.size()", is(2))
                    .body("user.filhos.name[0]", is("Zezinho"))
                    .body("user.filhos.name[1]", is("Luizinho"))
                    .body("user.filhos.name", hasItem("Zezinho"))
                    .body("user.filhos.name", hasItems("Luizinho", "Zezinho"));

    }

    @Test
    public void verificaUsuarioMaisSimples(){
        given()
                .when()
                    .get("/usersXML/3")
                .then()
//                    .statusCode(200)

                    .rootPath("user")
                    .body("name", is("Ana Julia"))
                    .body("@id", is("3"))

                    .rootPath("user.filhos")
                    .body("name.size()", is(2))

                    .detachRootPath("filhos") // retira filhos do rootPath, a título de prática
                    .body("filhos.name[0]", is("Zezinho"))
                    .body("filhos.name[1]", is("Luizinho"))

                    .appendRootPath("filhos") // anexa filhos no rootPath - já existe um rootPath
                    .body("name", hasItem("Zezinho"))
                    .body("name", hasItems("Luizinho", "Zezinho"));

    }

    @Test
    public void verificaListaUsuarios(){
        given()
                .when()
                    .get("/usersXML")
                .then()
//                    .statusCode(200)

                    .rootPath("users.user")
                    .body(".size()", is(3)) // quantidade de itens na lista
                    .body("findAll{it.age.toInteger() <= 25}.size()", is(2))  // quantidade de idades menor q 25
                    .body("@id", hasItems("1", "2", "3"))
                    .body("find{it.age == 25}.name", is("Maria Joaquina"))
                    .body("salary.find{it != null}.toDouble()", is(1234.5678d))  // encontra primeiro salario nao nulo
                    .body("age.collect{it.toInteger() * 2}", hasItems(40, 50, 60))  // verifica idades mult por 2
                    .body("name.findAll{it.toString().startsWith('Maria')}.collect{it.toString().toUpperCase()}",
                            is("MARIA JOAQUINA"));
    }

    @Test
    public void verificaListaUsuarioscomJava(){
        ArrayList<NodeImpl> nomes =
                given()
                .when()
                    .get("/usersXML")
                .then()
//                    .statusCode(200)
                    .extract().path("users.user.name.findAll{it.toString().contains('n')}");

        Assert.assertEquals(2, nomes.size());
        Assert.assertEquals("Maria Joaquina".toUpperCase(), nomes.get(0).toString().toUpperCase());
        Assert.assertTrue("ANA JULIA".equalsIgnoreCase(nomes.get(1).toString()));

    }

    @Test
    // https://www.red-gate.com/simple-talk/wp-content/uploads/imported/1269-Locators_table_1_0_2.pdf?file=4937
    public void verificaListaUsuariosComXPath(){  // dá para procurar XPath no XML <>
        given()
                .when()
                    .get("/usersXML")
                .then()
//                    .statusCode(200)
                    .body(hasXPath("count(/users/user)", is("3")))
                    .body(hasXPath("/users/user[@id = '1']"))
                    .body(hasXPath("//user[@id = '2']"))
                    .body(hasXPath("//name[text() = 'Luizinho']/../../name", is("Ana Julia")))
                    .body(hasXPath("//name[text() = 'Ana Julia']/following-sibling::filhos",
                            allOf(containsString("Zezinho"), containsString("Luizinho"))))
                    .body(hasXPath("/users/user/name", is("João da Silva")))
                    .body(hasXPath("//name", is("João da Silva")))
                    .body(hasXPath("/users/user[2]/name", is("Maria Joaquina")))
                    .body(hasXPath("/users/user[last()]/name", is("Ana Julia")))
                    .body(hasXPath("count(/users/user/name[contains(., 'n')])", is("2")))
                    .body(hasXPath("//user[age < 24]/name", is("Ana Julia")))
                    .body(hasXPath("//user[age > 20 and age < 30]/name", is("Maria Joaquina")))
                    .body(hasXPath("//user[age > 20][age < 30]/name", is("Maria Joaquina")));
    }



}
