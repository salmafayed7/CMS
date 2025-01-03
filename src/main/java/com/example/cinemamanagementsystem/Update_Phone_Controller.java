package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

//import java.awt.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;

public class Update_Phone_Controller extends Controller {

    @FXML
    private Button CancelButton;

    @FXML
    private Button UpdateButton;

    @FXML
    private TextField NewNumberTF;

    @FXML
    private TextField OldNumberTF;

    String OldPhoneNumber ;
    String NewPhoneNumber;


    String phoneRegex = "^\\+?[0-9]{1,4}[-.\\s]?[0-9]{1,4}[-.\\s]?[0-9]{1,4}[-.\\s]?[0-9]{1,4}$";
    Pattern pattern = Pattern.compile(phoneRegex);
    private String updateQuery = "UPDATE person SET phone = ? WHERE id = ?";
    private String getOldPhoneNumber = "SELECT phone FROM person WHERE id = ?";

    @FXML
    public void initialize() {
        setEnterKeyEvent(OldNumberTF, NewNumberTF);
        setEnterKeyEvent(NewNumberTF, UpdateButton);
    }

    @FXML
    void CancelFunc(ActionEvent event) {
        try{
            if (event.getSource() == CancelButton) {
                switchScene(event,"UpdateInfo.fxml","Update Info", userid);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void UpdateFunc(ActionEvent event) {
        Window owner = UpdateButton.getScene().getWindow();
         OldPhoneNumber = OldNumberTF.getText();
         NewPhoneNumber = NewNumberTF.getText();
        if (event.getSource() == UpdateButton) {
            if (OldPhoneNumber.isEmpty() || NewPhoneNumber.isEmpty()) {
                showAlert(owner);
            }
            else {
                Matcher match = pattern.matcher(OldPhoneNumber);
                Matcher matcher = pattern.matcher(NewPhoneNumber);
                 if (!matcher.matches()) {
                    showAlert(owner, "Please enter a valid phone number.");
                 }
                 else if (!match.matches()) {
                    showAlert(owner, "Please enter a valid phone number.");
                 }
                 else if (NewPhoneNumber.equals(OldPhoneNumber)) {
                    showAlert(owner, "Both phone numbers are the same. Please enter a new phone number.");
                 }
                 else if (NewPhoneNumber.length() > 11){
                     showAlert(Alert.AlertType.ERROR, owner, "Phone number too long.", "Please enter a valid phone number.");
                 }
                 else {
                     String oldPhoneFromDB = Jdbc.getOldPhone(getOldPhoneNumber, userid);
                     if (oldPhoneFromDB.equals(OldPhoneNumber)) {
                         boolean flag = Jdbc.UpdatePhoneNumber(NewPhoneNumber, updateQuery, userid);
                         if (flag) {
                             try {
                                 infoBox("Your phone number has been updated in our system.", "Phone Number Updated Successfully", " Update Successful");
                                 switchScene(event, "UpdateInfo.fxml", "Update Info", userid);
                             }
                             catch (IOException e) {
                                 e.printStackTrace();
                             }
                         }
                         else {
                             showAlert(Alert.AlertType.ERROR, owner, "Error", "An error occurred.");
                         }
                     }
                     else {
                         showAlert(Alert.AlertType.ERROR, owner, "Phone number doesn't exist", "This phone number does not exist in our system.");
                     }
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
}
