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

public class BlockedListViewController implements Initializable {

    @FXML
    ListView<String> listBlocked;

    @FXML
    Button buttonRemove, buttonBack, buttonLogout, buttonProfile;
    @FXML
    Label labelLogedUser;
    private ManagmentDao managmentDao = new ManagmentDaoImpl();
    private ObservableList<String> blockedList;
    private Utils utils = new Utils();
    private Session userSession = Session.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonBack.setOnMouseClicked(e-> utils.switchView(buttonBack, "managmentView.fxml"));
        buttonLogout.setOnMouseClicked(e-> Utils.logoutToMainPage(buttonLogout));

        blockedList = FXCollections.observableList(managmentDao.showBlockedList());
        listBlocked.setItems(blockedList);

        buttonRemove.setOnMouseClicked(e-> tryDelete());
        labelLogedUser.setText("Zalogowany : "+userSession.getUsername());

//        buttonProfile.setOnMouseClicked(e-> showProfile());
    }

//    private void showProfile() {
//        listBlocked.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            buttonProfile.setOnMouseClicked(e-> );
//        });
//    }

    private void tryDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usuwanie własności");
        alert.setHeaderText("");
        alert.setContentText("Czy na pewno chcesz usunąć własność?");

        listBlocked.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            buttonRemove.setOnMouseClicked(e-> {
                if(e.getClickCount() == 1) {
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        managmentDao.deleteProperty(newValue);
                    } else {
                        Utils.createSimpleDialog("Usuwanie z listy blokowanych", "",
                                "Nie udało się usunąć z listy zablokowanych!");
                    }
                }
            });
        });

        listBlocked.refresh();
    }
}
