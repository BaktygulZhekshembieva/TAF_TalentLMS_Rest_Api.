package com.digital_nomads.api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Nursultan Musakunov
 */

public class TalentLmsPropertiesReader {
    private static Properties properties;

    static {
        try {
            String path = "C:\\Users\\Nursultan\\IdeaProjects\\TAF_Digital_Nomads\\common\\src\\main\\resources\\talentLMS.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getProperty(String key) {
        return properties.getProperty(key).trim();
    }
}
