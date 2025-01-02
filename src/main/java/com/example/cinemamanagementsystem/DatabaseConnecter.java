package com.example.cinemamanagementsystem;

import java.sql.Connection;

public interface DatabaseConnecter {
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/cms";
    static final String DATABASE_USERNAME = "root";
    static final String DATABASE_PASSWORD = "";
    Connection getConnection();
}
