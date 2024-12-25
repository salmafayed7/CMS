package com.example.cinemamanagementsystem;
import java.util.*;
public class Showtime {
    public int showtimeID;
    public int movieID;
    public Date startTime;
    public Date endTime;
    public double ticketPrice;
    public Hall hall;

    public Showtime (int movieID, Date startTime, Date endTime, double ticketPrice, Hall hall) {
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
