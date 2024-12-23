module com.example.cinemamanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cinemamanagementsystem to javafx.fxml;
    exports com.example.cinemamanagementsystem;
}