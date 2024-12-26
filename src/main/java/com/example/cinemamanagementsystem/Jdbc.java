package com.example.cinemamanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Jdbc {
    public static boolean signUp(String email, String password, String name, String phone, String query) {
        SQLConnection sqlconnection = SQLConnection.getInstance();
        try (Connection connection = sqlconnection.getConnection()) {
            if (connection == null) {
                throw new SQLException("failed to establish connection");
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, name);
                preparedStatement.setString(4, phone);

                int result = preparedStatement.executeUpdate();
                return result > 0;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean validateLogin(String email, String password, String query) {
        SQLConnection sqlConnector = SQLConnection.getInstance();
        try (Connection connection = sqlConnector.getConnection();) {
            if (connection == null) {
                throw new SQLException("Failed to establish a connection to the database.");
            }
            try (PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setString(1, email);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean checkAvailability(String row, String seatnum, String query) {
        SQLConnection sqlConnector = SQLConnection.getInstance();
        boolean isAvailable = false;

        try (Connection connection = sqlConnector.getConnection()) {
            if (connection == null) {
                throw new SQLException("Failed to establish a connection to the database.");
            }

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, row);
                statement.setString(2, seatnum);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        isAvailable = resultSet.getBoolean("isavailable");
                    }
                }
            }
        } catch (SQLException e) {
            return false;
        }

        return isAvailable;
    }
}