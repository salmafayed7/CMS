package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class Update_Info_Controller extends Controller{

 
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
        try{
            if(event.getSource() == BackButton) {
                switchScene(event,"CustOptions.fxml","Customer Options", userid);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void EmailUpdate(ActionEvent event) {
        try {
            if(event.getSource() == emailUpdateButton) {
              
                switchScene(event,"UpdateEmail.fxml","Update Email", userid);

            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void PassUpdate(ActionEvent event) {
        try{
            if(event.getSource() == PasswordUpdateButton) {
                switchScene(event,"UpdatePassword.fxml","Update Password", userid);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void PhoneNumUpdate(ActionEvent event) {
        try {
            if(event.getSource() == PhoneUpdateButtom){
                switchScene(event,"UpdatePhone.fxml","Update Phone", userid);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
