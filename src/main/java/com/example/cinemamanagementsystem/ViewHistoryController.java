package com.example.cinemamanagementsystem;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ViewHistoryController extends Controller {

    @FXML
    private TableColumn<Booking, String> BookingIdcol;
    @FXML
    private Button cancelButton;

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


    public void setUp() {
        System.out.println("user id in setup"+userid);
        BookingIdcol.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        moviecol.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
        startcol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endcol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        hallcol.setCellValueFactory(new PropertyValueFactory<>("hallId"));
        pricecol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        pointscol.setCellValueFactory(new PropertyValueFactory<>("usePoints"));

        loadBookingData();
    }


    private void loadBookingData() {
        String query = "SELECT B.BookingID, B.CustomerID, M.Movieid, " +
                "M.Title, S.StartTime, S.EndTime, " +
                "S.HallID, B.totalPrice, B.usePoints " +
                "FROM Booking B " +
                "JOIN Showtime S ON B.ShowtimeID = S.ShowtimeID " +
                "JOIN Movie M ON S.MovieID = M.MovieID " +
                "WHERE B.CustomerID = ?";

        bookings = Jdbc.getBookingsByCustomerId(userid, query);

        System.out.println("Number of bookings fetched: " + bookings.size());

        if (bookings != null && !bookings.isEmpty()) {
            bookingtable.setItems(bookings);
        }
        else {
            System.out.println("No bookings to display for the customer ID: " + userid);
        }
    }

    @FXML
    void backAction(ActionEvent event) {
        try{
            switchScene(event,"CustOptions.fxml", "Customer Options", userid);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    void cancelAction(ActionEvent event) {
        Window owner = cancelButton.getScene().getWindow();
        Booking selectedB = bookingtable.getSelectionModel().getSelectedItem();
        if (selectedB != null) {
            ButtonType resultB = infoBox( "Are you sure you want to cancel this booking?","Confirm Cancellation");
            String bookingid = selectedB.getBookingId();
            Timestamp startTime = selectedB.getStartTime();
            LocalDate startDate = startTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (resultB == ButtonType.OK){
                if (startDate.isAfter(LocalDate.now())) {
                    boolean flag = Jdbc.cancelBooking(bookingid);
                    if (flag) {
                        infoBox("Your booking has been successfully cancelled.", "Booking Cancelled", "Booking Cancelled.");
                        bookings.clear();
                        loadBookingData();
                    }
                    else {
                        Platform.runLater(() -> {
                            infoBox("Failed to cancel booking.", "Error", "Cancellation Error.");
                        });
                    }
                }
                else {
                    showAlert(Alert.AlertType.ERROR, owner, "Error Cancelling", "This booking has already expired and cannot be cancelled.");
                }
            }
        }
        else {
            showAlert(Alert.AlertType.ERROR, owner, "No Booking Selected", "Please select a booking.");
            System.out.println("No row selected.");
        }
    }
}
