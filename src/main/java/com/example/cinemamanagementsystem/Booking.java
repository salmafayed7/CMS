package com.example.cinemamanagementsystem;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class Booking {
    public String bookingID;
    public String customerID;
    public Showtime showtime = new Showtime();
    public ArrayList<Seat> seats;
    public double totalPrice;
    public boolean usePoints;
    public Date bookingDate;
    public Booking(String customerID, String showtimeID, ArrayList<Seat> seats,Date bookingDate) {
        this.customerID = customerID;
        //recheck for null pointer exception
        this.showtime.showtimeID = showtimeID;

    }
   /* public boolean insert(List<Integer> seatIds) {
        String query = "INSERT INTO Booking (CustomerID, ShowtimeID, TotalPrice, UsePoints, BookingTime) VALUES (?, ?, ?, ?, ?)";
        String insertBookingSeatQuery = "INSERT INTO bookingSeat (booking_id, seat_id) VALUES (?, ?)";

        // Calling DatabaseConnection to insert the data
        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false); // Start a transaction

            // Insert the booking into the Booking table
            try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, customerID);
                statement.setString(2, showtime.showtimeID);
                statement.setString(3, String.valueOf(totalPrice));
                statement.setBoolean(4, usePoints);
                statement.setDate(5, new java.sql.Date(bookingDate.getTime()));
                statement.executeUpdate();

                // Retrieve the generated booking_id
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    String bookingId = generatedKeys.getString(1);
                    bookingID=bookingId;
                    // Insert entries into the bookingSeat table
                    try (PreparedStatement bookingSeatStmt = connection.prepareStatement(insertBookingSeatQuery)) {
                        for (int seatId : seatIds) {
                            bookingSeatStmt.setString(1, bookingId);  // Set the generated booking_id
                            bookingSeatStmt.setInt(2, seatId);     // Set the seat_id
                            bookingSeatStmt.addBatch();             // Add to batch for efficient insertion
                        }
                        bookingSeatStmt.executeBatch();  // Execute all insertions into the bookingSeat table
                    }

                    connection.commit();  // Commit the transaction
                    return true;  // Successfully saved
                } else {
                    connection.rollback();  // Rollback if booking_id is not generated
                    return false;
                }
            } catch (SQLException e) {
                connection.rollback();  // Rollback in case of any error
                System.out.println("Error saving booking and seats: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error establishing database connection: " + e.getMessage());
            return false;  // Error connecting to the database
        }
    }*/

}
