package br.com.carol.rest.tests;

import br.com.carol.rest.core.BaseTest;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BarrigaTest extends BaseTest {

    private String TOKEN;

    @Before
    public void login(){
        Map<String, String> login = new HashMap<>();
        login.put("email", "carol@carol");
        login.put("senha", "123456");

        TOKEN = given()
                    .body(login)
                .when()
                    .post("/signin")
                .then()
                    .statusCode(200)
                    .extract().path("token");
    }


    @Test
    public void naoDeveAcessarApiSemToken(){
        given()
                .when()
                    .get("/contas")
                .then()
                    .statusCode(401);
    }

    @Test
    public void deveIncluirContaComSucesso(){
        given()
                .header("Authorization", "JWT" + TOKEN) // pode ser "JWT" ou "bearer"
                .body("{ \"nome\": \"conta qualquer\" }")
                    .when()
                        .post("/contas")
                    .then()
                        .statusCode(201)
                        .extract().path("token");
    }

    @Test
    public void deveAlterarContaComSucesso(){
        given()
                .header("Authorization", "JWT" + TOKEN) // pode ser "JWT" ou "bearer"
                .body("{ \"nome\": \"conta alterada\" }")
                    .when()
                        .put("/contas/17585")
                    .then()
                        .log().all()
                        .statusCode(200)
                        .body("nome", is("conta alterada"));
    }

    @Test
    public void naoDeveInserirContaMesmoNome(){
        given()
                .header("Authorization", "JWT" + TOKEN) // pode ser "JWT" ou "bearer"
                .body("{ \"nome\": \"conta alterada\" }")
                    .when()
                        .post("/contas")
                    .then()
                        .log().all()
                        .statusCode(400)
                        .body("error", is("Já existe uma conta com esse nome!"));
        
    }
}
