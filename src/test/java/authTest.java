import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by ivan on 08/09/16.
 */
public class authTest {
    private static String uri = "http://ui.test.service.co";


    @Test
    public void shouldDropAuthService() {
        int i = 1;
        while (i < 300) {
            System.out.println("Trying Number:" + i);
            String MyJson = "{\n" +
                    "  \"request_type\": \"phone_authorize\",\n" +
                    "  \"phone_number\": \"+12331233333\",\n" +
                    "  \"state\": \"R\"\n" +
                    "}";
            given()
                    .baseUri(uri)
                    .contentType("application/json")
                    .body(MyJson).log().all()
                    .when().post("/api/v1/auth")
                    .then().log().all().statusCode(200);
            i++;

        }
    }
    @Test
    public void shouldDropMediaService(){
        int i = 1;
        while (i < 1000){
            System.out.println("Trying Number:" + i);
            String mediaJson = "{\n" +
                    "  \"request_type\": \"add\",\n" +
                    "  \"albums\": [\n" +
                    "    {\n" +
                    "      \"album_id\": 1,\n" +
                    "      \"type_id\": 2,\n" +
                    "      \"title\": \"test11\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"token\": \"4A3830EE58E2465EA6232C212B8D0131\"\n" +
                    "}";
            given()
                    .baseUri(uri)
                    .contentType("application/json")
                    .body(mediaJson).log().all()
                    .when().post("api/v1/media")
                    .then().log().all().statusCode(200);
            i++;
        }
    }
}
