package com.digital_nomads.api.apiHelper;

import com.digital_nomads.api.utils.TalentLmsPropertiesReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseMethods {

    public static RequestSpecification rs = RestAssured.given();


    public static RequestSpecification initHeaders(){
       return   rs
                .baseUri(TalentLmsPropertiesReader.getProperty("prod"))
                .auth()
                .basic(TalentLmsPropertiesReader.getProperty("apiKey"),"")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);

    }
}
