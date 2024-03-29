package br.com.carol.restassured;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;

public class AuthTest {

    @Test
    public void retornaPrimeiroUsuarioSwApi() {
        given().log().all()
                .when()
                    .get("https://swapi.dev/api/people/1")
                .then()
                    .log().all()
                    .statusCode(200)
                    .body("name", is("Luke Skywalker"));
    }

    @Test
    public void retornaClimaOpenWeather() {
        given()
                .log().all()
                .queryParam("q", "Fortaleza, BR")
                .queryParam("appid", "ec3fc978be72ea22febf77c09265e358")
                .queryParam("units", "metric")
                    .when()
                        .get("http://api.openweathermap.org/data/2.5/weather")
                    .then()
                        .log().all()
                        .statusCode(200)
                        .body("name", is("Fortaleza"))
                        .body("coord.lon", is(-38.52f))
                        .body("main.temp", greaterThan(25f));
    }

    @Test
    public void naoAcessaSemSenhaAutenticacaoBasica() {
        given().log().all()
            .when()
                .get("https://restapi.wcaquino.me/basicauth")
            .then()
                .log().all()
                .statusCode(401);
    }

    @Test
    public void acessaComAutenticacaoBasica() {
        given().log().all()
                .when()
                    .get("https://admin:senha@restapi.wcaquino.me/basicauth")
                .then()
                    .log().all()
                    .statusCode(200)
                    .body("status", is("logado"));
    }

    @Test
    public void acessaComAutenticacaoBasica2() {
        given()
                .log().all()
                .auth().basic("admin", "senha")
                    .when()
                        .get("https://restapi.wcaquino.me/basicauth")
                    .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", is("logado"));
    }

    @Test
    public void acessaComAutenticacaoBasica3() {
        given()
                .log().all()
                .auth().preemptive().basic("admin", "senha")
                    .when()
                        .get("https://restapi.wcaquino.me/basicauth2")
                    .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", is("logado"));
    }

    @Test
    public void acessaComTokenJWT() {
        Map<String, String> login = new HashMap<String, String>();
        login.put("email", "carol@carol");
        login.put("senha", "123456");

        // loga na api e recebe token
        String token = given()
                .log().all()
                .body(login)
                .contentType(ContentType.JSON)
                    .when()
                        .post("http://barrigarest.wcaquino.me/signin")
                    .then()
                        .log().all()
                        .statusCode(200)
                        .extract().path("token");

        // obtém as contas
        given()
                .log().all()
                .header("Authorization", "JWT " + token)
                    .when()
                        .get("http://barrigarest.wcaquino.me/contas")
                    .then()
                        .log().all()
                        .statusCode(200)
                        .body("nome", hasItem("Conta de teste"));
    }

    @Test
    public void acessaAplicacaoWeb() {
        String cookie = given()
                .log().all()
                .formParam("email", "carol@carol")
                .formParam("senha", "123456")
                .contentType(ContentType.URLENC.withCharset("UTF-8"))
                    .when()
                        .post("http://seubarriga.wcaquino.me/logar")
                    .then()
                        .log().all()
                        .statusCode(200)
                        .extract().header("set-cookie");

        cookie = cookie.split("=")[1].split(";")[0];
        System.out.println(cookie);

        // obtém conta
        String body = given()
                .log().all()
                .cookie("connect.sid", cookie)
                    .when()
                        .get("http://seubarriga.wcaquino.me/contas")
                    .then()
                        .log().all()
                        .statusCode(200)
                        .body("html.body.table.tbody.tr[0].td[0]", is("Conta de teste"))
                        .extract().body().asString();

        System.out.println("---------------");
        XmlPath xmlPath = new XmlPath(XmlPath.CompatibilityMode.HTML, body);
        System.out.println(xmlPath.getString("html.body.table.tbody.tr[0].td[0]"));
    }



}
