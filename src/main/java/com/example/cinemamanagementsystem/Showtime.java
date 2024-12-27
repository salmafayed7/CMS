package com.example.cinemamanagementsystem;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
public class Showtime {
    public String showtimeID;
    public String movieID;
    public String movieTitle;
    public Timestamp startTime;
    public Timestamp endTime;
    public String hallId;

    public Showtime (String movieID, String movieTitle, Timestamp startTime, Timestamp endTime, String hall) {
        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hallId=hall;

    }

    public Showtime(){}

    public Showtime(Timestamp startTime, Timestamp endTime, String movieID) {
        this.startTime=startTime;
        this.endTime=endTime;
        this.movieID=movieID;
    }
    public String toString (){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM HH:mm");
        String start = dateFormat.format(this.startTime);
        String end = dateFormat.format(this.endTime);
        return start + " - " + end;
    }
}

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

