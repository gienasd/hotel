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
import pl.teamjava.hotel.models.*;
import pl.teamjava.hotel.models.dao.PlaceDao;
import pl.teamjava.hotel.models.dao.WeatherDao;
import pl.teamjava.hotel.models.dao.impl.PlaceDaoImpl;
import pl.teamjava.hotel.models.dao.impl.WeatherDaoImpl;
import pl.teamjava.hotel.models.services.WeatherService1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private PlaceDao placeDao = new PlaceDaoImpl();
    private ToggleGroup group = new ToggleGroup();
    private WeatherDao weatherDao = new WeatherDaoImpl();
    private WeatherService1 weatherService = WeatherService1.getService();

    public void initialize(URL location, ResourceBundle resources) {

        weatherService.registerObserver(this);

        observableHotel = FXCollections.observableList(placeDao.getAllPlaceNames(new PlaceModel("Hotel")));
        listHotel.setItems(observableHotel);
        observableCamp = FXCollections.observableList(placeDao.getAllPlaceNames(new PlaceModel("Camping")));
        listCamp.setItems(observableCamp);
        observableTent = FXCollections.observableList(placeDao.getAllPlaceNames(new PlaceModel("Tent")));
        listTent.setItems(observableTent);


        rButtonPriceApartmentH.setToggleGroup(group);
        rButtonFreeRoomH.setToggleGroup(group);
        rButtonCityH.setToggleGroup(group);
        rButtonPriceRoomH.setToggleGroup(group);

        rButtonPriceApartmentH.setOnMouseClicked(s -> GuestUtils.sortByApartmentPrice(observableHotel,new PlaceModel("Hotel"),listHotel));
        rButtonPriceRoomH.setOnMouseClicked(s -> GuestUtils.sortByRoomPrice(observableHotel,new PlaceModel("Hotel"),listHotel));
        rButtonFreeRoomH.setOnMouseClicked(s -> GuestUtils.sortByFreeRooms(observableHotel,new PlaceModel("Hotel"),listHotel));
        rButtonCityH.setOnMouseClicked(s -> GuestUtils.sortByCities(observableHotel,new PlaceModel("Hotel"),listHotel));

        rButtonPriceApartmentC.setToggleGroup(group);
        rButtonFreeRoomC.setToggleGroup(group);
        rButtonCityC.setToggleGroup(group);
        rButtonPriceRoomC.setToggleGroup(group);

        rButtonPriceApartmentC.setOnMouseClicked(s -> GuestUtils.sortByApartmentPrice(observableCamp,new PlaceModel("Camping"), listCamp));
        rButtonPriceRoomC.setOnMouseClicked(s -> GuestUtils.sortByRoomPrice(observableCamp,new PlaceModel("Camping"),listCamp));
        rButtonFreeRoomC.setOnMouseClicked(s -> GuestUtils.sortByFreeRooms(observableCamp,new PlaceModel("Camping"),listCamp));
        rButtonCityC.setOnMouseClicked(s -> GuestUtils.sortByCities(observableCamp,new PlaceModel("Camping"),listCamp));

        rButtonPriceApartmentT.setToggleGroup(group);
        rButtonFreeRoomT.setToggleGroup(group);
        rButtonCityT.setToggleGroup(group);

        rButtonPriceApartmentT.setOnMouseClicked(s -> GuestUtils.sortByApartmentPrice(observableTent,new PlaceModel("Tent"),listTent));
        rButtonFreeRoomT.setOnMouseClicked(s -> GuestUtils.sortByFreeRooms(observableTent,new PlaceModel("Tent"),listTent));
        rButtonCityT.setOnMouseClicked(s -> GuestUtils.sortByCities(observableTent,new PlaceModel("Tent"),listTent));

        buttonReserveH.setOnMouseClicked(s -> tryReserve(buttonReserveH));
        buttonReserveC.setOnMouseClicked(s -> tryReserve(buttonReserveC));
        buttonReserveT.setOnMouseClicked(s -> tryReserve(buttonReserveT));


        listHotel.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            labelNameH.setText(newValue);
            //labelCityH.setText(hotelDao.getCityName(newValue));
            //labelRegionH.setText(hotelDao.getRegionName(newValue));
            //labelFreeRoomsH.setText();
            //labelFreeApartaments.setText();
        });

        cBoxAnimalsH.selectedProperty().addListener((observable, oldValue, newValue) -> {
            GuestUtils.showAnimals(observableHotel,new PlaceModel("Hotel"),listHotel);
        });
        cBoxPoolH.selectedProperty().addListener((observable, oldValue, newValue) -> {
            GuestUtils.showPools(observableHotel,new PlaceModel("Hotel"),listHotel);
        });
        cBoxWellSpaH.selectedProperty().addListener((observable, oldValue, newValue) -> {
            GuestUtils.showSpa(observableHotel,new PlaceModel("Hotel"),listHotel);
        });
        cBoxWiFiH.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            GuestUtils.showWiFi(observableHotel,new PlaceModel("Hotel"),listHotel);
        }));

       // listHotel.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
       //     weatherService.makeRequest(hotelDao.getCityName(newValue));
       // }));
    }


    public void tryReserve(Button button) {
        Stage stage = (Stage)button.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("loggedGuestView.fxml"));
            stage.setScene(new Scene(root,600,400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onWeatherUpdate(WeatherInfo info) {
        labelWeatherH.setText("Temp: " + info.getTemp() + " | Ciśnienie: " + info.getPressure());
        weatherDao.addWeather(new WeatherModel(info));
    }
}