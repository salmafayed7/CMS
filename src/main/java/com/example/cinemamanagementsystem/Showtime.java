package com.example.cinemamanagementsystem;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Showtime {
    public String showtimeID;
    public String movieID;// why string not Movie obj
    public Date startTime;
    public Date endTime;
    public double ticketPrice;
    public Hall hall;

    public Showtime (String movieID, Date startTime, Date endTime, double ticketPrice, Hall hall) {
        this.movieID = movieID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ticketPrice = ticketPrice;
        this.hall = hall;

    }
    public Showtime(){}

  /*  public boolean insert(){
        String query="INSERT INTO SHOWTIME(MovieID, HallID, StartTime, EndTime, TicketPrice) VALUES(?,?,?,?,?)";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement= connection.prepareStatement(query);
        ){
            statement.setString(1,movieID);
            statement.setString(2,hall.hallNum);
            statement.setDate(3,new java.sql.Date(startTime.getTime()));
            statement.setDate(4,new java.sql.Date(endTime.getTime()));
            statement.setDouble(5,ticketPrice);
            statement.executeUpdate();
            return true;

        }catch(SQLException e){
            System.out.println("Error saving showtime: " + e.getMessage());
            return false;
        }
    }*/
}
