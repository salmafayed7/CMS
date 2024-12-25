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

    Stage stage;
    Scene scene;
    @FXML
    void logout(ActionEvent event) {
        try {
            switchScene(event, "Customer_Login.fxml", "Customer_Login");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void newBooking(ActionEvent event) {
        try {
            switchScene(event, "newBooking.fxml", "NewBooking");
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void updateInfo(ActionEvent event) {
        try {
            switchScene(event,"UpdateInfo.fxml", "UpdateInfo");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void viewHistory(ActionEvent event) {
        try {
            switchScene(event, "viewHistory", "ViewHistory");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

}
