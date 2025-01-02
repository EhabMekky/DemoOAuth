package org.example;
import Pojo.Api;
import Pojo.GetCourse;
import Pojo.Mobile;
import Pojo.WebAutomation;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;

public class OAuth {
    public static void main(String[] args) {

        // Given
        // When
        // Then

        String token = given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type", "client_credentials")
                .formParam("scope", "trust")
                .when().log().all()
                .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
        System.out.println(token);


        JsonPath jsonPath = new JsonPath(token);
        String accessToken = jsonPath.getString("access_token");

         GetCourse gc = given().queryParam("access_token", accessToken)
                .when().log().all()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);

        System.out.println(gc.getInstructor());
        System.out.println(gc.getUrl());

        // get sum of all course prices
        int sum = 0;
        for (WebAutomation webAutomation : gc.getCourses().getWebAutomation()) {
            sum += webAutomation.getPrice();
        }
        for (Api api : gc.getCourses().getApi()) {
            sum += api.getPrice();
        }
        for (Mobile mobile : gc.getCourses().getMobile()) {
            sum += mobile.getPrice();
        }
        System.out.println(sum);
    }
}
