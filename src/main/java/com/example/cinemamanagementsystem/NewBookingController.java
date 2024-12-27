package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

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
    void confirmAction(ActionEvent event) {

    }

  /*  @FXML
    void movieAction(ActionEvent event) {
        Movie selectedMovie = movieBox.getValue();
        /*if (selectedMovie != null) {
            // Update showtimeBox based on the selected movie
            loadShowtimesForMovie(selectedMovie);
        }
    }
    @FXML
    void initialize() {
        loadMovies();
    }

    private void loadMovies() {
        List<Movie> movies = Jdbc.fetchMovies();
        movieBox.getItems().addAll(movies);
    }
    @FXML
    void noAction(ActionEvent event) {

    }

    @FXML
    void seatsAction(ActionEvent event) {

    }

    private void loadShowtimesForMovie(Movie movie) {
        // Fetch showtimes from the database based on the selected movie
        List<Showtime> showtimes = Jdbc.fetchShowtimesForMovie(movie);
        showtimeBox.getItems().clear();
        showtimeBox.getItems().addAll(showtimes);
    }

 /*   @FXML
    void showtimeAction(ActionEvent event) {
        Showtime selectedShowtime = showtimeBox.getValue();
        if (selectedShowtime != null) {
            // Update priceLabel based on selected showtime
            // Example: Assuming showtime has a method getPrice()
            priceLabel.setText("Price: $" + selectedShowtime.getPrice());
        }
    }

    @FXML
    void yesAction(ActionEvent event) {

    }*/

    public void setTotalPrice(double totalPrice){
        priceLabel.setText("Total Price" + totalPrice);
    }

}
