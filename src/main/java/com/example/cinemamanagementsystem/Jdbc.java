package com.example.cinemamanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    public static String validateLogin (String email, String password, String query){
        String userid=null;
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
                    userid=resultSet.getString("ID");
                    return userid;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userid;
    }
    public static boolean Updatepassword(String newp,String query, String userid){
        SQLConnection sqlConnecter = SQLConnection.getInstance();
        try (Connection connection = sqlConnecter.getConnection()) {
            if (connection == null) {
                throw new SQLException("failed to establish connection");
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newp);
                preparedStatement.setString(2, userid);

                int result = preparedStatement.executeUpdate();
                return result > 0;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public ObservableList<Booking> getBookingsByCustomerId(String customerId,String query) {
        ObservableList<Booking> bookings = FXCollections.observableArrayList();
       // String query = "SELECT B.bookingId, B.customerId,  " +
        //                " M.movieTitle, S.startTime, S.endTime, S.ticketPrice, " +
        //                "S.hallid, B.totalPrice, B.usePoints " +
        //                "FROM Booking B " +
        //                "JOIN Showtime S ON B.showtimeId = S.showtimeId " +
        //                "JOIN Movie M ON S.movieID = M.movieID " +  // Join Movie table to get movie title
        //                "WHERE B.customerId = ?";

        SQLConnection sqlConnecter = SQLConnection.getInstance();
        try (Connection connection = sqlConnecter.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int bookingId = rs.getInt("bookingId");
                String movieID = rs.getString("movieID");
                String movieTitle = rs.getString("movieTitle");  // Get movie title from Movie table
                Date startTime = rs.getDate("startTime");
                Date endTime = rs.getDate("endTime");
                String hallid= rs.getString("HallId");
                double ticketPrice = rs.getDouble("ticketPrice");
                double totalPrice = rs.getDouble("totalPrice");
                boolean usePoints = rs.getBoolean("usePoints");

                Showtime showtime = new Showtime(movieID, movieTitle, startTime, endTime, hallid);
                Booking booking = new Booking(customerId, showtime, totalPrice, usePoints);

                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

}