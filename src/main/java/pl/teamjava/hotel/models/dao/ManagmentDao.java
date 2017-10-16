package pl.teamjava.hotel.models.dao;

import pl.teamjava.hotel.models.PlaceModel;
import pl.teamjava.hotel.models.RoomModel;

import java.util.List;

public interface ManagmentDao {
    List<String> showProperties(String accessCode);
    boolean addRoom (RoomModel model);
    boolean deleteRoom (String name);
    boolean editRoom (RoomModel model, int id);
    boolean addProperty (PlaceModel model);
    boolean deleteProperty (String name);
    boolean addToBlockedList (String email);
    List<String> showBlockedList ();
    List<String> bookedRooms ();
    List<String> userList();
    List<String> cityList();
    List<String> placeNames();
    List<String> showRooms(String name);
}
