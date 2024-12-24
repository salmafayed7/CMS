package com.example.cinemamanagementsystem;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Admin extends Person {
    public String adminID;

    public Admin() {
        super();
    }
   /* public boolean save() {
        String query = "INSERT INTO Admin (AdminID) VALUES (?)";

        // Calling DatabaseConnection to insert the data
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, adminID);
            statement.executeUpdate();
            return true; // Successfully saved
        } catch (SQLException e) {
            System.out.println("Error saving admin: " + e.getMessage());
            return false; // Error saving
        }
    }*/
}
