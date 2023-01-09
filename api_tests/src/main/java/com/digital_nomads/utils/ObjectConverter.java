package com.digital_nomads.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectConverter { // свои методы для серилизации и десерилизации

    private static ObjectMapper objectMapper = new ObjectMapper();

/**
 * Converting json object string into the java object
 *
 * @param jsonObject
 * @param tClass java class that we want  to deserialize
 * @return Java object
 * @throws JsonProcessingException
 * */
    public static <T> T convertJsonObjectToJavaObject(String jsonObject, Class <T> tClass) throws JsonProcessingException {
        return objectMapper.readValue(jsonObject, tClass);
    }

}
