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

}
