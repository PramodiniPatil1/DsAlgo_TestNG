package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    public static Properties properties = new Properties();

    static {
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config/config.properties")) {
            if (inputStream == null) {
                throw new RuntimeException("Config file not found in classpath at config/config.properties");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file", e);
        }
    }

    // You can still call this if you want to reload explicitly (optional)
    public static void loadConfig() throws IOException {
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config/config.properties")) {
            if (inputStream == null) {
                throw new IOException("Config file not found");
            }
            properties.load(inputStream);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBrowserType() {
        String browser = properties.getProperty("browser");
        if (browser == null || browser.isEmpty()) {
            throw new RuntimeException("Browser not specified in config.properties");
        }
        return browser;
    }

    public static String getUrl() {
        return properties.getProperty("Url");
    }

    public static String getUserName() {
        return properties.getProperty("username");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }

    public static String getUrlHome() {
        return properties.getProperty("urlHome");
    }
}
