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
    private ComboBox<String> showtimeBox;

    @FXML
    private CheckBox yesBox;
    String moviequery = "SELECT * FROM movie";
    String showtimequery = "SELECT StartTime, EndTime, MovieID FROM showtime;";
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
        ArrayList<String> finalST = new ArrayList<>();
        for (Showtime s : showtimes) {
            if (selectedMovie.movieID.equals(s.movieID)) {
                finalST.add(s.toString());
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
        ObservableList<String> observableShowtimes = FXCollections.observableArrayList(finalST);
        showtimeBox.setItems(observableShowtimes);
    }


    @FXML
    void noAction(ActionEvent event) {

    }

    @FXML
    void seatsAction(ActionEvent event) {

    }


    @FXML
    void showtimeAction(ActionEvent event) {

    }

    @FXML
    void yesAction(ActionEvent event) {

    }

}
