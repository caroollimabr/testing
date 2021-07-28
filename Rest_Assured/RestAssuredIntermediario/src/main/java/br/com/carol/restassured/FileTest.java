package br.com.carol.restassured;

import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;

public class FileTest {

    @BeforeClass
    public static void setup(){
        RestAssured.baseURI = "http://restapi.wcaquino.me";
    }

    @Test
    public void erroSemArquivoUpload() {
        given().log().all()
                .when()
                    .post("/upload")
                .then()
                    .log().all()
                    .statusCode(404) // deveria ser 400
                    .body("error", is("Arquivo não enviado"));

    }

    @Test
    public void fazUploadArquivo() {
        given()
                .log().all()
                .multiPart("arquivo", new File("src/main/resources/users.txt")) // importando arquivo para upload
                    .when()
                        .post("/upload")
                    .then()
                        .log().all()
                        .statusCode(200)
                        .body("name", is("users.txt"));

    }

    @Test
    public void naofazUploadArquivoGrande() {
        given()
                .log().all()
                .multiPart("arquivo", new File("src/main/resources/apostila-java-oo.pdf")) // importando arquivo MUITO GRANDE para upload
                    .when()
                        .post("/upload")
                    .then()
                        .log().all()
                        .time(lessThan(10000L)) // tempo limite para resposta (10000 milissegundos ou 10 seg)
                        .statusCode(413);

    }

    @Test
    public void baixaArquivo() throws IOException {
        byte[] imagemDownload = given()
                .log().all()
                    .when()
                        .get("/download")
                    .then()
//                        .log().all()
                        .statusCode(200)
                        .extract().asByteArray();

        File imagem = new File("src/main/resources/file.jpg");  // não precisa ter o arquivo: essa é a forma como o arquivo será baixado
        OutputStream out = new FileOutputStream(imagem);
        out.write(imagemDownload);
        out.close();

        Assert.assertThat(imagem.length(), lessThan(100000L));

    }



}
