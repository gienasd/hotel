package pl.teamjava.hotel.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pl.teamjava.hotel.models.GuestUtils;
import pl.teamjava.hotel.models.PlaceModel;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.dao.PlaceDao;
import pl.teamjava.hotel.models.dao.impl.PlaceDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import static pl.teamjava.hotel.models.GuestUtils.showFreePlaces;
import static pl.teamjava.hotel.models.GuestUtils.showPlaces;

public class ReservationGuestViewController implements Initializable {

    @FXML
    Button buttonAccount, buttonLogOut, buttonReserve;

    @FXML
    DatePicker datePickArrivalDate, datePickDepartureDate;

    @FXML
    ListView<String> listViewHotel, listViewCamp, listViewTent;

    @FXML
    ChoiceBox<Integer> choiceBoxPeople, choiceBoxPeopleInRoom;

    @FXML
    Label labelInfo;

    ObservableList<String> observableHotel, observableCamp, observableTent;
    PlaceDao placeDao = new PlaceDaoImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        showAllPlaces();

        weeksOfPickerDate();

        GuestUtils.dateUtils(LocalDate.now(),datePickArrivalDate);
        GuestUtils.dateUtils(datePickArrivalDate,datePickDepartureDate);
Utils utils=new Utils();
        choiceBoxPeople.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));
        choiceBoxPeopleInRoom.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));

        choiceBoxPeople.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            choiceBoxPeople.setValue(newValue);
        });
        choiceBoxPeopleInRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            choiceBoxPeopleInRoom.setValue(newValue);
            checkChoiceBoxValue(choiceBoxPeopleInRoom,choiceBoxPeople);
            howManyRooms(choiceBoxPeopleInRoom,choiceBoxPeople);
            showNeededPlace(observableHotel,new PlaceModel("Hotel"),listViewHotel,howManyRooms(choiceBoxPeopleInRoom,choiceBoxPeople));
            showNeededPlace(observableCamp,new PlaceModel("Camping"),listViewCamp,howManyRooms(choiceBoxPeopleInRoom,choiceBoxPeople));
            showNeededPlace(observableTent,new PlaceModel("Tent"),listViewTent,howManyRooms(choiceBoxPeopleInRoom,choiceBoxPeople));
        });

        buttonReserve.setOnMouseClicked(s -> tryToReserve());
        buttonAccount.setOnMouseClicked(s -> showAccount(buttonAccount));
        buttonLogOut.setOnMouseClicked(s -> tryToLogOut(buttonLogOut));
        buttonAccount.getScene().getWindow().setOnCloseRequest(e->Utils.loadMainPage());}
    private void weeksOfPickerDate() {
        datePickArrivalDate.setShowWeekNumbers(false);
        datePickDepartureDate.setShowWeekNumbers(false);
    }

    private void showNeededPlace(ObservableList<String> observableList, PlaceModel model, ListView<String> listView, int rooms){
        observableList = FXCollections.observableList(placeDao.getFreePlace(model,rooms));
        listView.setItems(observableList);
    }

    private int howManyRooms(ChoiceBox<Integer> box1, ChoiceBox<Integer> box2) {
        int rooms = 0, value1, value2;
        value1 = box1.getSelectionModel().selectedItemProperty().getValue();
        value2 = box2.getSelectionModel().selectedItemProperty().getValue();
        if(value1 < value2 && value2%value1 == 0){
            rooms = value2/value1;
        }else if(value1==value2){
            rooms = 1;
        }else if(value1 < value2 && value2%value1 != 0){
            rooms = (value2/value1)+1;
        }

        return rooms;
    }

    private void showPlaces(ObservableList<String> observableList, PlaceModel model, ListView<String> listView){
        observableList = FXCollections.observableList(placeDao.getPlaceByAmount(model));
        listView.setItems(observableList);
    }

    private void checkChoiceBoxValue(ChoiceBox<Integer> box1, ChoiceBox<Integer> box2) {
        if(box1.getSelectionModel().selectedItemProperty().getValue() > box2.getSelectionModel().selectedItemProperty().getValue()){
            labelInfo.setText("Liczba gości w pokoju\n nie może być większa niż liczba gości\n rezerwujących pokój");
        }
    }

    private void tryToLogOut(Button button) {
        Stage stage = (Stage)button.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainView.fxml"));
            stage.setScene(new Scene(root,600,400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAccount(Button button) {
        Stage stage = (Stage)button.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("accountView.fxml"));
            stage.setScene(new Scene(root,600,400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tryToReserve() {
        if(datePickArrivalDate.getValue() != null && datePickDepartureDate.getValue()!= null && !choiceBoxPeople.equals(null)){
            labelInfo.setText("Rezerwacja przebiegła poprawnie");
        } else
            labelInfo.setText("Wypełnij wszystkie dane");
    }

    private void showAllPlaces() {
        showFreePlaces(observableHotel,listViewHotel, "Hotel");
        showFreePlaces(observableCamp,listViewCamp, "Camping");
        showFreePlaces(observableTent,listViewTent, "Tent");
    }
}
