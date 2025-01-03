package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ConfirmNBController extends Controller{

    @FXML
    private Button confirmBtn;

    @FXML
    private Label movieLabel;

    @FXML
    private Label ptsLabel;

    @FXML
    private Label seatsLabel;

    @FXML
    private Label stlabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Button cancelBtn;

    @FXML
    void cancelAction(ActionEvent event) {
        try {
            switchScene(event, "CustOptions.fxml", "Customer Options", userid);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    Movie movie;
    Showtime showtime;
    boolean pts;
    ArrayList<String> seats;
    double price;


    @FXML
    void setUp() {
        movie = NewBookingController.sendMovie();
        movieLabel.setText(movie.title);
        movieLabel.setVisible(true);

        showtime = NewBookingController.sendShowtime();
        stlabel.setText(showtime.toString());
        stlabel.setVisible(true);

        ptsLabel.setVisible(true);

        seats = stdHallController.sendSeats();
        String s = "";
        for (var i : seats){
            s += i;
            s += " ";
        }
        seatsLabel.setText(s);
        seatsLabel.setVisible(true);

        priceLabel.setText(String.valueOf(price));
        priceLabel.setVisible(true);

        pts = NewBookingController.sendPts();
        if(pts)
            ptsLabel.setText("Yes");
        else {
            ptsLabel.setText("No");
        }
    }

    int points;
    public void setTotalPrice(double totalPrice) {
        points = Jdbc.getUserPoints(userid);
        this.price = totalPrice;
        double remainingPrice = totalPrice;
        if (this.pts) {
            remainingPrice -= points;
            if (remainingPrice >= 0) {
                points = 0;
            } else {
                points -= totalPrice;
                remainingPrice = 0;
            }
        }
        else {
            points += totalPrice;
        }

        if (remainingPrice < 0) {
            remainingPrice = 0;
        }
        priceLabel.setText("$" + String.valueOf(remainingPrice));
    }


    @FXML
    void confirmAction(ActionEvent event) {
        String newBookingID = Jdbc.getMaxBookingID();
        boolean bookingConfirmed = Jdbc.insertNewBooking(newBookingID, userid, showtime.showtimeID, price, pts);
        for (String s : seats) {
            String row = s.substring(0, 1);
            String num = s.substring(1);
            String bookedSeat = Jdbc.getSeat(row, num);
            boolean isSeatBooked = Jdbc.insertBookingSeat(newBookingID, bookedSeat);
            if (isSeatBooked) {
                boolean seatUpdated = Jdbc.updateAvailableSeat(bookedSeat);
            }
            boolean ptsUpdate = Jdbc.updatePts(userid, points);
        }
        try {
            switchScene(event, "CustOptions.fxml", "Customer Options", userid);
            seats.clear();
            this.price = 0.0;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
