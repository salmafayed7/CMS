package com.example.cinemamanagementsystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLConnection implements DatabaseConnecter{
    private static SQLConnection instance;

    private SQLConnection() {}

    public static synchronized SQLConnection getInstance() {
        if (instance == null) {
            instance = new SQLConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error occurred while connecting to the database: " + e.getMessage());
            return null;
        }
    }
}
