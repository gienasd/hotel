package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import pl.teamjava.hotel.models.Utils;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomManagmentViewController implements Initializable {

    @FXML
    Button buttonAdd, buttonRemove, buttonEdit, buttonLogout, buttonBack;

    private Utils utils = new Utils();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonAdd.setOnMouseClicked(e-> utils.switchView(buttonAdd, "addRoom.fxml"));
        buttonEdit.setOnMouseClicked(e-> utils.switchView(buttonEdit, "editRoom.fxml"));
        buttonRemove.setOnMouseClicked(e-> utils.switchView(buttonRemove, "deleteRoom.fxml"));
        buttonBack.setOnMouseClicked(e-> utils.switchView(buttonBack, "managmentView.fxml"));
        buttonLogout.setOnMouseClicked(e-> utils.switchView(buttonLogout, "mainView.fxml"));
    }
}
