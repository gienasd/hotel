package pl.teamjava.hotel.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.dao.ManagmentDao;
import pl.teamjava.hotel.models.dao.impl.ManagmentDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeletePropertyController implements Initializable {

    @FXML
    Button buttonDelete, buttonBack, buttonLogout;

    @FXML
    ListView<String> listProperty;

    @FXML
    ScrollBar scrollProperty;

    private ManagmentDao managmentDao = new ManagmentDaoImpl();
    private ObservableList<String> observableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonBack.setOnMouseClicked(e-> switchView(buttonBack, "propertiesManagmentView.fxml"));
        buttonLogout.setOnMouseClicked(e-> switchView(buttonLogout, "mainView.fxml"));

        loadProperties();

        buttonDelete.setOnMouseClicked(e-> deleteProperty());
    }

    private void deleteProperty() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usuwanie własności");
        alert.setHeaderText("");
        alert.setContentText("Czy na pewno chcesz usunąć własność?");

        listProperty.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            buttonDelete.setOnMouseClicked(e-> {
                if(e.getClickCount() == 1) {
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        managmentDao.deleteProperty(newValue);
                    } else {
                        Utils.createSimpleDialog("Usuwanie własności", "", "Nie udało się usunąć własności!");
                    }
                }
            });
        });

        loadProperties();
    }

    private void loadProperties() {
        observableList = FXCollections.observableList(managmentDao.placeNames());
        listProperty.setItems(observableList);
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
