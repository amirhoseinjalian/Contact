package com.jalian.contact.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.util.Properties;
public class Config {
    private InputStream reader;
    //private FileReader reader;
    private Properties properties;
    private static Config config = new Config();
    private final Logger logger = LoggerFactory.getLogger(Config.class.getName());
    private Config() {
        try {
            //reader = new FileReader("D:/java apps/contactsWithDBAndMVC/src/config/system config.properties");
            logger.warn("you should have a properties file with name configuration system if you dont, you cant use app");
            reader = ClassLoader.getSystemResourceAsStream("configuration.properties");
            //reader = Thread.currentThread().getContextClassLoader().getResourceAsStream("system config.properties");
            properties = new Properties();
            properties.load(reader);
        }catch (Exception e) {
            logger.error("catches an exception while loading properties");
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public String getStoreModeProperty() {
        logger.info("you must set store mode {}", Config.class.getSimpleName());
        return properties.getProperty("storeMode");
    }
    public String getLogPathProperty() {
        return properties.getProperty("logPath");
    }
    public static Config getInstance() { return config; }
}