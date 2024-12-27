package com.example.cinemamanagementsystem;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.Date;

public class ViewHistoryController extends Controller {

    @FXML
    private TableColumn<Booking, String> BookingIdcol;

    @FXML
    private TableView<Booking> bookingtable;

    @FXML
    private TableColumn<Booking, Date> endcol;

    @FXML
    private TableColumn<Booking, String> hallcol;

    @FXML
    private TableColumn<Booking, String> moviecol;

    @FXML
    private TableColumn<Booking, String> pointscol;

    @FXML
    private TableColumn<Booking, Double> pricecol;

    @FXML
    private TableColumn<Booking, Date> startcol;


    private ObservableList<Booking> bookings;


    public void setup() {
        System.out.println("user id in setup"+userid);
        // Initialize the table columns with the Booking class fields
        BookingIdcol.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        moviecol.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
        startcol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endcol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        hallcol.setCellValueFactory(new PropertyValueFactory<>("hallId"));
        pricecol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        pointscol.setCellValueFactory(new PropertyValueFactory<>("usePoints"));

        // Load data into the table
        loadBookingData(); // Replace with actual customer ID
    }


    private void loadBookingData() {
        String query = "SELECT B.BookingID, B.CustomerID, M.Movieid, " +
                "M.Title, S.StartTime, S.EndTime, S.TicketPrice, " +
                "S.HallID, B.totalPrice, B.usePoints " +
                "FROM Booking B " +
                "JOIN Showtime S ON B.ShowtimeID = S.ShowtimeID " +
                "JOIN Movie M ON S.MovieID = M.MovieID " +
                "WHERE B.CustomerID = ?";

        bookings = Jdbc.getBookingsByCustomerId(userid, query);

        // Debugging: Check if bookings have been added
        System.out.println("Number of bookings fetched: " + bookings.size());

        // If bookings are null or empty, display a message
        if (bookings != null && !bookings.isEmpty()) {
            bookingtable.setItems(bookings);
        } else {
            System.out.println("No bookings to display for the customer ID: " + userid);
        }
    }

    @FXML
    void backAction(ActionEvent event) {
        try{
            switchScene(event,"CustOptions.fxml", "CustOptions",userid);
        }catch(IOException e){
            e.printStackTrace();
        }

    }


}
