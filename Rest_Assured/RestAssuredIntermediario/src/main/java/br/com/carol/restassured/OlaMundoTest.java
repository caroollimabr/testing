package br.com.carol.restassured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class OlaMundoTest {

    @Test
    public void testOlaMundo() {
        Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me:80/ola");
        Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
        Assert.assertTrue(response.statusCode() == 200);
        Assert.assertTrue("O status deveria ser 200", response.statusCode() == 200);
        Assert.assertEquals(200, response.statusCode());

        ValidatableResponse validacao = response.then();
        validacao.statusCode(200);
    }

    @Test
    public void validandoBody() {
        given()
                .when()
                    .get("http://restapi.wcaquino.me:80/ola")
                .then()
                .statusCode(200)
                .body(is("Ola Mundo!"))
                .body(containsString("Mundo"))
                .body(is(not(nullValue())));

    }

    @Test
    // http://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/Matchers.html
    public void matchersComHamcrest() {
        Assert.assertThat("Maria", Matchers.is("Maria"));
        Assert.assertThat(128, Matchers.is(128));
        Assert.assertThat(128, Matchers.isA(Integer.class));
        Assert.assertThat(128d, Matchers.isA(Double.class));
        Assert.assertThat(128d, Matchers.greaterThan(120d));
        Assert.assertThat(128d, Matchers.lessThan(130d));

        List<Integer> impares = Arrays.asList(1, 3, 5, 7, 9);
        Assert.assertThat(impares, hasSize(5));
        Assert.assertThat(impares, contains(1, 3, 5, 7, 9));  // todo contains precisa ter todos os elementos da lista
        Assert.assertThat(impares, containsInAnyOrder(3, 1, 9, 5, 7));
        Assert.assertThat(impares, hasItem(1));
        Assert.assertThat(impares, hasItems(1, 5));

        Assert.assertThat("Maria", is(not("Mariah")));
        Assert.assertThat("Maria", not("Mariah"));
        Assert.assertThat("Maria", anyOf(is("Maria"), is ("maria"), is("MARIA")));
        Assert.assertThat("Maria", allOf(startsWith("Ma"), endsWith("ia"), containsString("r")));
    }
}
