package br.com.carol.rest.tests.funcionalidades;

import br.com.carol.rest.utils.BarrigaUtils;
import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SaldosTest {

    @Test
    public void deveCalcularSaldoContas(){

        Integer CONTA_ID = BarrigaUtils.getIdContaPeloNome("Conta para saldo");

        given()
                .when()
                    .get("/saldo")
                .then()
                    .statusCode(200)
                    .body("find{it.conta_id == " + CONTA_ID + "}.saldo", is("534.00"));

    }
}
