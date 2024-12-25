package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class Customer_Login_Controller {

    @FXML
    private TextField emailTF;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passTF;

    @FXML
    void loginAction(ActionEvent event) {
        Window owner = loginButton.getScene().getWindow();
        if(emailTF.getText().isEmpty()){

        }

    }

}
