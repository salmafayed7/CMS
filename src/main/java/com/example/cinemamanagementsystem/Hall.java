package com.example.cinemamanagementsystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
public class Hall {
    public String hallNum;
    public int maxCapacity;
    public ArrayList<Seat> availableSeats;
    public String hallType;

    public boolean insert() {
        String query = "INSERT INTO HALL (MaxCapacity, HallType) VALUES (?, ?)";

        // Calling DatabaseConnection to insert the data
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, maxCapacity);
            statement.setString(2,hallType);
            statement.executeUpdate();
            return true; // Successfully saved
        } catch (SQLException e) {
            System.out.println("Error saving hall: " + e.getMessage());
            return false; // Error saving
        }
    }
}
