package br.com.carol.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class EnvioDadosTest {

    @BeforeClass
    public static void setup(){

        RestAssured.baseURI = "http://restapi.wcaquino.me";
        RestAssured.basePath = "/v2";
    }

    /* Podemos realizar a requisição:
     * 1. na própria URL (path);
     * 2. com query/queryParameters;
     * 3. pelo header;
     * 4. pelo body. */

    @Test
    public void retornaListaUsuariosViaQuery() {
        given().log().all()
                .when()
                    .get("/users?format=json")  // dessa forma
                .then()
                    .log().all()
                    .statusCode(200)
                    .contentType(ContentType.JSON);

    }

    @Test
    public void retornaListaUsuariosViaQueryParameters() {
        given()
                .log().all()
                .queryParam("format", "xml")  // dessa forma
                .queryParam("outra", "coisa")  // dessa forma
                    .when()
                        .get("/users")
                    .then()
                        .log().all()
                        .statusCode(200)
                        .contentType(ContentType.XML)
                        .contentType(containsString("utf-8"));

    }

    @Test
    public void retornaListaUsuariosViaHeader() {
        given()
                .log().all()
                .accept(ContentType.XML)  // dessa forma
                    .when()
                        .get("/users")
                    .then()
                        .log().all()
                        .statusCode(200)
                        .contentType(ContentType.XML)
                        .contentType(containsString("utf-8"));

    }

    @Test
    public void retornaListaUsuariosComHtml() {
        given().log().all()
                .when()
                    .get("/users")
                .then()
                    .log().all()
                    .statusCode(200)
                    .contentType(ContentType.HTML)
                    .body("html.body.div.table.tbody.tr.size()", is(3))
                    .body("html.body.div.table.tbody.tr[1].td[2]", is("25"))
                    .appendRootPath("html.body.div.table.tbody") // deixa já no rootPath para as próximas verificações
                    .body("tr.find{it.toString().startsWith('2')}.td[1]", is("Maria Joaquina"));

    }

    @Test
    public void retornaListaUsuariosComXPathEmHtml() {
        given().log().all()
                .when()
                    .get("/users?format=clean") // coloca assim no get
                .then()
                    .log().all()
                    .statusCode(200)
                    .contentType(ContentType.HTML)
                    .body(hasXPath("count(//table/tr)", is("4")))
                    .body(hasXPath("//td[text() = '2']/../td[2]", is("Maria Joaquina")));
    }


}
