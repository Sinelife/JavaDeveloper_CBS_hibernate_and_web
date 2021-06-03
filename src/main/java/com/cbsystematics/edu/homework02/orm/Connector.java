package com.cbsystematics.edu.homework02.orm;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {

    public static final String HEROKU_DB_PROPERTIES = "heroku_db.properties";
    public static final String DRIVER = "driver";
    public static final String URL = "url";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";



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
