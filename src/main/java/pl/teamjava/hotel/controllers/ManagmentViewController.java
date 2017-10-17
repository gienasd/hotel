package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import pl.teamjava.hotel.models.Utils;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagmentViewController implements Initializable {

    @FXML
    Button buttonMainPage,buttonProperty, buttonRoom, buttonReservation, buttonBlockedList, buttonEdit;

    private Utils utils = new Utils();

    public void initialize(URL location, ResourceBundle resources) {

        buttonProperty.setOnMouseClicked(e -> utils.switchView(buttonProperty, "propertiesManagmentView.fxml"));
        buttonMainPage.setOnMouseClicked(e -> utils.switchView(buttonMainPage, "mainView.fxml"));
        buttonBlockedList.setOnMouseClicked(e -> utils.switchView(buttonBlockedList, "blockedListView.fxml"));
        buttonRoom.setOnMouseClicked(e -> utils.switchView(buttonRoom, "roomManagmentView.fxml"));
        buttonReservation.setOnMouseClicked(e -> utils.switchView(buttonReservation, "reservationView.fxml"));
        // buttonEdit.setOnMouseClicked(e -> switchView(buttonEdit, ?)); TODO
    }
}
