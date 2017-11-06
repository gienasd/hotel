package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pl.teamjava.hotel.models.Session;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.dao.UserDao;
import pl.teamjava.hotel.models.dao.impl.UserDaoImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class PropertiesManagmentViewController implements Initializable {
UserDao userDao=new UserDaoImpl();
    @FXML
    Button buttonAdd, buttonRemove, buttonEdit, buttonBack, buttonLogout;
    @FXML
    Label labelLogedUser;

    private Utils utils = new Utils();
    private Session userSession = Session.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonAdd.setOnMouseClicked(e-> utils.switchView(buttonAdd, "addProperty.fxml"));
        buttonEdit.setOnMouseClicked(e-> utils.switchView(buttonEdit, "editProperty.fxml"));
        buttonRemove.setOnMouseClicked(e-> utils.switchView(buttonRemove, "deleteProperty.fxml"));
        buttonBack.setOnMouseClicked(e-> utils.switchView(buttonBack, "managmentView.fxml"));
        buttonLogout.setOnMouseClicked(e-> Utils.logoutToMainPage(buttonLogout));
        labelLogedUser.setText("Zalogowany : "+userSession.getUsername());
        buttonLogout.setOnMouseClicked(e-> Utils.logoutToMainPage(buttonLogout));
    }
}
