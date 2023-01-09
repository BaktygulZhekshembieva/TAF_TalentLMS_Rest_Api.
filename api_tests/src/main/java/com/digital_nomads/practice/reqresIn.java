package com.digital_nomads.practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class reqresIn {

    Response response;
    RequestSpecification rs;

    @BeforeMethod
    public void setUp(){
        rs = RestAssured
                .given()
                .baseUri("https://reqres.in/api")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    @Test
    public void getNamesTest(){
        List<String> list = rs.given()
                .basePath("/users")
                .when().get().then()
                .statusCode(200)// не будем писать ассерт екуалс
                .extract() // ИЗВЛЕКАТЬ от кудо-то
                .jsonPath().getList("data.first_name");

        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + " --> " + list.get(i));
        }
    }



}
