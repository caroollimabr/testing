package br.com.carol.restassured;

import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXParseException;

import static io.restassured.RestAssured.given;

public class SchemaTest {

    @BeforeClass
    public static void setup(){
        RestAssured.baseURI = "http://restapi.wcaquino.me";
    }

    @Test
    // gerador schema Xml (copie e cole um exemplo do que vc precisa e copie o schema, colocando em um arquivo em resources): https://www.freeformatter.com/xsd-generator.html#ad-output
    public void validaSchemaXml() {
        given().log().all()
                .when()
                    .get("/usersXML")
                .then()
                    .log().all()
                    .statusCode(200)
                    .body(RestAssuredMatchers.matchesXsdInClasspath("users.xsd"));
    }

    @Test(expected= SAXParseException.class)
    public void naoValidaSchemaXmlInvalido() {
        given().log().all()
                .when()
                    .get("/invalidUsersXML")
                .then()
                    .log().all()
                    .statusCode(200)
                    .body(RestAssuredMatchers.matchesXsdInClasspath("users.xsd"));
    }

    @Test
    // gerador schema Json (copie e cole um exemplo do que vc precisa e copie o schema, colocando em um arquivo em resources): https://jsonschema.net/home
    public void validaSchemaJson() {
        given().log().all()
                .when()
                    .get("/users")
                .then()
                    .log().all()
                    .statusCode(200)
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("users.json"));
    }


}
