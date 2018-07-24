package com.qulix.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceFactory {

    public static Properties getResources(String resourcePath) {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();

        try(InputStream resourceStream = classloader.getResourceAsStream(resourcePath)) {
            properties.load(resourceStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return properties;
    }
}
