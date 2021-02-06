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

    //falta confirmar objeto
    @Test
    public void getAllCategoria(){
        Response response = given()
                        .when().get(baseURI)
                        .then()
                            .body("id[0]",is(1))
                            .body("categoria[0]", containsString("tabuleiro"))
                            .body("id[1]",is(2))
                            .body("categoria[1]", containsString("RPG"))
                            .body("id[2]",is(4))
                            .body("categoria[2]", containsString("card game"))
                            .statusCode(200).contentType(ContentType.JSON).extract().response();

        System.out.println("Retorno => " + response.body().asString());

    }

    @Test
    public void getIdCategoria(){

        Response response = given()
                .contentType("application/json")
                .when().get(baseURI.concat(urlCategoriaId))
                .then()
                    .body("id",is(1))
                    .body("categoria", containsString("tabuleiro"))
                    .statusCode(200).contentType(ContentType.JSON).extract().response();

    }

    @Test
    public void getNomeCategoria(){

        Response response = given()
                .contentType("application/json")
                .when().get(baseURI.concat(urlNomeCategoria))
                .then()
                    .body("id[0]",is(2))
                    .body("categoria[0]", containsString("RPG"))
                    .statusCode(200).contentType(ContentType.JSON).extract().response();
    }

    @Test
    public void post(){
        Response response = given().contentType("application/json").body(corpoCategoria)
                .when().post(baseURI);

        response.then().body(containsString("acao"))
                .statusCode(201);
        System.out.println("Retorno => " + response.body().asString());
    }

    @Test
    public void put(){

    }

    @Test
    public void delete(){
        Response response = given()
                .contentType("application/json")
                .pathParam("id", 3)
                .when().delete(baseURI.concat(urlCategoriaDelete));

        response.then()
                .statusCode(200);
    }

}
