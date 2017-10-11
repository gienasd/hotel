package pl.teamjava.hotel.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;
import pl.teamjava.hotel.models.dao.ManagmentDao;
import pl.teamjava.hotel.models.dao.impl.ManagmentDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReservationViewController implements Initializable {

    @FXML
    Button buttonLogout, buttonCancel, buttonShowProfile, buttonBack;

    @FXML
    ScrollBar scrollReservation;

    @FXML
    ListView<String> listReservation;

    @FXML
    SplitMenuButton splitProperty;

    private ManagmentDao managmentDao = new ManagmentDaoImpl();
    private ObservableList<String> observableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonBack.setOnMouseClicked(e-> switchView(buttonBack, "managmentView.fxml"));
        buttonLogout.setOnMouseClicked(e-> switchView(buttonLogout, "mainView.fxml"));

        observableList = FXCollections.observableList(managmentDao.bookedRooms());
        listReservation.setItems(observableList);

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
