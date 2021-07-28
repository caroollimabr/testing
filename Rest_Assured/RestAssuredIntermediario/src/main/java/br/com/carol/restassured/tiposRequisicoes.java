package br.com.carol.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.nullValue;

public class tiposRequisicoes {

    public static RequestSpecification reqSpec;
    public static ResponseSpecification resSpec;

    @BeforeClass
    public static void setup(){
        RestAssured.baseURI = "http://restapi.wcaquino.me";
    }

    @Test
    public void incluiUsuario(){
        given()
                .log().all() // loga dados da chamada
                .contentType("application/json")
                .body("{\"name\": \"Jose\", \"age\": 50}")
                    .when()
                        .post("/users")
                    .then()
                        .log().all()
                        .statusCode(201)
                        .body("id", is(notNullValue()))
                        .body("name", is("Jose"))
                        .body("age", is(50));
    }

    @Test
    public void incluiUsuarioXml(){
        given()
                .log().all() // loga dados da chamada
                .contentType(ContentType.XML)
                .body("<user><name>Jose</name><age>50</age></user>")
                    .when()
                        .post("/usersXML")
                    .then()
                        .log().all()
                        .statusCode(201)
                        .rootPath("user")
                        .body("@id", is(notNullValue()))
                        .body("name", is("Jose"))
                        .body("age", is("50"));
    }

    @Test
    public void incluiUsuarioXmlComObjeto(){
        User user = new User("Usuario XML", 55);

        given()
                .log().all() // loga dados da chamada
                .contentType(ContentType.XML)
                .body(user)
                    .when()
                        .post("/usersXML")
                    .then()
                        .log().all()
                        .statusCode(201)
                        .rootPath("user")
                        .body("@id", is(notNullValue()))
                        .body("name", is("Usuario XML"))
                        .body("age", is("55"));
    }

    @Test
    public void desserializaXmlAoIncluirUsuario(){
        User user = new User("Usuario XML", 55);

        User usuarioInseridoXml = given()
                .log().all() // loga dados da chamada
                .contentType(ContentType.XML)
                .body(user)
                    .when()
                        .post("/usersXML")
                    .then()
                        .log().all()
                        .statusCode(201)
                        .extract().body().as(User.class);

        Assert.assertThat(usuarioInseridoXml.getId(), notNullValue());
        Assert.assertThat(usuarioInseridoXml.getName(), is("Usuario XML"));
        Assert.assertThat(usuarioInseridoXml.getAge(), is(55));
        Assert.assertThat(usuarioInseridoXml.getSalary(), nullValue());
    }

    @Test
    public void incluiUsuarioComMap(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "Usuário via Map");
        params.put("age", 44);

        given()
                .log().all() // loga dados da chamada
                .contentType("application/json")
                .body(params)
                    .when()
                        .post("/users")
                    .then()
                        .log().all()
                        .statusCode(201)
                        .body("id", is(notNullValue()))
                        .body("name", is("Usuário via Map"))
                        .body("age", is(44));
    }

    @Test
    public void incluiUsuarioComObjeto(){
        User user = new User("Usuário via Objeto", 37);

        given()
                .log().all() // loga dados da chamada
                .contentType("application/json")
                .body(user)
                    .when()
                        .post("/users")
                    .then()
                        .log().all()
                        .statusCode(201)
                        .body("id", is(notNullValue()))
                        .body("name", is("Usuário via Objeto"))
                        .body("age", is(37));
    }

    @Test
    public void desserializaObjetoAoIncluirUsuario(){
        User user = new User("Usuário desserializado", 37);

        User usuarioInserido = given()
                .log().all() // loga dados da chamada
                .contentType("application/json")
                .body(user)
                    .when()
                        .post("/users")
                    .then()
                        .log().all()
                        .statusCode(201)
                        .extract().body().as(User.class);

        Assert.assertThat(usuarioInserido.getId(), notNullValue());
        Assert.assertEquals("Usuário desserializado", usuarioInserido.getName());
        Assert.assertThat(usuarioInserido.getAge(), is(37));
    }

    @Test
    public void naoIncluiUsuarioSemNome(){
        given()
                .log().all() // loga dados da chamada
                .contentType("application/json")
                .body("{\"age\": 50}")
                    .when()
                        .post("/users")
                    .then()
                        .log().all()
                        .statusCode(400)
                        .body("id", is(nullValue()))
                        .body("error", is("Name é um atributo obrigatório"));
    }

    @Test
    public void editaUsuario(){
        given()
                .log().all() // loga dados da chamada
                .contentType("application/json")
                .body("{\"name\": \"Usuário editado\", \"age\": 99}")
                    .when()
                        .put("/users/1")
                    .then()
                        .log().all()
                        .statusCode(200)
                        .body("id", is(1))
                        .body("name", is("Usuário editado"))
                        .body("age", is (99))
                        .body("salary", is(1234.5678f));
    }

    @Test
    public void deletaUsuario(){
        given()
                .log().all() // loga dados da chamada
                    .when()
                        .delete("/users/1")
                    .then()
                        .log().all()
                        .statusCode(204);
    }

    @Test
    public void naoDeletaUsuarioInexistente(){
        given()
                .log().all() // loga dados da chamada
                    .when()
                        .delete("/users/99")
                    .then()
                        .log().all()
                        .statusCode(400)
                        .body("error", is("Registro inexistente"));
    }

    @Test
    public void customizaUrl(){
        given()
                .log().all() // loga dados da chamada
                .contentType("application/json")
                .body("{\"name\": \"Usuário editado\", \"age\": 99}")
                    .when()
                        .put("/{colecao}/{id}", "users", "1")
                    .then()
                        .log().all()
                        .statusCode(200)
                        .body("id", is(1))
                        .body("name", is("Usuário editado"))
                        .body("age", is (99))
                        .body("salary", is(1234.5678f));
    }

    @Test
    public void customizaUrl2(){
        given()
                .log().all() // loga dados da chamada
                .contentType("application/json")
                .body("{\"name\": \"Usuário editado\", \"age\": 99}")
                .pathParam("colecao", "users")
                .pathParam("id", 1)
                    .when()
                        .put("/{colecao}/{id}", "users", "1")
                    .then()
                        .log().all()
                        .statusCode(200)
                        .body("id", is(1))
                        .body("name", is("Usuário editado"))
                        .body("age", is (99))
                        .body("salary", is(1234.5678f));
    }
}
