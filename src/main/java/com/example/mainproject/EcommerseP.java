package com.example.mainproject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class EcommerseP extends Application {
   // UserInteeface userInterface=new UserInteeface();
    private Label homeLabel;
    UserInterface userInterface=new UserInterface();

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(userInterface.createContent(), 800, 600);
      //  homeLabel=new Label();
     //   homeLabel.setText("Ecommerce Application");
        //homeLabel.setAlignment(Pos.CENTER);
        stage.setTitle("Ecommerce Application");



        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}