package br.com.testes.testeLojaDeGames;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TesteProduto extends MassaDeDadosProdutos{

    @BeforeClass
    public static void urlBase(){
        baseURI = "http://localhost:8080/produtos/";
    }

    //falta fazer asserts de double

    @Test
    public void getAllProdutos(){
        Response response = given()
                .when().get()
                .then()
                .body("id[0]",is(5))
                .body("nomeProduto[0]", containsString("Zelda"))
                .body("preco[0]", equalTo(450f)) // 450f = 450.0
                .body("qtdEstoque[0]",is(5))
                .body("id[1]",is(6))
                .body("nomeProduto[1]", equalTo("Detetive"))
                .body("preco[1]",equalTo(100.99f))
                .body("qtdEstoque[1]",is(8))
                .body("id[2]",is(8))
                .body("nomeProduto[2]", equalTo("HeartStone"))
                .body("preco[2]",equalTo(65.49f))
                .body("qtdEstoque[2]",is(2))
                .statusCode(200).contentType(ContentType.JSON).extract().response();

    }

    @Test
    public void getIdProduto(){

        Response response = given()
                .contentType("application/json")
                .pathParam("id", 5)
                .when().get("{id}")
                .then()
                .body("id",is(5))
                .body("nomeProduto", containsString("Zelda"))
                .body("preco", equalTo(450f)) // 450f = 450.0
                .body("qtdEstoque",is(5))
                .statusCode(200).contentType(ContentType.JSON).extract().response();

    }

    @Test
    public void getNomeProduto(){
        Response response = given()
                .contentType("application/json")
                .pathParam("nomeProduto", "Zelda")
                .when().get(urlNomeProduto)
                .then()
                .body("id[0]",is(5))
                .body("nomeProduto[0]", containsString("Zelda"))
                .body("preco[0]",equalTo(450.0f))
                .body("qtdEstoque[0]",is(5))
                .body("categoria[0].id", is(2))
                .body("categoria[0].categoria", containsString("RPG"))
                .statusCode(200).contentType(ContentType.JSON).extract().response();
    }

    @Test
    public void postProduto(){
        Response response = given().contentType("application/json").body(corpoProduto)
                .when().post();

        response.then()
                .body("nomeProduto", equalTo("World of Warcraft")) // mais assertivo que containsString
                .body("preco", equalTo(563.9f))
                .body("qtdEstoque", is(3))
                .statusCode(201);
        System.out.println("Retorno => " + response.body().asString());
    }

    @Test
    public void putProduto(){
        Response response = given().contentType("application/json").body(corpoProdutoAlteracao)
                .when().put();

        response.then()
                .body("id", is(17))
                .body("nomeProduto",containsString("World of Warcraft II"))
                .body("preco", equalTo(663.9f))
                .body("preco", notNullValue()) // não é nulo
                .body("qtdEstoque", is(4))
                .body("categoria", nullValue()) // é nulo
                .statusCode(200);
    }

    @Test
    public void deleteProduto(){
        Response response = given()
                .contentType("application/json")
                .pathParam("id", 21)
                .when().delete(baseURI.concat("{id}"));  // outra opção - não precisa de @BeforeClass

        response.then()
                .statusCode(200);
    }

}
