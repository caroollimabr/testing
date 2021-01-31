package br.com.testes.testeLojaDeGames;

import org.junit.BeforeClass;

import static io.restassured.RestAssured.baseURI;

public class TesteProduto {

    @BeforeClass
    public static void urlBase(){
        baseURI = "http://localhost:8080/produtos/";
    }
}
