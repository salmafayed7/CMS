package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Controller {
    protected String userid;

    public void switchScene(ActionEvent event, String fxmlFile, String title, String userid) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setUserid(userid);
        if (controller instanceof CustOptions_Controller) {
            ((CustOptions_Controller) controller).setup();
        }

        if (controller instanceof ViewHistoryController) {
            ((ViewHistoryController) controller).setup();
        }

        Scene scene = new Scene(root );
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
    public void switchScene(ActionEvent event, String fxmlFile, String title, String userid, String moviename) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

        Parent root = loader.load();

        WatchTrailerController controller = loader.getController();
        controller.moviename=moviename;
        controller.setUserid(userid);
        controller.setup();

        Scene scene = new Scene(root );
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    public void switchScene(ActionEvent event, String fxmlFile, String title, String userid, double totalPrice) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();

        ConfirmNBController confirmNBController = loader.getController();
        confirmNBController.setUserid(userid);
        confirmNBController.setUp();
        confirmNBController.setTotalPrice(totalPrice);

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    public void switchScene(ActionEvent event, String fxmlFile, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        if (loader.getLocation() == null) {
            System.out.println("Error: Could not find FXML file at " + fxmlFile);
            return;
        }
        Parent root = loader.load();
        Controller controller = loader.getController();
        if (controller != null) {
            controller.setUserid(userid);
        }
        if (controller instanceof CustOptions_Controller) {
            ((CustOptions_Controller) controller).setup();
        }
        Scene scene=new Scene(root);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    public void setUserid(String userid) {
        this.userid = userid;
        System.out.println("User ID set: " + userid);
    }

    public void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.show();
    }

    public ButtonType infoBox(String infoMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        return result;
    }

    public void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.showAndWait();
    }

    public void showAlert(Window owner, String message) {
        showAlert(Alert.AlertType.INFORMATION, owner, "Success", message);
    }

    public void showAlert(Window owner) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Empty field");
        alert.setHeaderText(null);
        alert.setContentText("Please fill in all fields.");
        alert.initOwner(owner);
        alert.show();
    }
}