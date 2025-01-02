package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class stdHallController extends Controller {


    @FXML
    private Button A1;

    @FXML
    private Button A10;

    @FXML
    private Button A11;

    @FXML
    private Button A12;

    @FXML
    private Button A2;

    @FXML
    private Button A3;

    @FXML
    private Button A4;

    @FXML
    private Button A6;

    @FXML
    private Button A7;

    @FXML
    private Button A8;

    @FXML
    private Button A9;

    @FXML
    private Button B1;

    @FXML
    private Button B10;

    @FXML
    private Button B11;

    @FXML
    private Button B12;

    @FXML
    private Button B2;

    @FXML
    private Button B3;

    @FXML
    private Button B4;

    @FXML
    private Button B5;

    @FXML
    private Button B6;

    @FXML
    private Button B7;

    @FXML
    private Button B8;

    @FXML
    private Button B9;

    @FXML
    private Button C1;

    @FXML
    private Button C10;

    @FXML
    private Button C11;

    @FXML
    private Button C12;

    @FXML
    private Button C2;

    @FXML
    private Button C3;

    @FXML
    private Button C4;

    @FXML
    private Button C5;

    @FXML
    private Button C6;

    @FXML
    private Button C7;

    @FXML
    private Button C8;

    @FXML
    private Button C9;

    @FXML
    private Button D1;

    @FXML
    private Button D10;

    @FXML
    private Button D11;

    @FXML
    private Button D12;

    @FXML
    private Button D2;

    @FXML
    private Button D3;

    @FXML
    private Button D4;

    @FXML
    private Button D5;

    @FXML
    private Button D6;

    @FXML
    private Button D7;

    @FXML
    private Button D8;

    @FXML
    private Button D9;

    @FXML
    private Button E1;

    @FXML
    private Button E10;

    @FXML
    private Button E11;

    @FXML
    private Button E12;

    @FXML
    private Button E2;

    @FXML
    private Button E3;

    @FXML
    private Button E4;

    @FXML
    private Button E5;

    @FXML
    private Button E6;

    @FXML
    private Button E7;

    @FXML
    private Button E8;

    @FXML
    private Button E9;

    @FXML
    private Button F1;

    @FXML
    private Button F10;

    @FXML
    private Button F11;

    @FXML
    private Button F12;

    @FXML
    private Button F2;

    @FXML
    private Button F3;

    @FXML
    private Button F4;

    @FXML
    private Button F5;

    @FXML
    private Button F6;

    @FXML
    private Button F7;

    @FXML
    private Button F8;

    @FXML
    private Button F9;

    @FXML
    private Button G1;

    @FXML
    private Button G10;

    @FXML
    private Button G11;

    @FXML
    private Button G12;

    @FXML
    private Button G2;

    @FXML
    private Button G3;

    @FXML
    private Button G4;

    @FXML
    private Button G5;

    @FXML
    private Button G6;

    @FXML
    private Button G7;

    @FXML
    private Button G8;

    @FXML
    private Button G9;

    @FXML
    private Button H1;

    @FXML
    private Button H10;

    @FXML
    private Button H11;

    @FXML
    private Button H12;

    @FXML
    private Button H2;

    @FXML
    private Button H3;

    @FXML
    private Button H4;

    @FXML
    private Button H5;

    @FXML
    private Button H6;

    @FXML
    private Button H7;

    @FXML
    private Button H8;

    @FXML
    private Button H9;

    @FXML
    private Button J1;

    @FXML
    private Button J10;

    @FXML
    private Button J11;

    @FXML
    private Button J12;

    @FXML
    private Button J2;

    @FXML
    private Button J3;

    @FXML
    private Button J4;

    @FXML
    private Button J5;

    @FXML
    private Button J6;

    @FXML
    private Button J7;

    @FXML
    private Button J8;

    @FXML
    private Button J9;

    @FXML
    private Button K1;

    @FXML
    private Button K10;

    @FXML
    private Button K11;

    @FXML
    private Button K12;

    @FXML
    private Button K2;

    @FXML
    private Button K3;

    @FXML
    private Button K4;

    @FXML
    private Button K7;

    @FXML
    private Button K8;

    @FXML
    private Button K9;

    @FXML
    private Button L1;

    @FXML
    private Button L10;

    @FXML
    private Button L11;

    @FXML
    private Button L12;

    @FXML
    private Button L2;

    @FXML
    private Button L3;

    @FXML
    private Button L4;

    @FXML
    private Button L8;

    @FXML
    private Button L9;




    @FXML
    private Button confirmBtn;

    @FXML
    private Button cancelBtn;



    @FXML
    void cancelButton(ActionEvent event) {
        try {
            switchScene(event, "NewBooking.fxml", "New Booking", userid);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private static ArrayList<String> selectedSeats = new ArrayList<>();
    String seatName;

    public static ArrayList<String> sendSeats(){
        return selectedSeats;
    }


    @FXML
    void initialize() {
        /*ArrayList<Button> buttons = new ArrayList<>();
        for (Button button : buttons) {
            String SN = button.getText();
            String row = SN.substring(0, 1);
            String num = SN.substring(1);
            System.out.println("Extracted row: " + row + ", seat number: " + num);
            String query = "select IsAvailable from seat where row = ? and seatnum = ?";
            System.out.println("Executing query: " + query + " with row = " + row + " and seatnum = " + num);
            boolean isAvailable = Jdbc.checkAvailability(row, num, query);
            System.out.println("Seat availability: " + isAvailable);
            if(!isAvailable){
                button.setDisable(true);
                button.setStyle("-fx-background-color: grey;");
            }
        }*/
    }


    public void handleSeatSelection(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        Window window = clickedButton.getScene().getWindow();

        seatName = clickedButton.getId();

        String row = seatName.substring(0, 1);
        String num = seatName.substring(1);

        String query = "select IsAvailable from seat where row = ? and seatnum = ?";

        boolean isAvailable = Jdbc.checkAvailability(row, num, query);
        if (isAvailable) {
            if (selectedSeats.contains(row + num)) {
                selectedSeats.remove(row + num);
                if(row.equals("J") || row.equals("K") || row.equals("L"))
                    clickedButton.setStyle("-fx-background-color: black;");
                else clickedButton.setStyle("-fx-background-color: maroon");
            }
            else {
                selectedSeats.add(row + num);
                clickedButton.setStyle("-fx-background-color: green;");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, window, "Already reserved", "Seat is not available");
        }
    }

    public double totalPrice;
    @FXML
    void confirmButton(ActionEvent event) {
        final double standardPrice = 150.0;
        final double vipPrice = 200.0;
        totalPrice = 0.0;
        Window owner = confirmBtn.getScene().getWindow();
        if (!selectedSeats.isEmpty()) {
            for (String seat : selectedSeats) {
                String row = seat.substring(0, 1);
                if(row.equals("J") || row.equals("K") || row.equals("L")) {
                    totalPrice += vipPrice;
                }
                else {
                    totalPrice += standardPrice;
                }
            }
            try {
                switchScene(event, "ConfirmNB.fxml", "Confirm Booking", userid, totalPrice);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            showAlert(Alert.AlertType.ERROR, owner,"Empty Selection","You didn't choose any seats");
        }
    }


}

