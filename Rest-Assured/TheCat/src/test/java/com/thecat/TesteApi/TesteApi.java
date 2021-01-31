package com.thecat.TesteApi;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;


public class TesteApi extends MassaDeDados {

    @BeforeClass
    public static void urlBase(){
        baseURI = "https://api.thecatapi.com/v1/";
    }

    @Test
    public void cadastro(){

        Response response = given()
                .contentType("application/json").body(corpoCadastro)
                .when().post(urlCadastro);

        validacao(response);
    }

    @Test
    public void votacao(){

        Response response = given()
                .contentType("application/json")
                .body(corpoVotacao)
                .when().post("votes/");

        validacao(response);

        String id = response.jsonPath().getString("id");
        vote_id = id; // variável global irá receber o valor do id para deletarmos
        System.out.println("ID => " + id);
    }

    @Test
    public void deletaVotacao(){
        votacao();
        deletaVoto();

    }

    @Test
    public void executaFavoritarDeletar(){
        favorita();
        deletaFavorito();
    }


    private void deletaVoto() {

        Response response = given()
                .contentType("application/json")
                .header("x-api-key","cf399608-a7fc-4977-976b-85e9ac923ee0")
                .pathParam("vote_id", vote_id)
                .when().delete("votes/{vote_id}");

        System.out.println("Retorno DELETA VOTO => " + response.body().asString());

        response.then()
                .statusCode(400)
                .body("message", containsString("INVALID_ACCOUNT"), "level",containsString("info"));

    }

    private void favorita() {

        Response response = given()
                .contentType("application/json")
                .header("x-api-key","cf399608-a7fc-4977-976b-85e9ac923ee0")
                .body(corpoFavorita)
                .when().post("favourites");

        String id = response.jsonPath().getString("id");
        favourite_id = id;

        validacao(response);

    }

    private void deletaFavorito() {

        Response response = given()
                .contentType("application/json")
                .header("x-api-key","cf399608-a7fc-4977-976b-85e9ac923ee0")
                .pathParam("favourite_id", favourite_id)
                .when().delete(urlDeletaFavorito);

        validacao(response);
    }

    public void validacao(Response response){
        response.then().body("message", containsString("SUCCESS")).statusCode(200);
        System.out.println("Retorno => " + response.body().asString());
        System.out.println("------------------------------------------------------------------------------------");

    }


}
