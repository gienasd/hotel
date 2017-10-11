package pl.teamjava.hotel.models.dao.impl;

import pl.teamjava.hotel.models.MySqlConnector;
import pl.teamjava.hotel.models.PlaceModel;
import pl.teamjava.hotel.models.RoomModel;
import pl.teamjava.hotel.models.Session;
import pl.teamjava.hotel.models.dao.ManagmentDao;

import javax.swing.text.html.ListView;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ManagmentDaoImpl implements ManagmentDao {

    MySqlConnector connector = MySqlConnector.getInstance();
    Session session = Session.getInstance();

    public List<String> showProperties(String accessCode) {

        List<String> propertiesList = new ArrayList<String>();

        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(
                    "SELECT name FROM place WHERE user_access = ?"
            );

            statement.setString(1, accessCode);
            ResultSet set = statement.executeQuery();

            while(set.next()){
                propertiesList.add(set.toString());
            }

            statement.close();
            return propertiesList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addRoom(RoomModel model) {

        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(
                    "INSERT INTO room VALUES(?,?,?,?,?,?,?,?)"
            );

            statement.setInt(1, 0);
            statement.setString(2, model.getName());
            statement.setString(3, model.getKindOfRoom());
            statement.setInt(4, model.getCapacity());
            statement.setString(5, model.getPlaceName());
            statement.setDouble(6, model.getPrice());
            statement.setBoolean(7, false);
            statement.setString(8, session.getAccessCode());
            statement.execute();
            statement.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteRoom(String name) {

        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(
                    "DELETE FROM room WHERE name = ?"
            );

            statement.setString(1, name);
            statement.execute();
            statement.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean editRoom(RoomModel model, int id) {

        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(
                    "UPDATE room SET name = ?, kindOfRoom = ?, capacity = ?," +
                            " placeName = ?, price = ?, isBooked = ? WHERE name = ?"
            );

            statement.setString(1, model.getName());
            statement.setString(2, model.getKindOfRoom());
            statement.setInt(3, model.getCapacity());
            statement.setString(4, model.getPlaceName());
            statement.setDouble(5, model.getPrice());
            statement.setInt(6, id);
            statement.execute();
            statement.close();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addProperty(PlaceModel model) {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(
                    "INSERT INTO place VALUES (?,?,?,?,?,?,?,?,?,?)"
            );

            statement.setInt(1, 0);
            statement.setString(2, model.getName());
            statement.setString(3, model.getCity());
            statement.setString(4, model.getRegion());
            statement.setString(5, model.getCategory());
            statement.setBoolean(6, model.isThereWiFi());
            statement.setBoolean(7, model.canIHaveAPet());
            statement.setBoolean(8, model.isThereSwimmingPool());
            statement.setBoolean(9, model.isThereSpa());
            statement.setString(10, "123123");
            statement.execute();
            statement.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProperty(String name) {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(
                    "DELETE FROM place WHERE name = ?"
            );

            statement.setString(1, name);
            statement.execute();
            statement.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addToBlockedList(String email) {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(
                    "UPDATE user SET isBlocked = ? WHERE email = ?"
            );

            statement.setBoolean(1, true);
            statement.setString(2, email);
            statement.execute();
            statement.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> showBlockedList() {

        List<String> blockedList = new ArrayList<String>();

        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(
                    "SELECT name FROM user WHERE isBlocked = 1"
            );

            ResultSet set = statement.executeQuery();

            while (set.next()){
                blockedList.add(set.toString());
            }

            statement.close();
            return blockedList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> bookedRooms() {

        List<String> bookedRoomsList = new ArrayList<>();
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(
                    "SELECT name FROM rooms WHERE isBooked = 1"
            );

            ResultSet set = statement.executeQuery();

            while (set.next()){
                bookedRoomsList.add("name");
            }
            statement.close();

            return bookedRoomsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> userList() {

        List<String> userList = new ArrayList<String>();

        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(
                    "SELECT name FROM user"
            );

            ResultSet set = statement.executeQuery();
            while (set.next()){
                userList.add(set.toString());
            }
            statement.close();

            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
