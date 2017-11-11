package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;
import pl.teamjava.hotel.models.Session;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.dao.UserDao;
import pl.teamjava.hotel.models.dao.impl.UserDaoImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagmentViewController implements Initializable {
    UserDao userDao = new UserDaoImpl();
    @FXML
    Button buttonMainPage, buttonProperty, buttonRoom, buttonReservation, buttonBlockedList,buttonMailerOpen, buttonEdit;
    @FXML
    Label labelLogedUser;
    private Session userSession = Session.getInstance();
    private Utils utils = new Utils();

    public void initialize(URL location, ResourceBundle resources) {

        buttonProperty.setOnMouseClicked(e -> utils.switchView(buttonProperty, "propertiesManagmentView.fxml"));
        buttonMainPage.setOnMouseClicked(e -> utils.switchView2(buttonMainPage, "mainView.fxml", 600, 600, true, StageStyle.DECORATED,true));
        buttonBlockedList.setOnMouseClicked(e -> utils.switchView(buttonBlockedList, "blockedListView.fxml"));
        buttonRoom.setOnMouseClicked(e -> utils.switchView(buttonRoom, "roomManagmentView.fxml"));
        buttonReservation.setOnMouseClicked(e -> utils.switchView(buttonReservation, "reservationView.fxml"));
        labelLogedUser.setText("Zalogowany : " + userSession.getUsername());
        buttonMailerOpen.setOnMouseClicked(e->Utils.switchView2(buttonMailerOpen,"mailerView.fxml",240,550,true, StageStyle.DECORATED,false));
        buttonMainPage.setOnMouseClicked(e -> Utils.logoutToMainPage(buttonMainPage));
        // buttonEdit.setOnMouseClicked(e -> switchView(buttonEdit, ?)); TODO
    }
}
