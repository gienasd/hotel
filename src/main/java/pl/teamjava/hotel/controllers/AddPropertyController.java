package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pl.teamjava.hotel.models.PlaceModel;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.dao.ManagmentDao;
import pl.teamjava.hotel.models.dao.impl.ManagmentDaoImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPropertyController implements Initializable {

    @FXML
    TextField textName, textCity, textRegion;

    @FXML
    ChoiceBox splitCategory;

    @FXML
    CheckBox checkboxWifi, checkboxPets, checkboxPool, checkboxSpa;

    @FXML
    Button buttonBack, buttonLogout, buttonAdd;

    private ManagmentDao managmentDao = new ManagmentDaoImpl();
    private Utils utils = new Utils();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonBack.setOnMouseClicked(e-> utils.switchView(buttonBack, "propertiesManagmentView.fxml"));
        buttonLogout.setOnMouseClicked(e-> utils.switchView(buttonLogout, "mainView.fxml"));

        buttonAdd.setOnMouseClicked(e-> addProperty());

        splitCategory.getItems().addAll("Hotel", "Camping", "Pole namiotowe");
    }

    private void addProperty() {
        if(managmentDao.addProperty(new PlaceModel(
                textName.getText(), textCity.getText(), textRegion.getText(),
                splitCategory.getValue().toString(), checkboxWifi.isSelected(), checkboxPool.isSelected(),
                checkboxSpa.isSelected(), checkboxPets.isSelected()))){
            Utils.createSimpleDialog("Dodawanie...", "", "Udało się dodać własność!");
        }else{
            Utils.createSimpleDialog("Dodawanie...", "", "Coś poszło nie tak :(");
        }
        textName.clear();
        textRegion.clear();
        textCity.clear();
        checkboxPool.setSelected(false);
        checkboxSpa.setSelected(false);
        checkboxPets.setSelected(false);
        checkboxWifi.setSelected(false);
    }
}
