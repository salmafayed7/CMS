package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.stage.Window;


public class  View_Movies_Controller extends Controller {

    @FXML
    private ComboBox<Movie> MoviesComboBox;
    @FXML
    private Button Back_Button;
    @FXML
    private Label Actors;
    @FXML
    private Label Director;
    @FXML
    private Label Duration;
    @FXML
    private Label Genre;
    @FXML
    private Label Rating;
    @FXML
    private Label ReleaseDate;
    @FXML
    private Label Status;
    @FXML
    private Label Title;
    @FXML
    private Label alabel;
    @FXML
    private Label dirlabel;
    @FXML
    private Label durlabel;
    @FXML
    private Label glabel;
    @FXML
    private Label ratelabel;
    @FXML
    private Label rellabel;
    @FXML
    private Label slabel;
    @FXML
    private Label tlabel;
    @FXML
    private Button trailerButton;


    String query = "SELECT * FROM movie";

    public void initialize() {
        ArrayList<Movie> movies = Jdbc.GetMovies(query);
        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }
        ObservableList<Movie> observableMovies = FXCollections.observableArrayList(movies);
        MoviesComboBox.setItems(observableMovies);
        MoviesComboBox.setVisibleRowCount(10);
    }

    Movie movie;
    Movie selectedMovie;
    @FXML
    public void showMovieDetails() {
        selectedMovie = MoviesComboBox.getSelectionModel().getSelectedItem();
        this.movie=selectedMovie;
        if (selectedMovie != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String rd = sdf.format(selectedMovie.releaseDate);
            String formattedActors = selectedMovie.actors.replace(", ", "\n");
            String formattedDirectors = selectedMovie.director.replace(", ", "\n");
            dirlabel.setText(formattedDirectors);
            ratelabel.setText(selectedMovie.rating);
            rellabel.setText(rd);
            glabel.setText(selectedMovie.genre);
            alabel.setText(formattedActors);
            durlabel.setText(Integer.toString(selectedMovie.duration) + " minutes");
            slabel.setText(selectedMovie.status);
            dirlabel.setVisible(true);
            ratelabel.setVisible(true);
            rellabel.setVisible(true);
            glabel.setVisible(true);
            alabel.setVisible(true);
            durlabel.setVisible(true);
            slabel.setVisible(true);
            Director.setVisible(true);
            Rating.setVisible(true);
            ReleaseDate.setVisible(true);
            Genre.setVisible(true);
            Actors.setVisible(true);
            Duration.setVisible(true);
            Status.setVisible(true);
        }
        else {
            System.out.println("Movie not found");
        }
    }

    @FXML
    void BackButtonFun(ActionEvent event) {
        try {
            if (event.getSource() == Back_Button) {
                switchScene(event,"CustOptions.fxml","Customer Options", userid);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void trailerAction(ActionEvent event) {
        Window owner = trailerButton.getScene().getWindow();
        if (selectedMovie == null){
            showAlert(Alert.AlertType.ERROR, owner, "No movie selected", "Please select a movie.");
        }
        else {
            try {
                switchScene(event,"WatchTrailer.fxml", "Watch Trailer", userid, movie.title);
                System.out.println(movie.title + "in view movies");
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}