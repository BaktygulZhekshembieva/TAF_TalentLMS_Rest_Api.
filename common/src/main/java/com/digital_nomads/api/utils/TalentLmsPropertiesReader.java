package com.digital_nomads.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Nursultan Musakunov
 */

public class TalentLmsPropertiesReader {
    private static Properties properties;

    /**
     * Getting properties from properties file
     */

    static {
        try {
//            String path = "C:\\Users\\Nursultan\\IdeaProjects\\TAF_Digital_Nomads\\common\\src\\main\\resources\\talentLMS.properties";
            String path = "C:\\Users\\User\\IdeaProjects\\TAF_TalentLMS_Rest_Api\\common\\src\\main\\resources\\talentLMS.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getProperty(String key) { // б-т искать конкретно что-то
        return properties.getProperty(key).trim();
    }
}
