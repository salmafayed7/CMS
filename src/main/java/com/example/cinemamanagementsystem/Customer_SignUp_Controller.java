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
public class Customer_SignUp_Controller extends Controller {

    @FXML
    private Button ConfirmButton;

    @FXML
    private Button CancelButton;

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
    private Button LoginButton;

    @FXML
    public void initialize() {
        setEnterKeyEvent(NameTF, EmailTF);
        setEnterKeyEvent(EmailTF, PasswordTF);
        setEnterKeyEvent(PasswordTF, ConfirmPasswordTF);
        setEnterKeyEvent(ConfirmPasswordTF, PhoneNumberTF);
        setEnterKeyEvent(PhoneNumberTF, ConfirmButton);
    }

    @FXML
    void ButtonAction(ActionEvent event){
        Window owner = ConfirmButton.getScene().getWindow();
        String email = EmailTF.getText();
        String phoneNumber = PhoneNumberTF.getText();
        String name = NameTF.getText();
        String password = PasswordTF.getText();
        String query = "Insert INTO PERSON (email, password, name, phone) VALUES (?,?,?,?)";

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);

        String phoneRegex = "^\\+?[0-9]{1,4}[-.\\s]?[0-9]{1,4}[-.\\s]?[0-9]{1,4}[-.\\s]?[0-9]{1,4}$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        Matcher phoneMatcher = phonePattern.matcher(phoneNumber);

        if (name.isEmpty()) {
            showAlert(AlertType.ERROR, owner,"Empty field","Name field is empty.");
        }
        else if (email.isEmpty()) {
            showAlert(AlertType.ERROR, owner, "Empty field", "Email field empty");
        }
        else if (!emailMatcher.matches()) {
            showAlert(AlertType.ERROR, owner, "Invalid email address", "Please enter a valid email address.");
        }
        else if (password.isEmpty()) {
            showAlert(AlertType.ERROR, owner,"Empty field","Password field is empty.");
        }
        else if (ConfirmPasswordTF.getText().isEmpty()) {
            showAlert(AlertType.ERROR, owner,"Empty field","Confirm Password field is empty.");
        }
        else if (!password.equals(ConfirmPasswordTF.getText())) {
            showAlert(AlertType.ERROR, owner,"Empty field","Passwords don't match.");
        }
        else if (password.length() < 8 || ConfirmPasswordTF.getText().length() < 8) {
            showAlert(Alert.AlertType.ERROR, owner, "Password too short", "Password must be at least 8 characters long.");
        }
        else if (phoneNumber.isEmpty()) {
            showAlert(AlertType.ERROR, owner,"Empty field","Phone Number field is empty.");
        }
        else if (!phoneMatcher.matches()) {
            showAlert(AlertType.ERROR, owner,"Invalid phone number", "Please enter a valid phone number.");
        }
        else {
            userid = Jdbc.signUp(email, password, name, phoneNumber, query);
            if (userid != null) {
                try {
                    infoBox("Your account has been successfully added to our system!",  "Signup successful", "Success!");
                    switchScene(event, "CustOptions.fxml", "Customer Options", userid);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            else {
                showAlert(AlertType.ERROR, owner, "Account already exists", "An account with this information already exists.");
                try {
                    switchScene(event, "Customer_Login.fxml", "Login");
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setEnterKeyEvent(TextField currentField, TextField nextField) {
        currentField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                nextField.requestFocus();
                event.consume();
            }
        });
    }

    private void setEnterKeyEvent(TextField currentField, Button nextButton) {
        currentField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                nextButton.requestFocus();
                event.consume();
            }
        });
    }

    @FXML
    void CancelAction(ActionEvent event) {
        try{
            switchScene(event,"Customer_Login.fxml", "Login", userid);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
