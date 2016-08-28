
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


/**
 * Created by Ivan on 28/08/16.
 */
public class testRest {

    @Test
    public void shouldBe200() {
        final String uri = "http://ui.test.service.co";
        final RequestSpecification basicAuth = given().auth().preemptive().basic("User", "PWD");
        final Response response = basicAuth.accept(ContentType.JSON).get(uri);
        org.junit.Assert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
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
                .baseUri("http://ui.test.service.co")
                .contentType("application/json")
                .body(myJson).log().all()
                .when().post("/api/v1/media")
                .then().log().all().statusCode(200)
                .body("error_code", equalTo(0))
                .body("status", equalTo("OK"));

    }

}
