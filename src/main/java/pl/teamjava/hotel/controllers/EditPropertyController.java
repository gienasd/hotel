package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pl.teamjava.hotel.models.Session;
import pl.teamjava.hotel.models.Utils;

import java.net.URL;
import java.util.ResourceBundle;

public class EditPropertyController implements Initializable {

    //TODO: dokończyć;

    @FXML
    Button buttonEdit, buttonBack, buttonLogout;

    @FXML
    ListView<String> listProperty;

    @FXML
    ScrollBar scrollProperty;

    @FXML
    TextField textName;
    @FXML
    Label labelLogedUser;
    @FXML
    CheckBox checkWifi, checkPets, checkPool, checkSpa;

    private Utils utils = new Utils();
    private Session userSession = Session.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonBack.setOnMouseClicked(e-> utils.switchView(buttonBack, "propertiesManagmentView.fxml"));
        buttonLogout.setOnMouseClicked(e-> Utils.logoutToMainPage(buttonLogout));
        labelLogedUser.setText("Zalogowany : "+userSession.getUsername());


    }
}
