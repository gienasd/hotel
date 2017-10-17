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

public class PropertiesManagmentViewController implements Initializable {

    @FXML
    Button buttonAdd, buttonRemove, buttonEdit, buttonBack, buttonLogout;

    private Utils utils = new Utils();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonAdd.setOnMouseClicked(e-> utils.switchView(buttonAdd, "addProperty.fxml"));
        buttonEdit.setOnMouseClicked(e-> utils.switchView(buttonEdit, "editProperty.fxml"));
        buttonRemove.setOnMouseClicked(e-> utils.switchView(buttonRemove, "deleteProperty.fxml"));
        buttonBack.setOnMouseClicked(e-> utils.switchView(buttonBack, "managmentView.fxml"));
        buttonLogout.setOnMouseClicked(e-> utils.switchView(buttonLogout, "mainView.fxml")); //TODO wylogowanie
    }
}
