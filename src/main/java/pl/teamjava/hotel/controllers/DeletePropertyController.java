package pl.teamjava.hotel.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.dao.ManagmentDao;
import pl.teamjava.hotel.models.dao.impl.ManagmentDaoImpl;

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
    private Utils utils = new Utils();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonBack.setOnMouseClicked(e-> utils.switchView(buttonBack, "propertiesManagmentView.fxml"));
        buttonLogout.setOnMouseClicked(e-> utils.switchView(buttonLogout, "mainView.fxml"));

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
}
