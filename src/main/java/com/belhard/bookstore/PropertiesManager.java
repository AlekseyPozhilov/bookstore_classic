package com.belhard.bookstore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    private static final Logger logger = LogManager.getLogger(PropertiesManager.class);
    private final Properties properties;

    public PropertiesManager(String fileName) {
        try (FileInputStream fis = new FileInputStream("src/main/resources/" + fileName)) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            logger.error("ERROR");
            throw new RuntimeException(e);
        }
    }

    public String getKey(String key){
        return properties.getProperty(key);
    }
}
