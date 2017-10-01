package pl.teamjava.hotel.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pl.teamjava.hotel.models.dao.CampingDao;
import pl.teamjava.hotel.models.dao.HotelDao;
import pl.teamjava.hotel.models.dao.TentDao;
import pl.teamjava.hotel.models.dao.impl.CampingDaoImpl;
import pl.teamjava.hotel.models.dao.impl.HotelDaoImpl;
import pl.teamjava.hotel.models.dao.impl.TentDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class guestViewController implements Initializable {

    @FXML
    ListView<String> listHotel,listCamp,listTent;
    @FXML
    Label labelHotel,labelCamp,labelTent;
    @FXML
    Button buttonReserveH, buttonReserveC, buttonReserveT, buttonWeatherH, buttonWeatherC, buttonWeatherT;
    @FXML
    RadioButton rButtonPriceApartmentH, rButtonPriceApartmentC, rButtonPriceApartmentT, rButtonPriceRoomH,
            rButtonPriceRoomC,rButtonFreeRoomH,rButtonFreeRoomC,rButtonFreeRoomT,
            rButtonOpinionH,rButtonOpinionC,rButtonOpinionT,rButtonCityH,rButtonCityC,rButtonCityT;
    @FXML
    CheckBox cBoxAquaPH,cBoxAquaPC,cBoxAquaPT,cBoxPoolH,cBoxPoolC,cBoxPoolT,cBoxAnimalsH,cBoxAnimalsC,cBoxAnimalsT,
            cBoxWiFiH,cBoxWiFiC,cBoxWiFiT,cBoxWellSpaH,cBoxWellSpaC,cBoxWellSpaT;

    private ObservableList<String> observableHotel, observableCamp, observableTent;
    private HotelDao hotelDao = new HotelDaoImpl();
    private CampingDao campingDao = new CampingDaoImpl();
    private TentDao tentDao = new TentDaoImpl();
    private ToggleGroup group = new ToggleGroup();

    public void initialize(URL location, ResourceBundle resources) {

        //wyświetlanie głównej listy hoteli, kampingów i pól namioptowych
        observableHotel = FXCollections.observableList(hotelDao.getAllHotelsNames());
        listHotel.setItems(observableHotel);
        //   observableCamp = FXCollections.observableList(campingDao.getAllCampingNames());
        //   listCamp.setItems(observableCamp);
        //   observableTent = FXCollections.observableList(tentDao.getAllTentsNames());
        //   listTent.setItems(observableTent);

        //TODO: buttony, labelki

        //dołączenie radioButton do grupy
        rButtonPriceApartmentH.setToggleGroup(group);
        rButtonFreeRoomH.setToggleGroup(group);
        rButtonCityH.setToggleGroup(group);
        rButtonPriceRoomH.setToggleGroup(group);

        //obsługa sortowania listy wg kryteriów hotele
        rButtonPriceApartmentH.setOnMouseClicked(s -> sortByApartmentPrice());
        rButtonPriceRoomH.setOnMouseClicked(s -> sortByRoomPrice());
        rButtonFreeRoomH.setOnMouseClicked(s -> sortByFreeRooms());
        rButtonCityH.setOnMouseClicked(s -> sortByCities());

        //obsługa rezerwacji - przeniesienie do okna logowania/rehestracji
        buttonReserveH.setOnMouseClicked(s -> tryReserve());

        //wyświetlanie informacji o hotelu po kliknięcu na nazwę
        listHotel.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    showInfo(newValue);
                }
        );
    }

    //elo
    // no siema :D

    private void showInfo(String newValue) {
    }

    private void tryReserve() {
        Stage stage = (Stage)buttonReserveH.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("loggedGuestView.fxml"));
            stage.setScene(new Scene(root,600,400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void sortByApartmentPrice() {
        observableHotel = FXCollections.observableList(hotelDao.getCheapApartment());
        listHotel.setItems(observableHotel);
    }

    private void sortByFreeRooms() {
        observableHotel = FXCollections.observableList(hotelDao.getFreeHotels());
        listHotel.setItems(observableHotel);
    }

    private void sortByRoomPrice() {
        observableHotel = FXCollections.observableList(hotelDao.getCheapRoom());
        listHotel.setItems(observableHotel);
    }

    private void sortByCities() {
        observableHotel = FXCollections.observableList(hotelDao.getHotelsNames());
        listHotel.setItems(observableHotel);
    }
}