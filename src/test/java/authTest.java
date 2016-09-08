import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by kate on 08/09/16.
 */
public class authTest {
    private static String uri = "http://ui.test.service.co";


    @Test
    public void shouldDropService() {

        for (int i = 0; i < 100; i++) {
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
}
