package org.example.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.example.model.Order;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class HomeTaskApiTest {
    @BeforeClass
    public void prepare() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2/")
                .addHeader("api_key", "Vasiliev")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        RestAssured.filters(new ResponseLoggingFilter());
    }
    @Test
    public void placeAnOrderForAPet() {
        Order ourOrder = new Order();
        int id = new Random().nextInt(500000);
        int petId = new Random().nextInt(500000);

        ourOrder.setId(id);
        ourOrder.setPetId(petId);

        given()
                    .body(ourOrder)
                .when()
                    .post("/store/order")
                .then()
                    .statusCode(200);

        Order ourActualOrder = given()
                    .pathParam("id", id)
                .when()
                    .get("/store/order/{id}")
                .then()
                    .statusCode(200)
                    .extract().body()
                    .as(Order.class);

        Assert.assertEquals(ourActualOrder.getId(), ourOrder.getId());
    }
    @Test
    public void deletePurchaseOrderById() {
        Order ourOrderForDelete = new Order();
        int id = new Random().nextInt(500000);
        int petId = new Random().nextInt(500000);

        ourOrderForDelete.setId(id);
        ourOrderForDelete.setPetId(petId);

        given()
                .body(ourOrderForDelete)
                .when()
                .post("/store/order")
                .then()
                .statusCode(200);

        given()
                .pathParam("id", ourOrderForDelete.getId())
                .when()
                .delete("/store/order/{id}")
                .then()
                .statusCode(200);
        given()
                .pathParam("id", ourOrderForDelete.getId())
                .when()
                .delete("/store/order/{id}")
                .then()
                .statusCode(404);
    }
    @Test
    public void savingBodyAsMap() {
        Map ourMap =
                given()
                        .when()
                        .get("/store/inventory")
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .as(Map.class);

        Assert.assertTrue(ourMap.containsKey("available"), "Inventory не содержит статус available" );
    }
}
