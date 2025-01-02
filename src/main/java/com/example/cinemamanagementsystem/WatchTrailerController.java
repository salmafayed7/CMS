package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.*;

import java.io.IOException;

public class WatchTrailerController extends Controller {
 public String moviename;
    @FXML
   private WebView webview;
    @FXML
    private Button backButton;

 public void setup() {
  // Get the WebEngine from the WebView
  WebEngine webEngine = webview.getEngine();

  String url=MovieTrailerFetcher.getTrailerUrlByName(moviename);
     System.out.println(url);
     System.out.println(moviename);
  webEngine.load(url);
 }

    @FXML
    void backAction(ActionEvent event) {
        try {
            switchScene(event, "View_Movies.fxml", "View_Movies",userid);
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }


}
