package com.digital_nomads.helpers;


import com.digital_nomads.pojo.User;
import com.digital_nomads.pojo.UserResponseList;
import com.digital_nomads.utils.ObjectConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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

    public static void returnUsers(String url, String endpoint, String code) throws JsonProcessingException {
        rs.baseUri(url)
                .basePath(endpoint)
                .auth()
                .basic(code, "")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .then().statusCode(200);
        response = rs.request(Method.GET);

        UserResponseList users = ObjectConverter
                .convertJsonObjectToJavaObject(response
                        .body().asString(), UserResponseList.class);

    Assert.assertNotNull(users);

//    List<User> list = users.getUsers();
//
//        List<Integer> user = list.stream()
//                .map(a -> a.getId())
//                .collect(Collectors.toList());
//        System.out.println(user);
        for(User us : users.getUsers()){
//            ObjectConverter.convertJsonObjectToJavaObject(us);
            System.out.println(us);
        }
    }

    public static void userSingUp(String url, String endpoint, String code) throws JsonProcessingException {
        for (int i = 0; i <= 3; i++) {
            rs
                    .baseUri(url)
                    .basePath(endpoint)
                    .auth().basic(code, "")
                    .contentType("multipart/form-data")
                    .multiPart("first_name", faker.name().firstName())
                    .multiPart("last_name", faker.name().lastName())
                    .multiPart("email", faker.internet().emailAddress())
                    .multiPart("login", faker.name().username())
                    .multiPart("password", faker.internet().password(9,10,
                            true,true,true))
                    .then().statusCode(200);

            try {
                response = rs.request(Method.POST);
            }catch (Exception e){
                e.printStackTrace();
            }

            Assert.assertEquals(response.statusCode(),200);
        }

        User[] users = ObjectConverter
                .convertJsonObjectToJavaObject(response
                        .body().asString(), User[].class);

        Assert.assertEquals(users.length,5);

        for (User u:users) {
            System.out.println(u);
        }
    }

    public static void editUser(String url, String endpoint, String code) throws JsonProcessingException {
        rs.baseUri(url)
                .basePath(endpoint)
                .auth().basic(code, "")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .then().statusCode(200);

        response = rs.request(Method.GET);

        User[] users = ObjectConverter
                .convertJsonObjectToJavaObject(response
                        .body().asString(), User[].class);

        Assert.assertEquals(users.length, 5);

        String p;
        int t;
        for (User u : users){
            p = String.valueOf(u.getId());
            t = Integer.parseInt(p);
            if (t != 1){
                rs
                        .contentType("multipart/form-data")
                        .multiPart("first_name", faker.name().firstName())
                        .multiPart("last_name", faker.name().lastName())
                        .multiPart("email", faker.internet().emailAddress())
                        .multiPart("login", faker.name().username())
                        .multiPart("password", faker.internet().password(9,10,
                                true,true,true));

                try {
                    response = rs.request(Method.POST);
                }catch (Exception e){
                    e.printStackTrace();
                }
                Assert.assertEquals(response.statusCode(),200);
            }
        }
    }







}
