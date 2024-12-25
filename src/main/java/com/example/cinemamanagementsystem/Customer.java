package com.example.cinemamanagementsystem;
import java.util.*;
public class Customer extends Person {
    public int customerID;

    public ArrayList<Booking> bookingHistory;
    public int points;

    public Customer(String name, String email, String password, int phone) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.customerID = phone;
        bookingHistory = new ArrayList<Booking>();
    }

}
