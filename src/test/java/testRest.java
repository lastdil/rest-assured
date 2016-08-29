
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;


import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;


/**
 * Created by Ivan on 28/08/16.
 */
public class testRest {
    static String uri = "http://ui.test.service.co";

//    @Test
//    public void shouldBe200() {
//        final String uri = "http://ui.test.service.co";
//        final RequestSpecification basicAuth = given().auth().preemptive().basic("User", "PWD");
//        final Response response = basicAuth.accept(ContentType.JSON).get(uri);
//        org.junit.Assert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
//    }

    @Test
    public void shouldUploadNewFile() {
        ValidatableResponse response = given()
                .multiPart("file", new File("C:\\1\\RA\\rest-assured\\src\\test\\data\\gomer.png"), "image/png")
                .baseUri(uri)
                .log().all()
                .when()
                .post("/file/upload")
                .then()
                .body("status", equalTo("OK"))
                .log().all();
        String filename = response.extract().path("filename");
        System.out.println(filename);
    }

    @Test
    public void NewAlbum() {
        String myJson = "{\n" +
                "  \"request_type\": \"add\",\n" +
                "  \"albums\": [\n" +
                "    {\n" +
                "      \"album_id\": null,\n" +
                "      \"type_id\": 2,\n" +
                "      \"title\": \"test11\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"token\": \"4A3830EE58E2465EA6232C212B8D0131\"\n" +
                "}\n";

        given()
                .baseUri(uri)
                .contentType("application/json")
                .body(myJson).log().all()
                .expect()
                        .body("error_code", equalTo(1))
                        .body("status", equalTo("-OK"))
                        .when().post("/api/v1/media")
                        .then().log().all().statusCode(200);

    }

}
