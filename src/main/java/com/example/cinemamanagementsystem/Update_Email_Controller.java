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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Update_Email_Controller extends Controller {
    @FXML
    private Button CancelButton;

    @FXML
    private TextField NewEmailTF;

    @FXML
    private TextField OldEmailTF;

    @FXML
    private Button UpdateButton;

    @FXML
    public void initialize() {
        setEnterKeyEvent(OldEmailTF, NewEmailTF);
        setEnterKeyEvent(NewEmailTF, UpdateButton);
    }

    private String Newemail;
    private String Oldemail;
    private String checkOldEmailQuery = "SELECT email from person where id = ?";
    private String updateQuery = "UPDATE person SET email = ? WHERE id = ?";
    private String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private Pattern pattern = Pattern.compile(emailRegex);

    @FXML
    void CancelFun(ActionEvent event) {
        try {
            if (event.getSource() == CancelButton) {
                switchScene(event, "UpdateInfo.fxml", "Update Info", userid);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void UpdateEmailFunc(ActionEvent event) {
        Window owner = UpdateButton.getScene().getWindow();
        Newemail = NewEmailTF.getText();
        Oldemail = OldEmailTF.getText();

        if (event.getSource() == UpdateButton) {
            if (Newemail.isEmpty() || Oldemail.isEmpty()) {
                showAlert(owner);
            }
            else {
                Matcher matcher = pattern.matcher(Oldemail);
                Matcher match = pattern.matcher(Newemail);
                if (!matcher.matches()) {
                    showAlert(Alert.AlertType.ERROR, owner, "Incorrect format", "Please enter a valid email address.");
                }
                else if (!match.matches()) {
                    showAlert(Alert.AlertType.ERROR, owner, "Incorrect format", "Please enter a valid email address.");
                }
                else if (Newemail.equals(Oldemail)) {
                    showAlert(owner, "Both emails are the same. Please enter a different email address.");
                }
                else {
                    String oldEmailFromDB = Jdbc.getOldEmail(checkOldEmailQuery, userid);
                    if (oldEmailFromDB.equals(Oldemail)) {
                        boolean flag = Jdbc.UpdateEmail(Newemail, updateQuery, userid);
                        if (flag) {
                            try {
                                infoBox("Your email address has been updated in our system.", "Email Updated Successfully", " Update Successful");
                                switchScene(event, "UpdateInfo.fxml", "Update Info", userid);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            showAlert(Alert.AlertType.ERROR, owner, "Error", "An error occurred.");
                        }
                    }
                    else {
                        showAlert(Alert.AlertType.ERROR, owner, "Email doesn't exist", "This email does not exist in our system.");
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
