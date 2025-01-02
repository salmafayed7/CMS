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

   // @FXML
    //private Label totalPricelabel;

    String query = "SELECT * FROM snacks";

    ArrayList<Snack> selectedSnacks = new ArrayList<>();

    double totalprice = 0;

    @FXML
    private ImageView imageview;

    @FXML
    private Button ViewImagebtn;

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
                    switchScene(event,"CustOptions.fxml","CustOptions",userid);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            /*totalPricelabel.setText(String.format("%.2f", totalprice));
            totalPricelabel.setVisible(true);
            yourorder.clear();
            selectedSnacks.clear();
          //  totalprice = 0;*/
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
        System.out.println("Number of Snacks: " + observableSnack.size());
        menuList.setItems(observableSnack);
        System.out.println("Snacks loaded: " + observableSnack.size()); // Check size
        for (Snack snack : observableSnack) {
            System.out.println("snack: " + snack.toString());
        }
    }

    private void updateSnackImage(Snack snack) {
        try{
            String imagePath = snack.getImagePath();
            Image image = new Image(getClass().getResourceAsStream("/" + imagePath)); // Use resource path
            imageview.setImage(image);
        } catch (Exception e) {
            System.err.println("Error loading image: " + snack.getImagePath());
            imageview.setImage(null); // Clear the image if there's an error
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
}
