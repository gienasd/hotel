package pl.teamjava.hotel.controllers;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import pl.teamjava.hotel.models.Utils;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoggedGuestViewController implements Initializable {

    @FXML
    Label labelStatus;


    public void handler() {

        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("loginView.fxml"));
            primaryStage.setTitle("Hotel ver: " + Utils.VERSION);
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.setScene(new Scene(root, 600, 430));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initialize(URL location, ResourceBundle resources) {
//        Platform.setImplicitExit(false);
//        labelStatus.getParent().getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent event) {
//                event.consume();
//            }
////            @Override
////            public void handle(WindowEvent event) {
////                try {
////                    handler();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });

//
//        });


    }
}