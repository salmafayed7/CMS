package com.example.cinemamanagementsystem;

import java.util.*;

public class Booking {
    public int bookingID;
    public int customerID;
    public Showtime showtime;
    public ArrayList<Seat> seats;
    public double totalPrice;
    public boolean usePoints;
    public Date bookingDate;
    public Booking(int customerID, int showtimeID, ArrayList<Seat> seats,Date bookingDate) {
        this.customerID = customerID;
        //recheck for null pointer exception
        this.showtime.showtimeID = showtimeID;

    }
}
