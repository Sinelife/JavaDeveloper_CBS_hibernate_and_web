package com.cbsystematics.edu.homework01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "impl:postgresql://ec2-54-83-8-246.compute-1.amazonaws.com:5432/d4gckf113qmjk1?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

    //  Database credentials
    static final String USER = "puvwmdlrwnpfav";
    static final String PASSWORD = "bcac9988952843913948e5477982acfad1f1f6c9a9655fc401878a1627337778";

    private static Connection conn;

    public static Connection getConnection() {
        return conn;
    }

    public static void connectToDatabase() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
