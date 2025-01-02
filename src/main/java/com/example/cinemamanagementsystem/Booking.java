package com.example.cinemamanagementsystem;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class Booking {
    public String bookingId;
    public String customerID;
    public Showtime showtime;
    public double totalPrice;
    public boolean usePoints;
    public LocalDateTime bookingDate;

    public Booking(String customerID, String bookingID,Showtime showtime,double totalPrice, boolean usePoints) {
        this.customerID = customerID;
        this.showtime = showtime;
        bookingDate=LocalDateTime.now();
        this.bookingId=bookingID;
        this.totalPrice=totalPrice;
        this.usePoints=usePoints;
    }

    public Booking(String customerID, Showtime showtime, double totalPrice, boolean usePoints) {
        this.customerID = customerID;
        this.showtime = showtime;
        this.totalPrice = totalPrice;
        this.usePoints = usePoints;
    }


    public String getBookingId() {
        return bookingId;
    }

    public String getMovieTitle() {
        return showtime.getMovieTitle();
    }

    public Timestamp getStartTime() {
        return showtime.getStartTime();
    }

    public Timestamp getEndTime() {
        return showtime.getEndTime();
    }

    public String getHallId() {
        return showtime.getHallId();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isUsePoints() {
        return usePoints;
    }
}
