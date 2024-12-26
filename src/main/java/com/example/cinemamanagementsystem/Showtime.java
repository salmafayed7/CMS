package com.example.cinemamanagementsystem;
import java.util.*;
public class Showtime {
    public String showtimeID;
    public String movieID;
    public String movieTitle;
    public Date startTime;
    public Date endTime;
    public String hallId;

    public Showtime (String movieID, String movieTitle, Date startTime, Date endTime, String hall) {
        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hallId=hall;

    }

    public Showtime(){}}

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

