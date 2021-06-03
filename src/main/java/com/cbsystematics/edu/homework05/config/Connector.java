package com.cbsystematics.edu.homework05.config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {

    private static final String HEROKU_DB_PROPERTIES = "heroku_db.properties";
    private static final String DRIVER = "driver";
    private static final String URL = "url";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";



    private static Connection conn;
    private static Properties configProperties;



    public static Connection getConnection() {
        connectToDatabase();
        return conn;
    }


    public static void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void connectToDatabase() {
        configProperties = new Properties();
        try {
            configProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(HEROKU_DB_PROPERTIES));
            Class.forName(configProperties.getProperty(DRIVER));
            conn = DriverManager.getConnection(
                    configProperties.getProperty(URL),
                    configProperties.getProperty(USERNAME),
                    configProperties.getProperty(PASSWORD)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
