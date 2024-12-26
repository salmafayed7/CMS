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

import static com.example.cinemamanagementsystem.Jdbc.testConnection;

public class  View_Movies_Controller extends Controller {

    @FXML
    private ComboBox<Movie> MoviesComboBox;
    @FXML
    private Label MovieDetailsLabel;
    @FXML
    private Button BackButton;

    String query = "SELECT * FROM movie";
    public void initialize() {
        testConnection();
        ArrayList<Movie> movies = Jdbc.GetMovies(query);
        ObservableList<Movie> observableMovies = FXCollections.observableArrayList(movies);
        MoviesComboBox.setItems(observableMovies);
        System.out.println("Movies loaded: " + observableMovies.size()); // Check size
        for (Movie movie : observableMovies) {
            System.out.println("Movie: " + movie.toString());
        }
    }

    @FXML
    public void showMovieDetails() {
        Movie selectedMovie = MoviesComboBox.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            MovieDetailsLabel.setText(selectedMovie.getDetails());
            MovieDetailsLabel.setVisible(true);
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