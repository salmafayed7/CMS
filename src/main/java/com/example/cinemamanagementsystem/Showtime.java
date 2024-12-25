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
}
