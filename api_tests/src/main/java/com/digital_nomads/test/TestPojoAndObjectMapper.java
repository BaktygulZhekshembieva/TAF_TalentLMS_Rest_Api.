package com.digital_nomads.test;

import com.digital_nomads.pojo.Unknown;
import com.digital_nomads.pojo.UnknownResponseList;
import com.digital_nomads.pojo.User;
import com.digital_nomads.pojo.UserResponseList;
import com.digital_nomads.utils.ObjectConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class TestPojoAndObjectMapper {
    Response response;

    RequestSpecification rs = RestAssured.given();

    @Test
    public void test1() throws JsonProcessingException {
        rs.baseUri("https://reqres.in/api/users")
        .contentType(ContentType.JSON);
        response = rs.request(Method.GET);
//        System.out.println(response.getBody().asPrettyString());//djз-ет весь список

        UserResponseList userResponseList = ObjectConverter
                .convertJsonObjectToJavaObject(response.body().asString(), UserResponseList.class);
        //этих весь поль-й полохим в лист. т.е из json конвертируем в java

//        for (User user: userResponseList.getUsers()){
//            System.out.println(user.getFirst_name());
//        }; // выводит только имена пол-й


        List<User> users = userResponseList.getUsers(); //положили всех поль-й в этот список

        List<String> firstNames = users
                .stream() // как укращенный foreach. перебираем.  Stream API java- прочитать
                .map(p -> p.getFirst_name()) // от тудого выбрали First_name
                .collect(Collectors.toList()); //и сохранили в лист

        System.out.println(firstNames);

    }

    @Test
    public void test2() throws JsonProcessingException {
        rs.baseUri("https://reqres.in/api/unknown")
                .contentType(ContentType.JSON);
        response = rs.request(Method.GET);
//        System.out.println(response.getBody().asPrettyString());//djз-ет весь список

        UnknownResponseList unknownResponseList = ObjectConverter
                .convertJsonObjectToJavaObject(response.body().asString(), UnknownResponseList.class);
        //этих весь поль-й полохим в лист. т.е из json конвертируем в java

        for (Unknown unknown : unknownResponseList.getUnknowns()) {
            System.out.println(unknown);
        }
        ;

    }



}
