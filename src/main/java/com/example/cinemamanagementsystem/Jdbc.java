package com.example.cinemamanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Jdbc {
    public static String signUp(String email, String password, String name, String phone, String query) {
        String userid = null;
        SQLConnection sqlconnection = SQLConnection.getInstance();
        try (Connection connection = sqlconnection.getConnection()) {
            if (connection == null) {
                throw new SQLException("failed to establish connection");
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, name);
                preparedStatement.setString(4, phone);

                int result = preparedStatement.executeUpdate();
                if(result > 0){
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            userid =generatedKeys.getString(1);
                            return userid;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userid;
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

    public static boolean UpdateEmail(String email, String query,String userid) {
        SQLConnection sqlConnector = SQLConnection.getInstance();
        try(Connection connection = sqlConnector.getConnection();){
            if (connection == null) {
                throw new SQLException("Failed to establish a connection to the database.");
            }
            try(PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);
                statement.setString(2, userid);

                int result = statement.executeUpdate();
                if(result > 0){
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean UpdatePhoneNumber(String phone, String query,String userid) {
        SQLConnection sqlConnector = SQLConnection.getInstance();
        try(Connection connection = sqlConnector.getConnection();){
            if (connection == null) {
                throw new SQLException("Failed to establish a connection to the database.");
            }
            try(PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, phone);
                statement.setString(2, userid);

                int result = statement.executeUpdate();
                if(result > 0){
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Movie> GetMovies(String query) {
        SQLConnection sqlConnector = SQLConnection.getInstance();
        ArrayList<Movie> movies = new ArrayList<>();
        try(Connection connection = sqlConnector.getConnection()) {
            if (connection == null) {
                throw new SQLException("Failed to establish a connection to the database.");
            }
            try(PreparedStatement statement = connection.prepareStatement(query)){
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()){
                    String title = resultSet.getString("Title");
                    String genre = resultSet.getString("Genre");
                    int duration = resultSet.getInt("Duration");
                    String actors = resultSet.getString("Actors");
                    String rating = resultSet.getString("Rating");
                    String director = resultSet.getString("Director");
                    Date rdate = resultSet.getDate("ReleaseDate");
                    String status = resultSet.getString("Status");

                    Movie movie = new Movie(title,genre,duration,actors,rating,rdate,director,status);
                    movies.add(movie);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
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