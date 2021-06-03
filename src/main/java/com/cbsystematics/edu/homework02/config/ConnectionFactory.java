package com.cbsystematics.edu.homework02.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
    private static Connection connection;
    private static Properties configProperties;

    private static final String HEROKU_DB_PROPERTIES_FILE_PATH = "heroku_db.properties";
    private static final String DRIVER = "driver";
    private static final String URL = "url";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";


    static {
        configProperties = new Properties();
        try {
            configProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(HEROKU_DB_PROPERTIES_FILE_PATH));
            Class.forName(configProperties.getProperty(DRIVER));
            connection = DriverManager.getConnection(
                    configProperties.getProperty(URL),
                    configProperties.getProperty(USERNAME),
                    configProperties.getProperty(PASSWORD)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    private ConnectionFactory() {}
}
