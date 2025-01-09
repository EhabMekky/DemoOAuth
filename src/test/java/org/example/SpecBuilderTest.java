package org.example;

import GoogleMaps.AddPlace;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static GoogleMaps.AddPlace.getAddPlace;
import static io.restassured.RestAssured.given;

public class SpecBuilderTest {
    public static void main(String[] args) {
        AddPlace p = getAddPlace();

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON)
                .build();


        //given() when() then()
        ResponseSpecification resSpc = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        RequestSpecification response = given().log().all().spec(req)
                .body(p);

        response.when().post("/maps/api/place/add/json")
                .then().spec(resSpc).extract().response().asString();

        System.out.println(response);
    }
}
