package com.example.cinemamanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Customer_SignUp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("com.example.cinemamanagementsystem.Customer_SignUp.fxml"));

    }
}
