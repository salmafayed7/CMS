module com.example.cinemamanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.java;


    opens com.example.cinemamanagementsystem to javafx.fxml;
    exports com.example.cinemamanagementsystem;
}