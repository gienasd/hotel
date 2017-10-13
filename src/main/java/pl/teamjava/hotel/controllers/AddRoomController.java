package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.teamjava.hotel.models.PlaceModel;
import pl.teamjava.hotel.models.RoomModel;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.dao.ManagmentDao;
import pl.teamjava.hotel.models.dao.impl.ManagmentDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.text.ParsePosition;
import java.util.ResourceBundle;

public class AddRoomController implements Initializable {

    @FXML
    TextField textName, textPrice;

    @FXML
    ChoiceBox splitCategory, splitCapacity, splitPlaceName;

    @FXML
    Button buttonAdd, buttonLogout, buttonBack;

    private ManagmentDao managmentDao = new ManagmentDaoImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonBack.setOnMouseClicked(e-> switchView(buttonBack, "roomManagmentView.fxml"));
        buttonLogout.setOnMouseClicked(e-> switchView(buttonLogout, "mainView.fxml"));

        splitCategory.getItems().addAll("Apartament", "Pokój", "Namiot", "Domek kempingowy");
        splitCapacity.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8);
        splitPlaceName.getItems().addAll(managmentDao.placeNames());

        buttonAdd.setOnMouseClicked(e -> addRoom());
    }

    private void addRoom() {
        if(managmentDao.addRoom(new RoomModel(textName.getText(), splitCategory.getValue().toString(),
                Integer.parseInt(splitCapacity.getValue().toString()), splitPlaceName.getValue().toString(),
                Double.parseDouble(textPrice.getText().toString()), false))){
            Utils.createSimpleDialog("Dodawanie...", "", "Udało się dodać pokój!");
        }else{
            Utils.createSimpleDialog("Dodawanie...", "", "Coś poszło nie tak :(");
        }
        textName.clear();
        textPrice.clear();
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
