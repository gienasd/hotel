package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RoomManagmentViewController implements Initializable {

    @FXML
    Button buttonAdd, buttonRemove, buttonEdit, buttonLogout, buttonBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonAdd.setOnMouseClicked(e-> switchView(buttonAdd, "addRoom.fxml"));
        buttonEdit.setOnMouseClicked(e-> switchView(buttonEdit, "editRoom.fxml"));
        buttonRemove.setOnMouseClicked(e-> switchView(buttonRemove, "deleteRoom.fxml"));
        buttonBack.setOnMouseClicked(e-> switchView(buttonBack, "managmentView.fxml"));
        buttonLogout.setOnMouseClicked(e-> switchView(buttonLogout, "mainView.fxml"));
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
