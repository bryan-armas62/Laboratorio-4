package com.example.dinosaurpark.config;

import java.io.InputStream;
import java.util.Properties;

public final class ParkConfig {

    private static ParkConfig instance;
    private final Properties props;

    private ParkConfig() {
        props = new Properties();

        try (InputStream input = getClass()
                .getClassLoader()
                .getResourceAsStream("park.properties")) {

            props.load(input);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized ParkConfig getInstance() {
        if (instance == null) {
            instance = new ParkConfig();
        }

        return instance;
    }

    public int getInt(String key, int defaultValue) {
        return Integer.parseInt(props.getProperty(key, String.valueOf(defaultValue)));
    }

    public double getDouble(String key, double defaultValue) {
        return Double.parseDouble(props.getProperty(key, String.valueOf(defaultValue)));
    }

    public String getString(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public static void resetForTesting() {
        instance = null;
    }
}