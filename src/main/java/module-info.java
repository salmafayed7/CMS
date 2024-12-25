module com.example.cinemamanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.cinemamanagementsystem to javafx.fxml;
    exports com.example.cinemamanagementsystem;
}