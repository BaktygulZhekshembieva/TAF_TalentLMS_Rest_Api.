package com.digital_nomads.practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class Demo1 {

    final String baseURL = "https://reqres.in/api";

    Response response; // to see Response detailes
    RequestSpecification requestSpecification; // указываем header параметры


    @BeforeMethod // создали беформетод, чтоб все не писать в каждом методе
    public void setBaseURL(){
        requestSpecification = RestAssured
                .given()
                .baseUri(baseURL) // указываем наш основной URL
                .contentType(ContentType.JSON) //для передачи данных в формате JSON
                .accept(ContentType.JSON); // для получения данных в формате JSON
    }

    @Test
    public void getListOfUsersTest() {
        response = requestSpecification
                .request(Method.GET, "/users");

        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.getBody().asPrettyString());
    }


    @Test
    public void getSingleUserTest(){
        response = requestSpecification
                .request(Method.GET, "/users/8");

        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.getBody().asPrettyString());
    }

    @Test
    public void createNewUserTest(){
        String userPayLoad = "{\n" +
                "    \"name\": \"TestName\",\n" +
                "    \"job\": \"SDET\"\n" +
                "}";
        response = requestSpecification
                .body(userPayLoad)
                .request(Method.POST, "/users");

        Assert.assertEquals(response.getStatusCode(), 201);
        System.out.println(response.getBody().asPrettyString());
    }



//    @Test
//    public void getListOfUsersTest(){
//        Response response = RestAssured //пишем запрос ис-я синтаксис геркин. и получаем ответ
//                .given()
//                .baseUri(baseURL) // наш основнойс ендпойнт
//                .contentType(ContentType.JSON)// укажем тип данных для отправки (формат данных)
//                .accept(ContentType.JSON)// укажем тип данных для получения ответа
//                .when()
//                .request(Method.GET,"/users"); //добавим в наш основной URL новый ендпойнт
        //.given(), .when() спомагательные. помогают попорядку, логически построить наш код.
        //у них никокого функционала

//        System.out.println(response.getStatusCode()); //для посмотра статус кода
//        System.out.println(response.getBody().asPrettyString()); // для прсмотра боди ответа
    //asPrettyString()-вывести в красивом виде или в формате JSON
//    }
}

