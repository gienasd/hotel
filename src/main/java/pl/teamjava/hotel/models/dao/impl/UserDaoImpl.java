package pl.teamjava.hotel.models.dao.impl;

import pl.teamjava.hotel.models.ManagmentModel;
import pl.teamjava.hotel.models.MySqlConnector;
import pl.teamjava.hotel.models.Session;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.dao.UserDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    MySqlConnector connector = MySqlConnector.getInstance();
    private Session session = Session.getInstance();

    public boolean addUser(ManagmentModel model) {

        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(
                    "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?)"
            );

            statement.setInt(1,0);
            statement.setString(2, model.getName());
            statement.setString(3, model.getLastname());
            statement.setString(4, model.getUsername());
            statement.setString(5, model.getEmail());
            statement.setString(6, model.getPhoneNumber());
            statement.setBoolean(7, false);
            statement.setString(8, model.getAccessCode());
            statement.setString(9, model.getPassword());


            statement.execute();
            statement.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean login(String name, String password) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement("SELECT * FROM user WHERE username =?");
            preparedStatement.setString(1,name);
            ResultSet resulSet = preparedStatement.executeQuery();
            if(!resulSet.next()){  // na pocz±tku jest -1, sprawdzamy, jezeli jest 0 tzn ¿e jest taki uzytkownik, jezeli -1, zwracamy false, tzn nie ma takiego u¿ytkownika
                return  false;
            }
            session.setId(resulSet.getInt("id"));
            return resulSet.getString("password").equals(Utils.shaHash(password));  // has³o poprawne



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
