package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CustOptions_Controller extends Controller {

    @FXML
    private Button bookingBtn;

    @FXML
    private Button historyBtn;

    @FXML
    private Button infoBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button moviesbtn;

    Stage stage;
    Scene scene;
    @FXML
    void logout(ActionEvent event) {
        try {
            switchScene(event, "Customer_Login.fxml", "Customer_Login", userid);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void newBooking(ActionEvent event) {
        try {
            if(event.getSource() == bookingBtn)
                switchScene(event, "NewBooking.fxml", "NewBooking", userid);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateInfo(ActionEvent event) {
        try {
            switchScene(event,"UpdateInfo.fxml", "UpdateInfo", userid);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void viewHistory(ActionEvent event) {
        try {
            switchScene(event, "viewHistory.fxml", "ViewHistory",userid);

        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void viewMovies(ActionEvent event) {
        try {
            if(event.getSource() == moviesbtn){
                switchScene(event, "View_Movies.fxml", "View_Movies",userid);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
