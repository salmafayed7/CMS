package com.example.cinemamanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewBookingController extends Controller {

    @FXML
    private Button confirmBtn;

    @FXML
    private ComboBox<Movie> movieBox;

    @FXML
    private CheckBox noBox;

    @FXML
    private Label priceLabel;

    @FXML
    private Button seatBtn;

    @FXML
    private ComboBox<Showtime> showtimeBox;

    @FXML
    private CheckBox yesBox;

    @FXML
    private Button backBtn;

    @FXML
    private double totalPrice;

    @FXML
    private boolean usePoints;

    String moviequery = "SELECT * FROM movie";
    String showtimequery = "SELECT StartTime, EndTime, MovieID FROM showtime;";

    @FXML
    public void initialize() {
        //Jdbc.testConnection();
        ArrayList<Movie> movies = Jdbc.GetMovies(moviequery);
        ObservableList<Movie> observableMovies = FXCollections.observableArrayList(movies);
        movieBox.setItems(observableMovies);
        System.out.println("Movies loaded: " + observableMovies.size()); // Check size
        for (Movie movie : observableMovies) {
            System.out.println("Movie: " + movie.toString());
        }



    }

    @FXML
    void confirmAction(ActionEvent event) {
        Showtime selectedShowtime = showtimeBox.getSelectionModel().getSelectedItem();
        Booking booking = new Booking(userid, selectedShowtime, this.totalPrice, this.usePoints);

    }

    @FXML
    void movieAction(ActionEvent event) {
        Window owner = movieBox.getScene().getWindow();
        ArrayList<Movie> movies = Jdbc.GetMovies(moviequery);
        Movie selectedMovie = movieBox.getSelectionModel().getSelectedItem();
        if (selectedMovie == null) {
            System.out.println("No movie selected.");
            return;
        }
        for (Movie movie : movies) {
            if (movie.equals(selectedMovie)) {
                selectedMovie.movieID = movie.movieID;
            }
        }

        System.out.println("Movie selected: " + selectedMovie.toString());
        System.out.println("Selected Movie ID: " + selectedMovie.movieID);
        System.out.println("Selected Movie Name: " + selectedMovie.title);

        ArrayList<Showtime> showtimes = Jdbc.getShowtimes(showtimequery);
        System.out.println("Fetched showtimes: " + showtimes.size());
        ArrayList<Showtime> finalST = new ArrayList<>();
        for (Showtime s : showtimes) {
            if (selectedMovie.movieID.equals(s.movieID)) {
                finalST.add(s);
            }
        }
        System.out.println("Filtered showtimes count: " + finalST.size());
        if (finalST.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "No Showtimes found", "No Showtimes found");
            try {
                switchScene(event, "NewBooking.fxml", "New Booking");
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }

        System.out.println("Filtered showtimes: " + finalST.size());
        ObservableList<Showtime> observableShowtimes = FXCollections.observableArrayList(finalST);
        showtimeBox.setItems(observableShowtimes);
    }


    @FXML
    void noAction(ActionEvent event) {

    }

    @FXML
    void seatsAction(ActionEvent event) {
        Window owner = seatBtn.getScene().getWindow();
        try {
            switchScene(event, "StdHall.fxml", "Choose seats", userid);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void showtimeAction(ActionEvent event) {

    }

    @FXML
    void yesAction(ActionEvent event) {

    }
    String pointsQuery = "SELECT Points FROM Customer WHERE CustomerID = ?";

    @FXML
    public void setUsePoints(){
        if (yesBox.isSelected()) {
            this.usePoints = true;
        }
        else this.usePoints = false;
    }

    @FXML
    public void setTotalPrice(double totalPrice){
        this.totalPrice = totalPrice;
        System.out.println("Total Price: " + this.totalPrice);
        int points = Jdbc.getpoints(userid, pointsQuery);
        setUsePoints();
        System.out.println("Points available: " + points);
        if(this.usePoints){
            totalPrice -= points;
        }
        else{
            System.out.println("cry");
        }
        if (totalPrice < 0) {
            totalPrice = 0;  // If the points exceed the total price, set price to 0
        }
        System.out.println("Updated total price: $" + totalPrice);
        priceLabel.setText("Total Price: $" + totalPrice);
    }



    @FXML
    void backAction(ActionEvent event) {
        Window owner = backBtn.getScene().getWindow();
        try {
            switchScene(event, "CustOptions.fxml", "CustOptions", userid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
