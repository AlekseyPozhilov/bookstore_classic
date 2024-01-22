package com.belhard.bookstore;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Log4j2
public class PropertiesManager {
    private final List<Properties> configs;

    public PropertiesManager(String... fileNames) {
        configs = new ArrayList<>();
        for (String fileName : fileNames) {
            try (InputStream is = getClass().getResourceAsStream(fileName)) {
                Properties properties = new Properties();
                properties.load(is);
                configs.add(properties);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getKey(String key){
        String prop = System.getenv(key);
        if(prop != null){
            return prop;
        }
        for (Properties config : configs) {
            prop = config.getProperty(key);
            if(prop != null){
                return prop;
            }
        }
        return null;
    }
}
