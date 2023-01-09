package com.digital_nomads.practice;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TalentLMS_Users {

    Response response;

    RequestSpecification rs;
    Faker faker = new Faker();

    @BeforeMethod
    public void setUp(){
        rs = RestAssured
                .given()
                .baseUri("https://baktygul.talentlms.com/api/v1/users")
                .auth() //аутентификация
                .basic("rUIbawUK5tDwTm5dls2Yuf6lwzPMXm","") // здесь исп-ли бейзик аут и сюда пишем наш токен
                                                                  // и оставляем пустой стринг, тюк у нас не была пароля
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }


    @Test
    public void getAllUsersTest(){
        response = rs
                .request(Method.GET);

        JsonPath jsonPath = response.jsonPath();

        String name = jsonPath.getString("[0].first_name");
        System.out.println("name: " + name);

        int id = jsonPath.getInt("[0].id");
        System.out.println("id: " + id);


        System.out.println("_________________________");

        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println(response.getBody().asPrettyString());

    }

    @Test

    public void getUserByIdTest() {
        response = rs
                .queryParam("id", 1)
                .request(Method.GET);

        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println(response.getBody().asPrettyString());
    }

    @Test
    public void isUserOnlineTest(){
        response = rs
                .baseUri("https://baktygul.talentlms.com/api/v1/isuseronline")
                .queryParam("user_id",1)
                .request(Method.GET);

        JsonPath jsonPath = response.jsonPath();// все ответы положили в jsonPath
        boolean isOnline = jsonPath.getBoolean("online");
        //создаем нужный тип данных булен, int или стринг смотря что он принимает в значения,
        // и туда укажем ключ из userPayLoadа, Н: id, name, last_name, status, online и.т.д
        Assert.assertTrue(isOnline);
//        Assert.assertFalse(isOnline); // если укажем это то тест упадет т.к. он тру
        System.out.println(response.getBody().asPrettyString());
    }

    @Test
    public void getUserAmountTest(){ //количество пользователей
        List<String> usersList = rs.given()
                .when()
                .get() //это http методы --> .request(Method.GET);
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("first_name");// в этом листе хранятся только имена

        System.out.println(usersList.size());

        for (String userName : usersList) {
            System.out.println(userName);
        }
    }

    @Test
    public void createNewUserTest(){ // соз-е 4 пользователя
        for (int i = 0; i <= 3; i++) {

            response = rs
                .baseUri("https://baktygul.talentlms.com/api/v1/usersignup")
                .contentType("multipart/form-data")
                .multiPart("first_name", faker.name().firstName())
                .multiPart("last_name", faker.name().lastName())
                .multiPart("email", faker.internet().emailAddress())
                .multiPart("login", faker.name().username())
                    .multiPart("password", "Qqrww1234")
                    .and()
//                .multiPart("password", faker.internet().password(8,10,true,true))
//                // password принимает мин 8 макс 10 символов, включая в себя апперкейсы и символы.
                .request(Method.POST);

        System.out.println(response.getBody().asPrettyString());
        Assert.assertEquals(response.statusCode(), 200);
    }
        List<String> users = rs.given() // cоздаем новый запрос чтоб узнать количество поль-й
                .baseUri("https://baktygul.talentlms.com/api/v1/users")
                .when().and()
                .get()
                .then().and()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath().get("id");

        String p = "";
        int t;

        Assert.assertEquals(users.size(), 5);
        Assert.assertNotNull(users);

        for (int i = 0; i < users.size(); i++) {
            p = users.get(i);
            t = Integer.parseInt(p);
            if(t != 1){
                System.out.println(i + " --> " + users.get(i));
            }
        }
    }

    @Test(dependsOnMethods = "createNewUserTest")
    public void deleteUsersTest1() {
        String p = "";
        int t;
        List<String> users = rs.given().when()
                .baseUri("https://baktygul.talentlms.com/api/v1/users")
                .and().get().then()
                .statusCode(200)
                .extract().jsonPath().getList("id");

        for (String user : users) {
           p = user;
           t = Integer.parseInt(p);
           if (t != 1){
               response = rs
                       .baseUri("https://baktygul.talentlms.com/api/v1/deleteuser")
                       .contentType("multipart/form-data")
                       .multiPart("user_id", t)
                       .multiPart("delete_by_user_id", 1)
                       .request(Method.POST);
               System.out.println(response.getBody().asPrettyString());
               Assert.assertEquals(response.getStatusCode(), 200);
           }
        }

        List<String> oneUser = rs
                .baseUri("https://baktygul.talentlms.com/api/v1/users")
                .when().get().then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath().getList("id");

        Assert.assertEquals(oneUser.size(), 1);
        Assert.assertNotNull(oneUser);
    }




    @Test
    public void createUsersTest(){
        // Create user
        // total users should be 5
    }

    @Test(dependsOnMethods = "createUsersTest")
    public void deleteUsersTest2(){
        // Delete all created users
        // total users should be 1
        // use dynamic way to delete users by ID
    }

}
