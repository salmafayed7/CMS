package com.example.cinemamanagementsystem;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Seat {
    public String seatID;
    public Hall hall;
    public int row;
    public String type;
    public boolean status;

   /* public boolean insert(){
        String query="INSERT INTO SEAT(HallID, Row, IsAvailable, SeatType) values(?,?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1,hall.hallNum);
            statement.setInt(2,row);
            statement.setBoolean(3,status);
            statement.setString(4,type);
            statement.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.out.println("Error saving seat: " + e.getMessage());
            return false;
        }
    }*/

}
