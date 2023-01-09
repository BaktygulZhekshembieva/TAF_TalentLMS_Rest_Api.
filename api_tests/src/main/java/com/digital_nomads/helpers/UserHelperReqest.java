package com.digital_nomads.helpers;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class UserHelperReqest {

    static Response response;
    static RequestSpecification rs = RestAssured.given();
    static Faker faker = new Faker();

    public static void createMockUser(){
        String userPayload = "{\n" +
                "    \"name\": \"" + faker.name().firstName() + "\",\n" +
                "    \"job\": \"" + faker.job().position() + "\"\n" +
                "}";
        response = rs
                .baseUri("https://reqres.in/api/users")
                .body(userPayload)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .request(Method.POST);

        Assert.assertEquals(response.getStatusCode(), 201);
        System.out.println(response.getBody().asPrettyString());
    }

}
