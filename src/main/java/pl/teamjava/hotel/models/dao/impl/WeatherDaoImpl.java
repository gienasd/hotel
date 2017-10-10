package pl.teamjava.hotel.models.dao.impl;

import pl.teamjava.hotel.models.MySqlConnector;
import pl.teamjava.hotel.models.WeatherModel;
import pl.teamjava.hotel.models.dao.WeatherDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeatherDaoImpl implements WeatherDao{

    MySqlConnector connector = MySqlConnector.getInstance();

    @Override
    public void addWeather(WeatherModel model) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "INSERT INTO weather VALUES(?,?,?,?)"
            );

            preparedStatement.setInt(1,0);
            preparedStatement.setString(2,model.getCityname());
            preparedStatement.setFloat(3,model.getTemp());
            preparedStatement.setDate(4,null);

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<WeatherModel> getAllWeatherData(String cityname) {
        try {
            List<WeatherModel> weatherList = new ArrayList<>();
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                   "SELECT * FROM weather WHERE cityname = ?"
            );

            preparedStatement.setString(1,cityname);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                weatherList.add(new WeatherModel(resultSet.getString("city"),resultSet.getFloat("temp"),resultSet.getDate("date")));
            }

            return weatherList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<String> getCities() {

        List<String> cityList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "SELECT DISTINCT place.city FROM place"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                cityList.add(resultSet.getString("place.city")); //czy lepiej nie wziąć miast z place?
            }

            return cityList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
