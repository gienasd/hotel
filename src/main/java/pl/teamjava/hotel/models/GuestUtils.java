package pl.teamjava.hotel.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import pl.teamjava.hotel.models.dao.PlaceDao;
import pl.teamjava.hotel.models.dao.UserDao;
import pl.teamjava.hotel.models.dao.impl.PlaceDaoImpl;
import pl.teamjava.hotel.models.dao.impl.UserDaoImpl;
import pl.teamjava.hotel.models.services.WeatherService1;

import java.io.IOException;
import java.time.LocalDate;

public class GuestUtils {

    private static PlaceDao placeDao = new PlaceDaoImpl();
    private static UserDao userDao = new UserDaoImpl();
    private static Session userSession = Session.getInstance();
    public static WeatherService1 weatherService = WeatherService1.getService();

    public static void showWiFi(ObservableList<String> observableList, PlaceModel model, ListView<String> listView) {
        observableList= FXCollections.observableList(placeDao.getWiFiPlace(model));
        listView.setItems(observableList);
    }

    public static void showSpa(ObservableList<String> observableList, PlaceModel model, ListView<String> listView) {
        observableList = FXCollections.observableList(placeDao.getSpaPlace(model));
        listView.setItems(observableList);
    }

    public static void showPools(ObservableList<String> observableList, PlaceModel model, ListView<String> listView) {
        observableList = FXCollections.observableList(placeDao.getPoolPlace(model));
        listView.setItems(observableList);
    }

    public static void showAnimals(ObservableList<String> observableList, PlaceModel model, ListView<String> listView) {
        observableList = FXCollections.observableList(placeDao.getAnimalsPlace(model));
        listView.setItems(observableList);
    }

    public static void sortByApartmentPrice(ObservableList<String> observableList, PlaceModel model, ListView<String> listView) {
        observableList = FXCollections.observableList(placeDao.getCheapApartment(model));
        listView.setItems(observableList);
    }

    public static void sortByFreeRooms(ObservableList<String> observableList, PlaceModel model, ListView<String> listView) {
        observableList = FXCollections.observableList(placeDao.getFreePlace(model));
        listView.setItems(observableList);
    }

    public static void sortByRoomPrice(ObservableList<String> observableList, PlaceModel model, ListView<String> listView) {
        observableList = FXCollections.observableList(placeDao.getCheapRoom(model));
        listView.setItems(observableList);
    }

    public static void sortByCities(ObservableList<String> observableList, PlaceModel model, ListView<String> listView) {
        observableList = FXCollections.observableList(placeDao.getPlaceNames(model));
        listView.setItems(observableList);
    }

    public static void dateUtils(DatePicker datePick, DatePicker datePick2) {
        final Callback<DatePicker, DateCell> dayCellFactory =
                new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);

                                if (item.isBefore(
                                        datePick.getValue().plusDays(1))
                                        ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                            }
                        };
                    }
                };
        datePick2.setDayCellFactory(dayCellFactory);
    }

    public static void dateUtils(LocalDate localDate, DatePicker datePick2) {
        final Callback<DatePicker, DateCell> dayCellFactory =
                new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);

                                if (item.isBefore(
                                        localDate.now())
                                        ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                            }
                        };
                    }
                };
        datePick2.setDayCellFactory(dayCellFactory);
    }

    public static void showHistory(ObservableList<String> observableList, ListView<String> listView) {
        observableList = FXCollections.observableArrayList(userDao.getAllPlaces(userSession.getAccessCode()));
        listView.setItems(observableList);
    }

    public static void loadData(TextField name,TextField lastName,TextField userName,TextField mail,TextField phoneNumber) {
        name.setText(userDao.getName());
        lastName.setText(userDao.getLastName(userSession.getAccessCode()));
        userName.setText(userDao.getUserName(userSession.getAccessCode()));
        mail.setText(userDao.getMail(userSession.getAccessCode()));
        phoneNumber.setText(userDao.getPhoneNumber(userSession.getAccessCode()));
    }

    public static void editableData(TextField name,TextField lastName,TextField userName,TextField mail,TextField phoneNumber){
        name.setEditable(false);
        lastName.setEditable(false);
        userName.setEditable(false);
        mail.setEditable(false);
        phoneNumber.setEditable(false);
    }

    public static void boxPool(CheckBox cBox, ObservableList<String> observableList, String category, ListView<String> listView) {
        cBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            GuestUtils.showPools(observableList,new PlaceModel(category),listView);
        });
    }
    public static void boxWellSpa(CheckBox cBox, ObservableList<String> observableList, String category, ListView<String> listView) {
        cBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            GuestUtils.showSpa(observableList,new PlaceModel(category),listView);
        });
    }
    public static void boxWiFi(CheckBox cBox, ObservableList<String> observableList, String category, ListView<String> listView) {
        cBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            GuestUtils.showWiFi(observableList,new PlaceModel(category),listView);
        });
    }
    public static void boxAnimals(CheckBox cBox, ObservableList<String> observableList, String category, ListView<String> listView) {
        cBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            GuestUtils.showAnimals(observableList,new PlaceModel(category),listView);
        });
    }

    public static void showList(ListView<String> listView,Label label1, Label label2, Label label3, Label label4, String category) {
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            label1.setText(newValue);
            label2.setText("Miasto: " + placeDao.getCityName(new PlaceModel(newValue,category)));
            label3.setText("Województwo: " + placeDao.getRegionName(new PlaceModel(newValue,category)));
            label4.setText("Liczba wolnych miejsc: " + String.valueOf(placeDao.getFreePlaceNumber(new PlaceModel(newValue,category))));
        });
    }

    public static void showWeatherData(ListView<String> listView, String category){
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            weatherService.makeRequest(placeDao.getCityName(new PlaceModel(newValue,category)));
        });

    }

    public static void showPlaces(ObservableList<String> observableList, ListView<String> listView, String category){
        observableList = FXCollections.observableList(placeDao.getAllPlaceNames(new PlaceModel(category)));
        listView.setItems(observableList);
    }




}
