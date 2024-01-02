package com.belhard.bookstore;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class PropertiesManager {
    private final Properties properties;

    public PropertiesManager(String fileName) {
        try (FileInputStream fis = new FileInputStream("src/main/resources/" + fileName)) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            log.error("ERROR");
            throw new RuntimeException(e);
        }
    }

    public String getKey(String key){
        return properties.getProperty(key);
    }
}
