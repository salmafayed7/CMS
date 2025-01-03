package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;
import javafx.scene.control.Alert;

import java.io.IOException;

public class UpdatePasswordController extends Controller {

    @FXML
    private Button cancelbtn;

    @FXML
    private TextField newPasswordtf;

    @FXML
    private TextField oldPasswordtf;

    @FXML
    private Button updatebtn;

    private String oldPassQuery = "SELECT password FROM person WHERE id = ?";

    @FXML
    public void initialize() {
        setEnterKeyEvent(oldPasswordtf, newPasswordtf);
        setEnterKeyEvent(newPasswordtf, updatebtn);
    }

    @FXML
    void UpdatepassAction(ActionEvent event) {
        Window owner = updatebtn.getScene().getWindow();
        String newP = newPasswordtf.getText();
        String oldP = oldPasswordtf.getText();
        String query = "UPDATE person SET password = ? WHERE id = ?";
        if (oldP.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Empty field", " Please enter the old password.");
        }
        else if (newP.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Empty field", " Please enter the new password.");
        }
        else if (newP.length() < 8) {
            showAlert(Alert.AlertType.ERROR, owner, "Password too short", "Password must be at least 8 characters long.");
        }
        else if (oldP.equals(newP)) {
            showAlert(owner, "Both passwords are the same. Please enter a new password.");
        }
        else {
            String oldPassFromDB = Jdbc.getOldPass(oldPassQuery, userid);
            if (oldPassFromDB.equals(oldP)){
                boolean flag = Jdbc.Updatepassword(newP, query, userid);
                if (flag) {
                    try {
                        infoBox("Your password has been updated.","Password updated successfully!","Update successful!");
                        switchScene(event,"UpdateInfo.fxml","Update Info", userid);
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else {
                    showAlert(Alert.AlertType.ERROR, owner, "Error", "An error occurred.");
                }
            }
            else {
                showAlert(Alert.AlertType.ERROR, owner, "Incorrect password", "The password you entered is not in our system.");
            }
        }
    }

    @FXML
    void cancelPasswordAction(ActionEvent event) {
        try{
            switchScene(event,"UpdateInfo.fxml","UpdateInfo", userid);
        }catch (IOException e){
            e.printStackTrace();
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
