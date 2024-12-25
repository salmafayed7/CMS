package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Customer_SignUp_Controller {

    @FXML
    private Button ConfirmButton;

    @FXML
    private TextField ConfirmPasswordTF;

    @FXML
    private TextField EmailTF;

    @FXML
    private TextField NameTF;

    @FXML
    private TextField PasswordTF;

    @FXML
    private TextField PhoneNumberTF;

    @FXML
    void ButtonAction(ActionEvent event) {
        if(EmailTF.getText().equals("")){

        }
    }
}
