package com.example.cinemamanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class trial extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateInfo.fxml"));
        Parent root = loader.load();
        //Stage stage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
