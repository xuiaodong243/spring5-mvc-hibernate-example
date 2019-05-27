package com.bnpp.demo.spring.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public final class Config{
    private static final String CONFIG_FILE = "application.properties";
    private static Config instance;
    private Properties config;

    private Config() {
        config = new Properties();
        String location = System.getProperty("property.folder");
        System.out.println("System.getProperty(\"property.folder\"):"+location);
        if (location == null) {
            try {
                config.load(getClass().getClassLoader().getResourceAsStream(CONFIG_FILE));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            File cFile = new File(location + File.separator + CONFIG_FILE);
            try {
                config.load(new FileInputStream(cFile));
                config.setProperty("system_property",location);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }
    public Properties getConfig() {
        return config;
    }

}