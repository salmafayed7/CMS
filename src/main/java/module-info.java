module com.example.cinemamanagementsystem {
    requires javafx.fxml;
    requires com.google.gson;
    requires java.sql;
    requires javafx.web;
    requires java.net.http;
    requires java.datatransfer;


    opens com.example.cinemamanagementsystem to javafx.fxml;
    exports com.example.cinemamanagementsystem;
}