package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Customer_SignUp_Controller extends Controller{

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
    void ButtonAction(ActionEvent event){
        Window owner = ConfirmButton.getScene().getWindow();
        String email = EmailTF.getText();
        String phoneNumber = PhoneNumberTF.getText();
        String name = NameTF.getText();
        String password = PasswordTF.getText();
        //make sure of the feilds names in db!!!!!!!!!!!!
        String query="Insert INTO PERSON (email, password, name, phone) VALUES (?,?,?,?) RETURNING id";
        // Regular expression for validating email format
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(emailRegex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(email);


        // Regex for validating phone numbers
        String phoneRegex = "^\\+?[0-9]{1,4}[-.\\s]?[0-9]{1,4}[-.\\s]?[0-9]{1,4}[-.\\s]?[0-9]{1,4}$";

        // Create a Pattern object
        Pattern pat = Pattern.compile(phoneRegex);

        // Create a Matcher object
        Matcher match = pattern.matcher(phoneNumber);

        if(email.equals("")) {
            showAlert(AlertType.ERROR, owner, "Customer_SignUp", "Email field empty");
        }
        // Check if the email matches the pattern
        else if (!matcher.matches()) {
            // If the email is not in a valid format, show an error
            showAlert(owner, "Please enter a valid email address.");
        } else if(name.equals("")){
            showAlert(AlertType.ERROR,owner,"Customer_SignUp","Name field empty");
        }else if(password.equals("")){
            showAlert(AlertType.ERROR,owner,"Customer_SignUp","Password field empty");
        } else if (ConfirmPasswordTF.getText().equals("")) {
            showAlert(AlertType.ERROR,owner,"Customer_SignUp","Confirm Password field empty");
        } else if ( !password.equals(ConfirmPasswordTF.getText())) {
            showAlert(AlertType.ERROR,owner,"Customer_SignUp","Password doesnt match");
        } else if ( phoneNumber.equals("")) {
            showAlert(AlertType.ERROR,owner,"Customer_SignUp","Phone Number field empty");
        }// Check if the phone number matches the pattern
        else if (!matcher.matches()) {
            // If the phone number is not in a valid format, show an error
            showAlert(owner, "Please enter a valid phone number.");
        }else{
            userid=Jdbc.signUp(email, password,name,phoneNumber,query);
            if(userid != null){
                try {
                    switchScene(event, "CustOptions.fxml", "CustOptions",userid);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }else{
                showAlert(owner);
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
        setEnterKeyEvent(NameTF, EmailTF);
        setEnterKeyEvent(EmailTF, PasswordTF);
        setEnterKeyEvent(PasswordTF, ConfirmPasswordTF);
        setEnterKeyEvent(ConfirmPasswordTF, PhoneNumberTF);
        setEnterKeyEvent(PhoneNumberTF, ConfirmButton);
    }


}
