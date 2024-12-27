package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;





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


    String query = "SELECT * FROM movie";
    public void initialize() {


        //Jdbc.testConnection();

        ArrayList<Movie> movies = Jdbc.GetMovies(query);
        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }
        ObservableList<Movie> observableMovies = FXCollections.observableArrayList(movies);
        System.out.println("Number of movies: " + observableMovies.size());
        MoviesComboBox.setItems(observableMovies);
        MoviesComboBox.setVisibleRowCount(10);
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String rd = sdf.format(selectedMovie.releaseDate);
            String formattedActors = selectedMovie.actors.replace(", ", "\n");
            String formattedDirectors = selectedMovie.director.replace(", ", "\n");

            //tlabel.setText(selectedMovie.title);
            dirlabel.setText(formattedDirectors);
            ratelabel.setText(selectedMovie.rating);
            rellabel.setText(rd);
            glabel.setText(selectedMovie.genre);
            alabel.setText(formattedActors);
            durlabel.setText(Integer.toString(selectedMovie.duration));
            slabel.setText(selectedMovie.status);
            //tlabel.setVisible(true);
            dirlabel.setVisible(true);
            ratelabel.setVisible(true);
            rellabel.setVisible(true);
            glabel.setVisible(true);
            alabel.setVisible(true);
            durlabel.setVisible(true);
            slabel.setVisible(true);
          //  Title.setVisible(true);
            Director.setVisible(true);
            Rating.setVisible(true);
            ReleaseDate.setVisible(true);
            Genre.setVisible(true);
            Actors.setVisible(true);
            Duration.setVisible(true);
            Status.setVisible(true);
        }else{
            System.out.println("Movie not found");
        }
    }

    @FXML
    void BackButtonFun(ActionEvent event) {
        try {
            if (event.getSource() == Back_Button) {
                switchScene(event,"CustOptions.fxml","CustOptions", userid);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}