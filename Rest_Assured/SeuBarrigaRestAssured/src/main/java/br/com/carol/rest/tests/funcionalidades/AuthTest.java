package br.com.carol.rest.tests.funcionalidades;

import io.restassured.RestAssured;
import io.restassured.specification.FilterableRequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AuthTest {

    @Test
    public void naoDeveAcessarApiSemToken(){

        // Retira o token
        FilterableRequestSpecification req =
                (FilterableRequestSpecification) RestAssured.requestSpecification;
        req.removeHeader("Authorization");

        given()
                .when()
                    .get("/contas")
                .then()
                    .statusCode(401);
    }
}
