package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.stage.Window;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Update_Email_Controller extends Controller{
    @FXML
    private Button CancelButton;

    @FXML
    private TextField NewEmailTF;

    @FXML
    private TextField OldEmailTF;

    @FXML
    private Button UpdateButton;



    Window owner = UpdateButton.getScene().getWindow();

    String Newemail=NewEmailTF.getText();
    String Oldemail=OldEmailTF.getText();
    String query="UPDATE person SET email=? WHERE id=?";

    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    // Create a Pattern object
    Pattern pattern = Pattern.compile(emailRegex);

    // Create a Matcher object
    Matcher matcher = pattern.matcher(Oldemail);

    Matcher match = pattern.matcher(Newemail);
    @FXML
    void CancelFun(ActionEvent event) {
        try{
            if (event.getSource() == CancelButton) {
                switchScene(event,"UpdateInfo.fxml","UpdateInfo",userid);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void UpdateEmailFunc(ActionEvent event) {
        if (event.getSource() == UpdateButton) {
            if(Newemail.isEmpty() || Oldemail.isEmpty()){
                showAlert(owner);
            }else if (!matcher.matches()) {
                // If the email is not in a valid format, show an error
                showAlert(Alert.AlertType.ERROR,owner,"UpdateEmail","Please enter a valid email address.");
            } else if (!match.matches()) {
                // If the email is not in a valid format, show an error
                showAlert(Alert.AlertType.ERROR,owner,"UpdateEmail","Please enter a valid email address.");
            } else if (Newemail.equals(Oldemail)) {
                showAlert(owner,"Both emails are the same. Please enter a different email address.");
            } else{
                boolean flag = Jdbc.UpdateEmail(Newemail,query,userid);
                try {
                    if(flag){
                        infoBox("Your email address has been updated in our system.", "Email Updated Successfully", " Update Successful");
                        switchScene(event, "UpdateInfo.fxml", "UpdateInfo", userid);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
