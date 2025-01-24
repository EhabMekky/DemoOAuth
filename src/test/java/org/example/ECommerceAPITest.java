package org.example;
import Pojo.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;

public class ECommerceAPITest {

    public static void main(String[] args) {
        LoginRequest login = new LoginRequest();
        login.setUserEmail("ehabkhallaf@gmail.com");
        login.setUserPassword("Hello123@");

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).build();

        // Given - When - Then
        RequestSpecification reqLogin = given().log().all().spec(req).body(login);

        LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login")
                .then().log().all().extract().response().as(LoginResponse.class);

        System.out.println(loginResponse.getToken());

        //Add product.
        String token = loginResponse.getToken();
        String userId = loginResponse.getUserId();

        RequestSpecification addProductBaseReq = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization", token)
                .build();

        RequestSpecification addProductReq = given().log().all().spec(addProductBaseReq)
                .contentType(ContentType.MULTIPART)
                .param("productName", "Macbook")
                .param("productAddedBy", userId)
                .param("productCategory", "electronics  ")
                .param("productSubCategory", "l aptops")
                .param("productPrice", "30000")
                .param("productDescription", "This is a macbook")
                .param("productFor", "men")
                .multiPart("productImage", new File("C://Users//ehab.khallaf//Downloads//1334857.png"));

        String addProductResponse = addProductReq.when().post("/api/ecom/product/add-product")
                .then().log().all().assertThat().statusCode(201).extract().response().asString();

        JsonPath js = new JsonPath(addProductResponse);
        System.out.println(addProductResponse);

        String productId = js.getString("productId");
        System.out.println("Product Id is " + productId);

        System.out.println("Full Response: " + addProductResponse);


        //Create Order
        String orderProductId = js.getString("productId");

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCountry("Egypt");
        orderDetails.setProductOrderedId(orderProductId);

        List<OrderDetails> orderDetailsList = new ArrayList<>();
        orderDetailsList.add(orderDetails);

        Orders order = new Orders();
        order.setOrders(orderDetailsList);

        RequestSpecification addProductToCart = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization", token)
                .setContentType(ContentType.JSON)
                .build();

        RequestSpecification orderReq = given().log().all().spec(addProductToCart)
                .body(order);

        String orderResponse = orderReq.when().post("/api/ecom/order/create-order")
                .then().log().all().extract().response().asString();

        System.out.println(orderResponse);

        // Delete Product
        RequestSpecification deleteProductBaseReq = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization", token)
                .setContentType(ContentType.JSON)
                .build();

        RequestSpecification deleteProductReq = given().log().all().spec(deleteProductBaseReq)
                .pathParams("productId", productId);

        String deleteProductResponse = deleteProductReq.when().delete("/api/ecom/product/delete-product/{productId}")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js1 = new JsonPath(deleteProductResponse);
        System.out.println(js1.getString("message"));
        Assert.assertEquals(js1.getString("message"), "Product Deleted Successfully");
    }
}
