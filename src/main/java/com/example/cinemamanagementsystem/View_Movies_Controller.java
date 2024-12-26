package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

//import static com.example.cinemamanagementsystem.Jdbc.testConnection;

public class  View_Movies_Controller extends Controller {

    @FXML
    private ComboBox<Movie> MoviesComboBox;
    @FXML
    private Label MovieDetailsLabel;
    @FXML
    private Button BackButton;

    String query = "SELECT * FROM movie";
    public void initialize() {

       // testConnection();
        ArrayList<Movie> movies = Jdbc.GetMovies(query);
        ObservableList<Movie> observableMovies = FXCollections.observableArrayList(movies);
        MoviesComboBox.setItems(observableMovies);
        System.out.println("Movies loaded: " + observableMovies.size()); // Check size
        for (Movie movie : observableMovies) {
            System.out.println("Movie: " + movie.toString());
        }
    }


        /*try {
            ArrayList<Movie> movies = Jdbc.GetMovies(query);
            ObservableList<Movie> observableMovies = FXCollections.observableArrayList(movies);
            MoviesComboBox.setItems(observableMovies);

            // Add listener to show movie details when selected
            MoviesComboBox.setOnAction(event -> showMovieDetails());
        } catch (Exception e) {
            e.printStackTrace();
            // Optionally show an alert to the user
        }
    }*/

    @FXML
    public void showMovieDetails() {
        Movie selectedMovie = MoviesComboBox.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            MovieDetailsLabel.setText(selectedMovie.getDetails());
            MovieDetailsLabel.setVisible(true);
        }
        else {
            MovieDetailsLabel.setText(""); // Clear details if no selection
        }
    }

    @FXML
    void BackButtonFun(ActionEvent event) {
        try {
            if (event.getSource() == BackButton) {
                switchScene(event,"CustOptions.fxml","CustOptions",userid);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}