package pl.teamjava.hotel.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import pl.teamjava.hotel.models.GuestUtils;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReservationGuestViewController implements Initializable {

    @FXML
    Button buttonAccount, buttonLogOut, buttonReserve;

    @FXML
    DatePicker datePickArrivalDate, datePickDepartureDate;

    @FXML
    ListView<String> listViewHotel, listViewCamp, listViewTent;

    @FXML
    ChoiceBox choiceBoxPeople, choiceBoxPeopleInRoom;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        datePickArrivalDate.setShowWeekNumbers(false);
        datePickDepartureDate.setShowWeekNumbers(false);

        GuestUtils.dateUtils(LocalDate.now(),datePickArrivalDate);
        GuestUtils.dateUtils(datePickArrivalDate,datePickDepartureDate);

        ChoiceBox<Integer> choiceBoxPeople = new ChoiceBox<>(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));
    }
}
