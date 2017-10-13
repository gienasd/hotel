package pl.teamjava.hotel.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import pl.teamjava.hotel.models.dao.PlaceDao;
import pl.teamjava.hotel.models.dao.impl.PlaceDaoImpl;

import java.time.LocalDate;

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
}
