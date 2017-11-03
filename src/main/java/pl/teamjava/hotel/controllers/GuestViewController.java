package pl.teamjava.hotel.controllers;

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
import pl.teamjava.hotel.models.*;
import pl.teamjava.hotel.models.dao.WeatherDao;
import pl.teamjava.hotel.models.dao.impl.WeatherDaoImpl;
import pl.teamjava.hotel.models.services.WeatherService1;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static pl.teamjava.hotel.models.GuestUtils.*;

public class GuestViewController implements Initializable, IWeatherOberver {

    @FXML
    ListView<String> listHotel,listCamp,listTent;
    @FXML
    Label labelNameH,labelNameC,labelNameT,labelCityH,labelCityC,labelCityT,labelRegionH,labelRegionC,labelRegionT,
            labelFreeRoomsH,labelFreeApartamentsH,labelFreeC,labelFreeT,labelWeatherH,labelWeatherC,labelWeatherT;
    @FXML
    Button buttonReserveH, buttonReserveC, buttonReserveT;
    @FXML
    RadioButton rButtonPriceApartmentH, rButtonPriceApartmentC, rButtonPriceApartmentT, rButtonPriceRoomH,
            rButtonPriceRoomC,rButtonFreeRoomH,rButtonFreeRoomC,rButtonFreeRoomT,rButtonCityH,rButtonCityC,rButtonCityT;
    @FXML
    CheckBox cBoxPoolH,cBoxPoolC,cBoxPoolT,cBoxAnimalsH,cBoxAnimalsC,cBoxAnimalsT,
            cBoxWiFiH,cBoxWiFiC,cBoxWiFiT,cBoxWellSpaH,cBoxWellSpaC;

    private ObservableList<String> observableHotel, observableCamp, observableTent;
    private ToggleGroup group = new ToggleGroup();
    private WeatherDao weatherDao = new WeatherDaoImpl();
    private WeatherService1 weatherService = WeatherService1.getService();

    public void initialize(URL location, ResourceBundle resources) {


        weatherService.registerObserver(this);

        showAllPlaces();

        createToggleGroup(rButtonPriceApartmentH,rButtonFreeRoomH,rButtonCityH,rButtonPriceRoomH);

        rButtonPriceApartmentH.setOnMouseClicked(s -> GuestUtils.sortByApartmentPrice(observableHotel,new PlaceModel("Hotel"),listHotel));
        rButtonPriceRoomH.setOnMouseClicked(s -> GuestUtils.sortByRoomPrice(observableHotel,new PlaceModel("Hotel"),listHotel));
        rButtonFreeRoomH.setOnMouseClicked(s -> GuestUtils.sortByFreeRooms(observableHotel,new PlaceModel("Hotel"),listHotel));
        rButtonCityH.setOnMouseClicked(s -> GuestUtils.sortByCities(observableHotel,new PlaceModel("Hotel"),listHotel));

        createToggleGroup(rButtonPriceApartmentC,rButtonFreeRoomC,rButtonCityC,rButtonPriceRoomC);

        rButtonPriceApartmentC.setOnMouseClicked(s -> GuestUtils.sortByApartmentPrice(observableCamp,new PlaceModel("Camping"), listCamp));
        rButtonPriceRoomC.setOnMouseClicked(s -> GuestUtils.sortByRoomPrice(observableCamp,new PlaceModel("Camping"),listCamp));
        rButtonFreeRoomC.setOnMouseClicked(s -> GuestUtils.sortByFreeRooms(observableCamp,new PlaceModel("Camping"),listCamp));
        rButtonCityC.setOnMouseClicked(s -> GuestUtils.sortByCities(observableCamp,new PlaceModel("Camping"),listCamp));

        createToggleGroup(rButtonPriceApartmentT,rButtonFreeRoomT,rButtonCityT,rButtonPriceApartmentT);

        rButtonPriceApartmentT.setOnMouseClicked(s -> GuestUtils.sortByApartmentPrice(observableTent,new PlaceModel("Tent"),listTent));
        rButtonFreeRoomT.setOnMouseClicked(s -> GuestUtils.sortByFreeRooms(observableTent,new PlaceModel("Tent"),listTent));
        rButtonCityT.setOnMouseClicked(s -> GuestUtils.sortByCities(observableTent,new PlaceModel("Tent"),listTent));

        reserve();

        showList(listHotel, labelNameH, labelCityH, labelRegionH,labelFreeRoomsH,"Hotel");
        showList(listCamp, labelNameC, labelCityC, labelRegionC,labelFreeC,"Camping");
        showList(listTent, labelNameT, labelCityT, labelRegionT,labelFreeT,"Tent");

        boxAnimals(cBoxAnimalsH, observableHotel, "Hotel",listHotel);
        boxPool(cBoxPoolH, observableHotel, "Hotel",listHotel);
        boxWellSpa(cBoxWellSpaH, observableHotel, "Hotel",listHotel);
        boxWiFi(cBoxWiFiH, observableHotel, "Hotel",listHotel);

        boxAnimals(cBoxAnimalsC, observableCamp, "Camping", listCamp);
        boxPool(cBoxPoolC, observableCamp, "Camping", listCamp);
        boxWellSpa(cBoxWellSpaC, observableCamp, "Camping", listCamp);
        boxWiFi(cBoxWiFiC, observableCamp, "Camping", listCamp);

        boxAnimals(cBoxAnimalsT, observableTent, "Tent", listTent);
        boxPool(cBoxPoolT, observableTent, "Tent", listTent);
        boxWiFi(cBoxWiFiT, observableTent, "Tent", listTent);

        showWeatherData(listHotel, "Hotel");
        showWeatherData(listCamp, "Camping");
        showWeatherData(listTent, "Tent");




    }

    private void reserve() {
        buttonReserveH.setOnMouseClicked(s -> tryReserve(buttonReserveH));
        buttonReserveC.setOnMouseClicked(s -> tryReserve(buttonReserveC));
        buttonReserveT.setOnMouseClicked(s -> tryReserve(buttonReserveT));
    }

    private void createToggleGroup(RadioButton priceA, RadioButton freeRoom, RadioButton city, RadioButton priceR) {
        priceA.setToggleGroup(group);
        freeRoom.setToggleGroup(group);
        city.setToggleGroup(group);
        priceR.setToggleGroup(group);
    }

    public void tryReserve(Button button) {
        Stage stage = (Stage)button.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("registerView.fxml"));
            stage.setScene(new Scene(root,600,420));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAllPlaces() {
        showPlaces(observableHotel,listHotel, "Hotel");
        showPlaces(observableCamp,listCamp, "Camping");
        showPlaces(observableTent,listTent, "Tent");
    }

    @Override
    public void onWeatherUpdate(WeatherInfo info) {
        labelWeatherH.setText("Temp: " + info.getTemp() + " | Ciśnienie: " + info.getPressure());
        labelWeatherC.setText("Temp: " + info.getTemp() + " | Ciśnienie: " + info.getPressure());
        labelWeatherT.setText("Temp: " + info.getTemp() + " | Ciśnienie: " + info.getPressure());
        weatherDao.addWeather(new WeatherModel(info));
    }


}