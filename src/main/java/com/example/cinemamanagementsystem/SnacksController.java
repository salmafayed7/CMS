package com.example.cinemamanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.IOException;
import java.util.ArrayList;

public class SnacksController extends Controller {

    @FXML
    private Button OrderButton;

    @FXML
    private ListView<Snack> menuList;

    @FXML
    private TextArea yourorder;

    @FXML
    private Button confirmButton;

    String query = "SELECT * FROM snacks";

    ArrayList<Snack> selectedSnacks = new ArrayList<>();

    double totalprice = 0;

    @FXML
    private ImageView imageview;

    @FXML
    private Button ViewImagebtn;

    @FXML
    private Button backButton;

    @FXML
    void Order(ActionEvent event) {
        if(event.getSource() == OrderButton) {
            addToOrder();
        }
    }

    @FXML
    void Confirm(ActionEvent event) {
        if(event.getSource() == confirmButton) {
            ButtonType result = infoBox("Your total price is " + totalprice,"Confirmation");
            try{
                if(result == ButtonType.OK) {
                    switchScene(event,"CustOptions.fxml","Customer Options",userid);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void initialize() {
        menuList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ArrayList<Snack> snacks  = Jdbc.GetSnack(query);
        for (Snack snack : snacks) {
            System.out.println(snack.toString());
        }
        ViewImagebtn.setDisable(true);
        menuList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ViewImagebtn.setDisable(newValue == null);
        });
        ObservableList<Snack> observableSnack = FXCollections.observableArrayList(snacks);
        menuList.setItems(observableSnack);
    }

    private void updateSnackImage(Snack snack) {
        try{
            String imagePath = snack.getImagePath();
            Image image = new Image(getClass().getResourceAsStream("/" + imagePath));
            imageview.setImage(image);
        } catch (Exception e) {
            imageview.setImage(null);
        }

    }

    @FXML
    public void addToOrder() {
        ObservableList<Snack> selectedSnackss = menuList.getSelectionModel().getSelectedItems();
        if (!selectedSnackss.isEmpty()) {
            for (Snack snack : selectedSnackss) {
                yourorder.appendText(snack.toString() + "\n");
                selectedSnacks.add(snack);
                totalprice += snack.getSPrice();
            }
        }
    }


    @FXML
    void ViewImage(ActionEvent event) {
        Snack selected= menuList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            updateSnackImage(selected);
        } else {
            infoBox("Please select a snack to view its image.",null,"No Selection");
        }
    }

    @FXML
    void Back(ActionEvent event) {
        try {
            switchScene(event, "CustOptions.fxml", "Customer Options", userid);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
