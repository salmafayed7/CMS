package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Update_Info_Controller extends Controller {
    Scene scene;
    Stage stage;
    @FXML
    private Button BackButton;

    @FXML
    private Button PasswordUpdateButton;

    @FXML
    private Button PhoneUpdateButtom;

    @FXML
    private Button emailUpdateButton;

    @FXML
    void BackFunc(ActionEvent event) {
        try {
            switchScene(event, "CustOptions.fxml", "UpdateInfo");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void EmailUpdate(ActionEvent event) {

    }

    @FXML
    void PassUpdate(ActionEvent event) {

    }

    @FXML
    void PhoneNumUpdate(ActionEvent event) {

    }
}
