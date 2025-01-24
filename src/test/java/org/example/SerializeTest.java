package org.example;
import GoogleMaps.AddPlace;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static GoogleMaps.AddPlace.getAddPlace;
import static io.restassured.RestAssured.given;

public class SerializeTest {
    public static void main(String[] args) {
      // TODO Auto-generated method stub
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        AddPlace p = getAddPlace();

        //given() when() then()
        Response response;
        response = given().log().all().queryParam("key", "qaclick123")
                .body(p)
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();

        System.out.println("Serialization" + response.asString());
    }
}
