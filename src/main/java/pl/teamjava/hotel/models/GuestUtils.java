package pl.teamjava.hotel.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import pl.teamjava.hotel.models.dao.PlaceDao;
import pl.teamjava.hotel.models.dao.impl.PlaceDaoImpl;

public class GuestUtils {

    private static PlaceDao placeDao = new PlaceDaoImpl();

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
}
