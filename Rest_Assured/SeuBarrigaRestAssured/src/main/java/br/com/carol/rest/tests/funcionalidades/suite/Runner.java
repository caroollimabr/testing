package br.com.carol.rest.tests.funcionalidades.suite;

import br.com.carol.rest.core.BaseTest;
import br.com.carol.rest.tests.funcionalidades.AuthTest;
import br.com.carol.rest.tests.funcionalidades.ContasTest;
import br.com.carol.rest.tests.funcionalidades.MovimentacoesTest;
import br.com.carol.rest.tests.funcionalidades.SaldosTest;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ContasTest.class,
        MovimentacoesTest.class,
        SaldosTest.class,
        AuthTest.class
})
public class Runner extends BaseTest {

    @BeforeClass
    public static void login(){
        Map<String, String> login = new HashMap<>();
        login.put("email", "carol@carol");
        login.put("senha", "123456");

        String TOKEN = given()
                .body(login)
                    .when()
                        .post("/signin")
                    .then()
                        .statusCode(200)
                        .extract().path("token");

        RestAssured.requestSpecification.header("Authorization", "JWT " + TOKEN); // pode ser "JWT" ou "bearer"

        RestAssured.get("/reset").then().statusCode(200);
    }

}
