package pl.teamjava.hotel.models.dao.impl;

import pl.teamjava.hotel.models.MySqlConnector;
import pl.teamjava.hotel.models.PlaceModel;
import pl.teamjava.hotel.models.dao.PlaceDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceDaoImpl implements PlaceDao {

    MySqlConnector connector = MySqlConnector.getInstance();



    public List<String> getPlaceNames(PlaceModel model){

        List<String> sortedCityList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "SELECT name FROM place WHERE category = ? ORDER BY city"
            );
            preparedStatement.setString(1,model.getCategory());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                sortedCityList.add(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sortedCityList;
    }

    public List<String> getAllPlaceNames(PlaceModel model) {
        List<String> nameList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "SELECT DISTINCT name FROM place WHERE place.category = ?");
            preparedStatement.setString(1, model.getCategory());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                nameList.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nameList;
    }

    public List<String> getFreePlace(PlaceModel model) {
        List<String> sortedFreeList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "SELECT DISTINCT place.name FROM place INNER JOIN room ON room.hotel_id = place.id WHERE place.category = ? AND room.isBooked = ?" //
                    //TODO: jak posortować ilością wolnych miejsc, nie zlicza normalnie
            );

            preparedStatement.setString(1,model.getCategory());
            preparedStatement.setInt(2,0);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                sortedFreeList.add(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sortedFreeList;
    }

    public List<String> getCheapApartment(PlaceModel model) {
        List<String> sortedCheapList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "SELECT DISTINCT place.name FROM place INNER JOIN room ON room.hotel_id = place.id WHERE place.category = ? AND kindOfRoom = ? ORDER BY room.price DESC"
            );

            preparedStatement.setString(1,model.getCategory());
            preparedStatement.setString(2,"Apartament");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                sortedCheapList.add(resultSet.getString("place.name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sortedCheapList;
    }

    public List<String> getCheapRoom(PlaceModel model) {
        List<String> sortedCheapRoomList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "SELECT DISTINCT place.name FROM place INNER JOIN room ON room.hotel_id = place.id WHERE place.category = ? AND kindOfRoom NOT LIKE 'Apartament'"
            );

            preparedStatement.setString(1,model.getCategory());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                sortedCheapRoomList.add(resultSet.getString("place.name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sortedCheapRoomList;
    }

    @Override
    public List<PlaceModel> gettAllPlaceData(PlaceModel model) {
        List<PlaceModel> hotelsList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement("" +
                    "SELECT place.city, place.region FROM place WHERE place.category = ? AND place.name = ?");

            preparedStatement.setString(1,model.getCategory());
            preparedStatement.setString(2,model.getName());
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                hotelsList.add(new PlaceModel(resultSet.getString("place.name"),resultSet.getString("place.city"),resultSet.getString("place.region")));
            }

            return hotelsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getAnimalsPlace(PlaceModel model) {
        List<String> animalsList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement("" +
                    "SELECT place.name FROM place WHERE place.category = ? AND place.pets = ?");

            preparedStatement.setString(1,model.getCategory());
            preparedStatement.setInt(2,1);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                animalsList.add(resultSet.getString("place.name"));
            }

            return animalsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getWiFiPlace(PlaceModel model) {
        List<String> WiFiList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement("" +
                    "SELECT place.name FROM place WHERE place.category = ? AND place.wifi = ?");
            preparedStatement.setString(1,model.getCategory());
            preparedStatement.setInt(2,1);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                WiFiList.add(resultSet.getString("place.name"));
            }

            return WiFiList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getSpaPlace(PlaceModel model) {
        List<String> spaList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement("" +
                    "SELECT place.name FROM place WHERE place.category = ? AND place.spa = ?");
            preparedStatement.setString(1,model.getCategory());
            preparedStatement.setInt(2,1);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                spaList.add(resultSet.getString("place.name"));
            }

            return spaList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getPoolPlace(PlaceModel model) {
        List<String> poolList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement("" +
                    "SELECT place.name FROM place WHERE place.category = ? AND place.swimmingPool = ?");
            preparedStatement.setString(1,model.getCategory());
            preparedStatement.setInt(2,1);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                poolList.add(resultSet.getString("place.name"));
            }

            return poolList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getCityName(PlaceModel model) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
               "SELECT place.city FROM place WHERE place.category = ? AND place.name = ?"
            );
            preparedStatement.setString(1,model.getCategory());
            preparedStatement.setString(2,model.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return resultSet.getString("place.city");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getRegionName(PlaceModel model) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "SELECT place.region FROM place WHERE place.category = ? AND place.name = ?"
            );
            preparedStatement.setString(1,model.getCategory());
            preparedStatement.setString(2,model.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return resultSet.getString("place.region");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getPlaceByAmount(PlaceModel model) {
       // List<String> placeList = new ArrayList<>();
      //  try {
      //      PreparedStatement preparedStatement = connector.getConnection().prepareStatement("" +
       //             "SELECT place.name FROM place WHERE place.category = ? AND ")
       // } catch (SQLException e) {
       //     e.printStackTrace();
      //  }
        return null;
    }
}