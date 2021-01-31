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

    @Test
    public void getAll(){
        Response response = given()
                        .when().get(baseURI)
                        .then().statusCode(200).contentType(ContentType.JSON).extract().response();

        System.out.println("Retorno => " + response.body().asString());

    }

//    @Test
//    public void getId(){
//
//        Response response = given()
//                .contentType("application/json")
//                .when().get(urlCategoriaId);
//
//        validacao(response);
//    }
//
//    @Test
//    public void getCategoria(){
//
//        Response response = given()
//                .contentType("application/json")
//                .when().get(urlNomeCategoria);
//
//        validacao(response);
//    }

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
                .when().delete(urlCategoriaId);

        response.then()
                .statusCode(200);
    }

//    private void validacao(Response response) {
//        response.then().body("message", containsString("SUCCESS")).statusCode(200);
//        System.out.println("Retorno => " + response.body().asString());
//        System.out.println("------------------------------------------------------------------------------------");
//    }

}
