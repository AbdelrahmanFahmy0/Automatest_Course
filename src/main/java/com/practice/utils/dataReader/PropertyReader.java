package com.practice.utils.dataReader;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class PropertyReader {

    /**
     * Loads all properties from .properties files located in the src/main/resources directory.
     * Recursively searches for all .properties files and merges them into system properties.
     * Each property file is loaded and added to the system properties collection.
     *
     * @return a Properties object containing all loaded properties, or null if an error occurs
     */
    public static Properties loadProperties() {
        try {
            Properties properties = new Properties();
            Collection<File> propertiesFiles;
            propertiesFiles = FileUtils.listFiles(new File("src/main/resources"), new String[]{"properties"}, true); //get all files with extension properties
            propertiesFiles.forEach(file -> {
                try {
                    properties.load(new FileInputStream(file));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                properties.putAll(System.getProperties());
                System.getProperties().putAll(properties);
            });
            return properties;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Retrieves the value of a specific property from system properties using the provided key.
     * Returns the property value if found, or an empty string if the property does not exist or an error occurs.
     *
     * @param key the property key to retrieve
     * @return the property value as a String, or an empty string if not found or an error occurs
     */
    public static String getProperty(String key) {
        try {
            return System.getProperty(key);
        } catch (Exception e) {
            return "";
        }
    }
}