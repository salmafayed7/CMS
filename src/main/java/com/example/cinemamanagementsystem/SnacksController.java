package com.example.cinemamanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class SnacksController {

    @FXML
    private Button OrderButton;

    @FXML
    private ListView<Snack> menuList;

    @FXML
    private TextArea yourorder;

    @FXML
    private Button confirmButton;

    @FXML
    private Label totalPricelabel;

    String query = "SELECT * FROM snacks";

    ArrayList<Snack> selectedSnacks = new ArrayList<>();

    double totalprice;

    @FXML
    void Order(ActionEvent event) {
        if(event.getSource() == OrderButton) {
            addToOrder();
        }
    }

    @FXML
    void Confirm(ActionEvent event) {
        if(event.getSource() == confirmButton) {
            totalPricelabel.setText(String.format("%.2f", totalprice));
            totalPricelabel.setVisible(true);
        }
    }

    public void initialize() {
        ArrayList<Snack> snacks  = Jdbc.GetSnack(query);
        for (Snack snack : snacks) {
            System.out.println(snack.toString());
        }
        ObservableList<Snack> observableSnack = FXCollections.observableArrayList(snacks);
        System.out.println("Number of Snacks: " + observableSnack.size());
        menuList.setItems(observableSnack);
        System.out.println("Snacks loaded: " + observableSnack.size()); // Check size
        for (Snack snack : observableSnack) {
            System.out.println("snack: " + snack.toString());
        }
    }

    @FXML
    public void addToOrder() {
        Snack selectedsnack = menuList.getSelectionModel().getSelectedItem();
        if (selectedsnack != null) {
            yourorder.appendText(selectedsnack.toString() + "\n");
            selectedSnacks.add(selectedsnack);
            totalprice += selectedsnack.getSPrice();
        }
    }
}
