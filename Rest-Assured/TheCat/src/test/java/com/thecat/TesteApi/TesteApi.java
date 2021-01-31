package com.thecat.TesteApi;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;


public class TesteApi {

    String vote_id;
    String favourite_id;

    @Test
    public void cadastro(){
        String url = "https://api.thecatapi.com/v1/user/passwordlesssignup";
        // barras antes das aspas para identificar corretamente as diferentes Strings
        String corpo = "{\"email\": \"carool.limabr@gmail.com\", \"appDescription\": \"tests with rest assured\"}";

        Response response = given()
                .contentType("application/json").body(corpo)
                .when().post(url);

        response.then().body("message", containsString("SUCCESS")).statusCode(200);

        System.out.println("Retorno => " + response.body().asString());
    }

    @Test
    public void votacao(){
        String url = "https://api.thecatapi.com/v1/votes/";

        Response response = given()
                .contentType("application/json")
                .body("{\"image_id\": \"ZdhQh9wc9\", \"value\": \"true\", \"sub_id\": \"demo-1fa94e\"}")
                .when().post(url);

        response.then().body("message", containsString("SUCCESS")).statusCode(200);

        System.out.println("Retorno VOTACAO => " + response.body().asString());
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
        String url = "https://api.thecatapi.com/v1/votes/{vote_id}";

        Response response = given()
                .contentType("application/json")
                .header("x-api-key","cf399608-a7fc-4977-976b-85e9ac923ee0")
                .pathParam("vote_id", vote_id)
                .when().delete(url);

        System.out.println("Retorno DELETA VOTO => " + response.body().asString());

        response.then()
                .statusCode(400)
                .body("message", containsString("INVALID_ACCOUNT"), "level",containsString("info"));

    }

    private void favorita() {

        Response response = given()
                .contentType("application/json")
                .header("x-api-key","cf399608-a7fc-4977-976b-85e9ac923ee0")
                .body("{\"image_id\": \"2uo\",\"sub_id\": \"your-user-1234\"}")
                .when().post("https://api.thecatapi.com/v1/favourites");

        String id = response.jsonPath().getString("id");
        favourite_id = id;

        System.out.println("Retorno FAVORITA => " + response.body().asString());

        response.then().body("message", containsString("SUCCESS")).statusCode(200);

    }

    private void deletaFavorito() {
        String url = "https://api.thecatapi.com/v1/favourites/{favourite_id}";

        Response response = given()
                .contentType("application/json")
                .header("x-api-key","cf399608-a7fc-4977-976b-85e9ac923ee0")
                .pathParam("favourite_id", favourite_id)
                .when().delete(url);

        System.out.println("Retorno DESFAVORITA => " + response.body().asString());

        response.then().body("message", containsString("SUCCESS")).statusCode(200);

    }


}
