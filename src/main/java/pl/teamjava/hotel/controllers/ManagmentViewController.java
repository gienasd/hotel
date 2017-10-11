package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.teamjava.hotel.models.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagmentViewController implements Initializable {

    @FXML
    Button buttonMainPage,buttonProperty, buttonRoom, buttonReservation, buttonBlockedList, buttonEdit;

    public void initialize(URL location, ResourceBundle resources) {

        buttonProperty.setOnMouseClicked(e -> switchView(buttonProperty, "propertiesManagmentView.fxml"));
        buttonMainPage.setOnMouseClicked(e -> switchView(buttonMainPage, "mainView.fxml"));
        buttonBlockedList.setOnMouseClicked(e -> switchView(buttonBlockedList, "blockedListView.fxml"));
        buttonRoom.setOnMouseClicked(e -> switchView(buttonRoom, "roomManagmentView.fxml"));
        buttonReservation.setOnMouseClicked(e -> switchView(buttonReservation, "reservationView.fxml"));
        // buttonEdit.setOnMouseClicked(e -> switchView(buttonEdit, ?)); TODO
    }

    public void switchView(Button button, String name){
        Stage stage = (Stage)button.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(name));
            stage.setScene(new Scene(root,600,420));
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
