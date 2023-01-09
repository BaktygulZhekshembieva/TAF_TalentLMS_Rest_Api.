package com.digital_nomads.practice;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import org.testng.annotations.Test;

public class ObjectMapperDemo {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void serializationTest() throws JsonProcessingException {
        Car x5 = new Car();
        x5.setBrand("BMW,X5");
        x5.setColor("Black");
        x5.setYear(2022);
        System.out.println(x5);

        String jsonPayLoad = objectMapper.writeValueAsString(x5);
        System.out.println(jsonPayLoad);
    }

    @Test
    public void deserializeTest() throws JsonProcessingException {
        String carPayLoad ="{\"" +
                "brand\":\"Lexus\"," +
                "\"color\":\"White\"," +
                "\"year\":2023}";
        System.out.println(carPayLoad);

        Car lexus = objectMapper.readValue(carPayLoad, Car.class);
        System.out.println(lexus);
    }
}
