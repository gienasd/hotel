package pl.teamjava.hotel.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.StageStyle;
import pl.teamjava.hotel.models.Session;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.dao.ManagmentDao;
import pl.teamjava.hotel.models.dao.UserDao;
import pl.teamjava.hotel.models.dao.impl.ManagmentDaoImpl;
import pl.teamjava.hotel.models.dao.impl.UserDaoImpl;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class
ReservationViewController implements Initializable {

    @FXML
    Button buttonLogout, buttonCancel, buttonShowProfile, buttonBack, buttonMailer;

    @FXML
    ScrollBar scrollReservation;

    @FXML
    ListView<String> listReservation;
    @FXML
    private Label labelLogedUser;

    @FXML
    ChoiceBox splitProperty;
    UserDao userDao=new UserDaoImpl();
    private ManagmentDao managmentDao = new ManagmentDaoImpl();
    private ObservableList<String> observableList;
    private Utils utils = new Utils();
    private Session userSession = Session.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonBack.setOnMouseClicked(e-> utils.switchView(buttonBack, "managmentView.fxml"));
        buttonLogout.setOnMouseClicked(e-> Utils.logoutToMainPage(buttonLogout));


        observableList = FXCollections.observableList(managmentDao.bookedRooms());
        listReservation.setItems(observableList);

        buttonCancel.setOnMouseClicked(e-> tryCancel());

        splitProperty.getItems().addAll(managmentDao.placeNames());
//        splitProperty.getSelectionModel().getSelectedItem().toString();
        labelLogedUser.setText("Zalogowany : "+userSession.getUsername());
        if(userSession.getUsername().equals("admin")){
            buttonMailer.setVisible(true);
        }
        buttonMailer.setOnMouseClicked(e->Utils.switchView2(buttonMailer,"/mailerViev.fxml",250,420,true, StageStyle.UNDECORATED));
    }

    private void tryCancel() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usuwanie rezerwacji");
        alert.setHeaderText("");
        alert.setContentText("Czy na pewno chcesz usunąć rezerwacje?");

        listReservation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            buttonCancel.setOnMouseClicked(e-> {
                if(e.getClickCount() == 1) {
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        managmentDao.deleteFromReservation(newValue);
                    } else {
                        Utils.createSimpleDialog("Usuwanie rezerwacji", "", "Nie udało się usunąć rezerwacji!");
                    }
                }
            });
        });

        listReservation.refresh();
    }
}
