package br.com.carol.rest.tests.funcionalidades;

import br.com.carol.rest.utils.BarrigaUtils;
import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ContasTest {

    @Test
    public void deveIncluirContaComSucesso(){
        given()
                .body("{ \"nome\": \" conta inserida \" }")  // conta vai ser sempre diferente e teste vai ter sucesso sempre
                    .when()
                        .post("/contas")
                    .then()
                        .statusCode(201)
                        .extract().path("id");
    }

    @Test
    public void deveAlterarContaComSucesso(){

        Integer CONTA_ID = BarrigaUtils.getIdContaPeloNome("Conta para alterar");

        given()
                .body("{ \"nome\": \"Conta alterada\" }")
                .pathParam("id", CONTA_ID) // envia id do t02 como pathParam
                    .when()
                        .put("/contas/{id}")
                    .then()
                        .log().all()
                        .statusCode(200)
                        .body("nome", is("Conta alterada"));
    }

    @Test
    public void naoDeveInserirContaMesmoNome(){
        given()
                .body("{ \"nome\": \"Conta mesmo nome\" }")
                    .when()
                        .post("/contas")
                    .then()
                        .log().all()
                        .statusCode(400)
                        .body("error", is("Já existe uma conta com esse nome!"));

    }
}
