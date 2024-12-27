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
    public String getMovieTitle() {
        return this.movieTitle;
    }
    public String getHallId() {
        return this.hallId;
    }
    public Date getStartTime() {
        return this.startTime;
    }
    public Date getEndTime() {
        return this.endTime;
    }



    public Showtime(){}
}

