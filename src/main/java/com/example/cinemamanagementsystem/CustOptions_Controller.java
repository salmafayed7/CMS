package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class CustOptions_Controller extends Controller {

    @FXML
    private Button bookingBtn;

    @FXML
    private Button historyBtn;

    @FXML
    private Button infoBtn;

    @FXML
    private Button logoutBtn;
    @FXML
    private Label nameLabel;

    @FXML
    private Label pointsLabel;

    @FXML
    private Button moviesbtn;
    @FXML
    private Button SnacksBtn;


    public void setup(){
        String userName = Jdbc.getUserName(userid);
        if (userName != null) {
            nameLabel.setText(userName);
        } else {
            nameLabel.setText("User not found");
        }
        int userPoints = Jdbc.getUserPoints(userid);
        if (userPoints != -1) {
            pointsLabel.setText(String.valueOf(userPoints));
        } else {
            nameLabel.setText("User not found");
        }
    }

    @FXML
    void logout(ActionEvent event) {
        ButtonType result = infoBox("Are you sure you want to logout?", "Confirm Logout");
        if (result == ButtonType.OK) {
            try {
                switchScene(event, "Customer_Login.fxml", "Login", userid);
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
        else if (result == ButtonType.CANCEL) {
            return;
        }

    }

    @FXML
    void newBooking(ActionEvent event) {
        try {
            if(event.getSource() == bookingBtn)
                switchScene(event, "NewBooking.fxml", "New Booking", userid);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateInfo(ActionEvent event) {
        try {
            switchScene(event,"UpdateInfo.fxml", "Update Info", userid);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void viewHistory(ActionEvent event) {
        try {
            switchScene(event, "viewHistory.fxml", "View History",userid);

        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void viewMovies(ActionEvent event) {
        try {
            if(event.getSource() == moviesbtn){
                switchScene(event, "View_Movies.fxml", "View Movies",userid);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void getSnacks(ActionEvent event) {
        try {
            if(event.getSource() == SnacksBtn){
                switchScene(event,"Snacks.fxml","Snacks", userid);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
