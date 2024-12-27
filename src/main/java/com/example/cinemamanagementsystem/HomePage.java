package com.example.cinemamanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePage extends Application {



    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file into a Parent object
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustOptions.fxml"));
        Parent root = loader.load();

        // Create a scene with the loaded root (Parent)
        Scene scene = new Scene(root);

        // Set the scene on the stage
        stage.setScene(scene);
        stage.setTitle("Cinema Management System");
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}