package com.example.cinemamanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Jdbc {

    public static  boolean validateLogin(String email, String password, String query){
        SQLConnection sqlConnector = SQLConnection.getInstance();
        try(Connection connection = sqlConnector.getConnection(); ){
            if (connection == null) {
                throw new SQLException("Failed to establish a connection to the database.");
            }
            try(PreparedStatement statement= connection.prepareStatement(query);){
                statement.setString(1,email);
                statement.setString(2,password);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return true;
                }
                return false;
            }
        }catch(SQLException e){
            return false;

        }

    }
}
