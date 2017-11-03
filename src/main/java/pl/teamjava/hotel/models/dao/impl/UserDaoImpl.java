package pl.teamjava.hotel.models.dao.impl;

import com.sun.org.apache.regexp.internal.RE;
import pl.teamjava.hotel.models.*;
import pl.teamjava.hotel.models.dao.UserDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    MySqlConnector connector = MySqlConnector.getInstance();

    public boolean addUser(ManagmentModel model) {

        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(
                    "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?)"
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
            statement.setBoolean(10, model.getMailing());


            statement.execute();
            statement.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean login(String username, String password) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement("SELECT * FROM user WHERE username =?");
            preparedStatement.setString(1,username);
            ResultSet resulSet = preparedStatement.executeQuery();
            if(!resulSet.next()){  // na pocz�tku jest -1, sprawdzamy, jezeli jest 0 tzn �e jest taki uzytkownik, jezeli -1, zwracamy false, tzn nie ma takiego u�ytkownika
                return  false;
            }
            return resulSet.getString("password").equals(Utils.shaHash(password));  // has�o poprawne



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean register(String name, String lastname,String username, String email,String phoneNumber,boolean isBlocked, String accessCode, String password,boolean mailing ) {

            try {
                PreparedStatement preparedStatement = connector.getConnection().prepareStatement("SELECT * FROM user WHERE username =?");
                preparedStatement.setString(1,username);
                ResultSet resulSet = preparedStatement.executeQuery();
                if(resulSet.next()){
                    Utils.createSimpleDialog("Rejestracja", "", "Użytkownik już istnieje w bazie !");
                    // na początku jest -1, sprawdzamy, jezeli jest 0 tzn że jest taki uzytkownik, jezeli -1, zwracamy false, tzn nie ma takiego użytkownika
                    return  false;
                }
                PreparedStatement preparedStatementInsert = connector.getConnection().prepareStatement("INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?)");
                preparedStatementInsert.setInt(1,0);
                preparedStatementInsert.setString(2,name);
                preparedStatementInsert.setString(3,lastname);
                preparedStatementInsert.setString(4,username);
                preparedStatementInsert.setString(5,email);
                preparedStatementInsert.setString(6,phoneNumber);
                preparedStatementInsert.setBoolean(7,false);
                preparedStatementInsert.setString(8,accessCode);//.isEmpty()?"null":accessCode);
                preparedStatementInsert.setString(9,Utils.shaHash(password));
                preparedStatementInsert.setBoolean(10,mailing);
                preparedStatementInsert.execute();
                preparedStatement.close();
                preparedStatementInsert.close();


                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }



    @Override
    public boolean loginByAccessCode(String accessCode) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement("SELECT * FROM user WHERE accessCode =?");
            preparedStatement.setString(1,accessCode);
            ResultSet resulSet = preparedStatement.executeQuery();
            if(!resulSet.next()){  // na pocz�tku jest -1, sprawdzamy, jezeli jest 0 tzn �e jest taki uzytkownik, jezeli -1, zwracamy false, tzn nie ma takiego u�ytkownika
                return  false;
            }
            return resulSet.getString("accessCode").equals(accessCode);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }




    @Override
    public String getName() {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "SELECT user.name FROM user"
            );

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getUserId(String username) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "SELECT user.name FROM user WHERE username = ? "
            );
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getLastName(String name) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "SELECT user.lastName FROM user WHERE user.name = ?"
            );

            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return resultSet.getString("lastName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getUserName(String name) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "SELECT user.userName FROM user WHERE user.name = ?"
            );

            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                return resultSet.getString("userName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getMail(String name) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "SELECT user.email FROM user WHERE user.name = ?"
            );

            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                return resultSet.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getPhoneNumber(String name) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "SELECT user.phoneNumber FROM user WHERE user.name = ?"
            );

            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                return resultSet.getString("phoneNumber");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean editAccount(String name, String lastName, String username, String email, String phoneNumber, String oldname) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "UPDATE user SET user.name = ?, user.lastName = ?, user.userName = ?, user.email = ?, user.phoneNumber = ? WHERE name = ?"
            );

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, phoneNumber);
            preparedStatement.setString(6, oldname);

            preparedStatement.execute();
            preparedStatement.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<String> getAllPlaces(String name) {
        List<String> placeList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "SELECT DISTINCT place.name FROM place INNER JOIN user ON user.accessCode = place.user_access WHERE user.name = ?"
            );

            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                placeList.add(resultSet.getString("place.name"));
            }

            return placeList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}