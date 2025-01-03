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
    public String getMovieTitle() {
        return this.movieTitle;
    }
    public String getHallId() {
        return this.hallId;
    }
    public Timestamp getStartTime() {
        return this.startTime;
    }
    public Timestamp getEndTime() {
        return this.endTime;
    }


    public Showtime(){}

    public Showtime(Timestamp startTime, Timestamp endTime, String movieID, String showtimeID) {
        this.startTime=startTime;
        this.endTime=endTime;
        this.movieID=movieID;
        this.showtimeID = showtimeID;
    }
    public String toString (){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM HH:mm");
        String start = dateFormat.format(this.startTime);
        String end = dateFormat.format(this.endTime);
        return start + " - " + end;
    }

}

