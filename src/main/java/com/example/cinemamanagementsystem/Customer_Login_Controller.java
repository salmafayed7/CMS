package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;

import java.io.IOException;


public class Customer_Login_Controller extends Controller{

    @FXML
    private TextField emailTF;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passTF;

    @FXML
    private Button signupBtn;



    @FXML
    void loginAction(ActionEvent event) {
        Window owner = loginButton.getScene().getWindow();
        if(emailTF.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"Error!","Please enter your email!");
            return;
        }
        if(passTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter your password!");
            return;
        }
        String email= emailTF.getText();
        String password= passTF.getText();
        String query= "SELECT * FROM PERSON WHERE Email=? and Password=?";
        System.out.println("Attempting login for: " + email); // Debugging line
        userid=Jdbc.validateLogin(email, password, query);
        if(userid == null){
            showAlert(Alert.AlertType.ERROR,owner,"Error!","Invalid username or password!");
            return;
        }else {
            System.out.println("Login successful, User ID: " + userid); // Debugging line
            try{
                switchScene(event,"CustOptions.fxml", "CustOptions",userid);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    private void setEnterKeyEvent(TextField currentField, TextField nextField) {
        currentField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                nextField.requestFocus();
                event.consume(); // Consume the event
            }
        });
    }


    private void setEnterKeyEvent(TextField currentField, Button nextButton) {
        currentField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                nextButton.requestFocus();
                event.consume(); // Consume the event
            }
        });
    }@FXML
    public void initialize() {
        // Set up Enter key event handling for text fields
        setEnterKeyEvent(emailTF, passTF);
        setEnterKeyEvent(passTF,loginButton);
    }
    @FXML
    void signupAction(ActionEvent event) {
        try {
            switchScene(event, "Customer_SignUp.fxml", "Customer_SignUp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}