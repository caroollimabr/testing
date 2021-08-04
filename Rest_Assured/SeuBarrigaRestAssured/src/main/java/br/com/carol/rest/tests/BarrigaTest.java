package br.com.carol.rest.tests;

import br.com.carol.rest.core.BaseTest;
import br.com.carol.rest.utils.DataUtils;
import io.restassured.RestAssured;
import io.restassured.specification.FilterableRequestSpecification;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // executa os testes tendo em vista o nome em ordem crescente
public class BarrigaTest extends BaseTest {

    //conta que vai sempre ter um nome diferente
    private static String CONTA_NAME = "Conta " + System.nanoTime(); // static para durar ao longo de todos os testes
    private static Integer CONTA_ID;
    private static Integer MOV_ID;

    @Test
    public void t01_deveIncluirContaComSucesso(){
        CONTA_ID = given()
                .body("{ \"nome\": \"" + CONTA_NAME + "\" }")  // conta vai ser sempre diferente e teste vai ter sucesso sempre
                    .when()
                        .post("/contas")
                    .then()
                        .statusCode(201)
                        .extract().path("id");
    }

    @Test
    public void t02_deveAlterarContaComSucesso(){
        given()
                .body("{ \"nome\": \"" + CONTA_NAME + " alterada\" }")
                .pathParam("id", CONTA_ID) // envia id do t02 como pathParam
                    .when()
                        .put("/contas/{id}")
                    .then()
                        .log().all()
                        .statusCode(200)
                        .body("nome", is(CONTA_NAME + " alterada"));
    }

    @Test
    public void t03_naoDeveInserirContaMesmoNome(){
        given()
                .body("{ \"nome\": \"" + CONTA_NAME + " alterada\" }")
                    .when()
                        .post("/contas")
                    .then()
                        .log().all()
                        .statusCode(400)
                        .body("error", is("Já existe uma conta com esse nome!"));

    }

    @Test
    public void t04_deveInserirMovimentacaoSucesso(){
        Movimentacao mov = getMovimentacaoValida();

        MOV_ID = given()
                    .body(mov)
                        .when()
                            .post("/transacoes")
                        .then()
                            .statusCode(201)
                            .extract().path("id");

    }

    @Test
    public void t05_deveValidarCamposObrigatoriosMovimentacao(){
        given()
                .body("{}")
                    .when()
                        .post("/transacoes")
                    .then()
                        .statusCode(400)
                        .body("$", hasSize(8))
                        .body("msg", hasItems("Data da Movimentação é obrigatório",
                                "Data do pagamento é obrigatório",
                                "Descrição é obrigatório",
                                "Interessado é obrigatório",
                                "Valor é obrigatório",
                                "Valor deve ser um número",
                                "Conta é obrigatório",
                                "Situação é obrigatório"));

    }

    @Test
    public void t06_naoDeveInserirMovimentacaoComDataFutura(){
        Movimentacao mov = getMovimentacaoValida();
        mov.setData_transacao(DataUtils.getDataDiferencaDias(2)); // depois de amanhã

        given()
                .body(mov)
                    .when()
                        .post("/transacoes")
                    .then()
                        .statusCode(400)
                        .body("$", hasSize(1))
                        .body("msg", hasItem("Data da Movimentação deve ser menor ou igual à data atual"));

    }

    @Test
    public void t07_naoDeveRemoverContaComMovimentacoes(){
        given()
                .pathParam("id", CONTA_ID)
                    .when()
                        .delete("/contas/{id}")
                    .then()
                        .statusCode(500)
                        .body("constraint", is("transacoes_conta_id_foreign"));

    }

    @Test
    public void t08_deveCalcularSaldoContas(){
        given()
                .when()
                    .get("/saldo")
                .then()
                    .statusCode(200)
                    .body("find{it.conta_id == " + CONTA_ID + "}.saldo", is("100.00"));

    }

    @Test
    public void t09_deveRemoverMovimentacao(){
        given()
                .pathParam("id", MOV_ID)
                    .when()
                        .delete("/transacoes/{id}")  // id da transação
                    .then()
                        .statusCode(204);

    }

    @Test
    public void t10_naoDeveAcessarApiSemToken(){

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

    private Movimentacao getMovimentacaoValida(){
        Movimentacao mov = new Movimentacao();
        mov.setConta_id(CONTA_ID);
//        mov.setUsuario_id(usuario_id);
        mov.setDescricao("Descricao da movimentacao");
        mov.setEnvolvido("Envolvido na mov");
        mov.setTipo("REC");
        mov.setData_transacao(DataUtils.getDataDiferencaDias(-1)); // ontem
        mov.setData_pagamento(DataUtils.getDataDiferencaDias(5));
        mov.setValor(100f);
        mov.setStatus(true);
        return mov;
    }
}
