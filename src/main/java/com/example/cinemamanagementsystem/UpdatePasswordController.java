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

    @FXML
    void UpdatepassAction(ActionEvent event) {
        Window owner = updatebtn.getScene().getWindow();
        String newp= newPasswordtf.getText();
        String oldp= oldPasswordtf.getText();
        String query="UPDATE person SET password = ? WHERE id = ?";
        if (oldp.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Empty field", " Please enter the new password.");
        }
        if (newp.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Empty field", " Please enter the old password.");
        }
        if (newp.length() < 8) {
            showAlert(Alert.AlertType.ERROR, owner, "Empty field",
                    "New password must be at least 8 characters long.");

        }
        boolean flag= Jdbc.Updatepassword(newp,query,userid);
        if(flag){
            try{
                infoBox("Your email has been update","Password update successfully!","successful update!");
                switchScene(event,"UpdateInfo.fxml","UpdateInfo",userid);
            }catch (IOException e){
                e.printStackTrace();
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
        setEnterKeyEvent(oldPasswordtf, newPasswordtf);
        setEnterKeyEvent(newPasswordtf, updatebtn);
    }

}
