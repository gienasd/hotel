package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pl.teamjava.hotel.models.Utils;

import java.net.URL;
import java.util.ResourceBundle;

public class EditRoomController implements Initializable {

    //TODO: Dokończyć

    @FXML
    ChoiceBox choiceCategory, choiceCapacity;

    @FXML
    Button buttonEdit, buttonBack, buttonLogout;

    @FXML
    ListView<String> listRoom;

    @FXML
    ScrollBar scrollRoom;

    @FXML
    TextField textName, textPrice;

    private Utils utils = new Utils();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonBack.setOnMouseClicked(e-> utils.switchView(buttonBack, "roomManagmentView.fxml"));
        buttonLogout.setOnMouseClicked(e-> utils.switchView(buttonLogout, "mainView.fxml"));

    }
}
