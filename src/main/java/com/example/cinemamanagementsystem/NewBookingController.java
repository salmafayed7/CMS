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
    private ComboBox<Movie> movieBox;

    @FXML
    private CheckBox noBox;

    @FXML
    private Button seatBtn;

    @FXML
    private ComboBox<Showtime> showtimeBox;

    @FXML
    private CheckBox yesBox;

    @FXML
    private Button backBtn;

    @FXML
    public static boolean usePoints;


    String movieQuery = "SELECT * FROM movie WHERE Status = 'Now Showing'";
    String showtimeQuery = "SELECT StartTime, EndTime, MovieID, ShowtimeID FROM showtime;";

    @FXML
    public void initialize() {
        //Jdbc.testConnection();
        ArrayList<Movie> movies = Jdbc.GetMovies(movieQuery);
        ObservableList<Movie> observableMovies = FXCollections.observableArrayList(movies);
        movieBox.setItems(observableMovies);
        System.out.println("Movies loaded: " + observableMovies.size()); // Check size
        for (Movie movie : observableMovies) {
            System.out.println("Movie: " + movie.toString());
        }
    }
    public static Showtime selectedShowtime;

    public static Movie selectedMovie;

    @FXML
    void movieAction(ActionEvent event) {
        Window owner = movieBox.getScene().getWindow();
        ArrayList<Movie> movies = Jdbc.GetMovies(movieQuery);
        selectedMovie = movieBox.getSelectionModel().getSelectedItem();
        for (Movie movie : movies) {
            if (movie.equals(selectedMovie)) {
                selectedMovie.movieID = movie.movieID;
            }
        }


        ArrayList<Showtime> showtimes = Jdbc.getShowtimes(showtimeQuery);
        ArrayList<Showtime> finalST = new ArrayList<>();
        for (Showtime s : showtimes) {
            if (selectedMovie.movieID.equals(s.movieID)) {
                finalST.add(s);
            }
        }

        if (finalST.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "No Showtimes found", "No Showtimes found");
            try {
                switchScene(event, "NewBooking.fxml", "New Booking", userid);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
        ObservableList<Showtime> observableShowtimes = FXCollections.observableArrayList(finalST);
        showtimeBox.setItems(observableShowtimes);
    }


    @FXML
    void noAction(ActionEvent event) {
        if (noBox.isSelected()) {
            usePoints = false;
            yesBox.setSelected(false);
        }
    }

    @FXML
    void seatsAction(ActionEvent event) {
        Window owner = seatBtn.getScene().getWindow();
        selectedShowtime = showtimeBox.getSelectionModel().getSelectedItem();
        if(selectedMovie == null) {
            showAlert(Alert.AlertType.ERROR, owner, "Empty Selection", "Please select a movie");
        }
        else if(selectedShowtime == null) {
            showAlert(Alert.AlertType.ERROR, owner, "Empty Selection", "Please select a showtime");
        }
        else if (!yesBox.isSelected() && !noBox.isSelected()) {
            showAlert(Alert.AlertType.ERROR, owner, "Empty Selection", "Please select whether you want to use your points");
        }
        else {
            try {
                switchScene(event, "StdHall.fxml", "Choose seats", userid);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void showtimeAction(ActionEvent event) {

    }

    @FXML
    void yesAction(ActionEvent event) {
        if(yesBox.isSelected()) {
            usePoints = true;
            noBox.setSelected(false);
        }
    }



    @FXML
    void backAction(ActionEvent event) {
        try {
            switchScene(event, "CustOptions.fxml", "Customer Options", userid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Movie sendMovie(){
        return selectedMovie;
    }

    public static Showtime sendShowtime(){
        return selectedShowtime;
    }

    public static boolean sendPts(){
        return usePoints;
    }

}
