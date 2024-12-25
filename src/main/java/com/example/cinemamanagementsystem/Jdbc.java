package com.example.cinemamanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Jdbc {
    public static boolean signUp(String email, String password, String name,String phone,String query){
        SQLConnection sqlconnection= SQLConnection.getInstance();
        try(Connection connection=sqlconnection.getConnection()){
            if(connection==null){
                throw new SQLException("failed to establish connection");
            }
            try(PreparedStatement preparedStatement=connection.prepareStatement(query)){
                preparedStatement.setString(1,email);
                preparedStatement.setString(2,password);
                preparedStatement.setString(3,name);
                preparedStatement.setString(4,phone);

                int result=preparedStatement.executeUpdate();
                if(result>0){
                    return true;
                }
                return false;
            }
        }catch(SQLException e) {
            return false;
        }
    }
}
