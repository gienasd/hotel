package pl.teamjava.hotel.models.dao;

import pl.teamjava.hotel.models.PlaceModel;
import pl.teamjava.hotel.models.RoomModel;

import java.util.List;

public interface ManagmentDao {
    List<String> showProperties(String accessCode);
    boolean addRoom (RoomModel model);
    boolean deleteRoom (String name);
    boolean editRoom (String name, String newName, String category, int capacity, double price);
    boolean addProperty (PlaceModel model);
    boolean deleteProperty (String name);
    boolean deleteFromBlockedList (String email);
    boolean deleteFromReservation(String name);
    boolean editProperty(String name, String newName, boolean isThereWifi,
                         boolean isTherePool, boolean isThereSpa, boolean canIHaveAPet);
    boolean getFromProperty(String addonName, String name);
    String getFromRoom(String name);
    int getCapacityRoom(String name);
    double getPriceRoom(String name);
    List<String> showBlockedList();
    List<String> bookedRooms();
    List<String> userList();
    List<String> cityList();
    List<String> placeNames();
    List<String> showRooms(String name);
}
