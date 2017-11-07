package pl.teamjava.hotel.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pl.teamjava.hotel.models.Session;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.dao.ManagmentDao;
import pl.teamjava.hotel.models.dao.impl.ManagmentDaoImpl;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeleteRoomController implements Initializable {

    @FXML
    ChoiceBox splitProperty;

    @FXML
    Button buttonDelete, buttonBack, buttonLogout;

    @FXML
    ListView<String> listRooms;

    @FXML
    ScrollBar scrollProperty;
    @FXML
    Label labelLogedUser;
    private ManagmentDao managmentDao = new ManagmentDaoImpl();
    private Session session = Session.getInstance();
    private ObservableList<String> observableList;
    private Utils utils = new Utils();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonBack.setOnMouseClicked(e-> utils.switchView(buttonBack, "roomManagmentView.fxml"));
        buttonLogout.setOnMouseClicked(e-> Utils.logoutToMainPage(buttonLogout));

        splitProperty.getItems().addAll(managmentDao.showProperties(session.getAccessCode()));

        loadRooms();

        buttonDelete.setOnMouseClicked(e -> deleteRoom());
        labelLogedUser.setText("Zalogowany : "+session.getUsername());

    }

    private void loadRooms() {
        observableList = FXCollections.observableList(managmentDao.showRooms(splitProperty.getSelectionModel().getSelectedItem().toString()));
        listRooms.getItems().addAll(observableList); //TODO: won't work till session will remember AccessCode
    }

    private void deleteRoom() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usuwanie własności");
        alert.setHeaderText("");
        alert.setContentText("Czy na pewno chcesz usunąć pokój?");

        listRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            buttonDelete.setOnMouseClicked(e-> {
                if(e.getClickCount() == 1) {
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        managmentDao.deleteRoom(newValue);
                    } else {
                        Utils.createSimpleDialog("Usuwanie pokoju", "", "Nie udało się usunąć pokoju!");
                    }
                }
            });
        });

        listRooms.refresh();
    }
}
