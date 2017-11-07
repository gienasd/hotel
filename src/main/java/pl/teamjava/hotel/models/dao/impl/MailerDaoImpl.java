package pl.teamjava.hotel.models.dao.impl;

import pl.teamjava.hotel.models.MailerModel;
import pl.teamjava.hotel.models.MySqlConnector;
import pl.teamjava.hotel.models.dao.MailerDao;

import java.lang.ref.PhantomReference;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukasz on 2017-11-05.
 */
public class MailerDaoImpl implements MailerDao {

    MySqlConnector connector = MySqlConnector.getInstance();

    @Override
   public List<String> recipientsList(){
        List<String>recipients= new ArrayList<>();
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement("SELECT * FROM user WHERE mailing = ? ");
            statement.setInt(1,1);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                recipients.add(result.getString("email"));
            }
statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipients;
    }

    @Override
    public String readLogin() {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement("SELECT login FROM mailer where id = ?");
            statement.setInt(1, 1);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getString("login");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean changeLogin(String login) {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement("UPDATE mailer SET login = ? where id = ?");
            statement.setString(1, login);
            statement.setInt(2, 1);
            return statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String readPassword() {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement("SELECT password FROM mailer where id = ?");
            statement.setInt(1, 1);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean changePassword(String password) {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement("UPDATE mailer SET password = ? where id = ?");
            statement.setString(1, password);
            statement.setInt(2, 1);

            return statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String readSmtpServer() {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement("SELECT smtpServer FROM mailer where id = ?");
            statement.setInt(1, 1);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getString("smtpServer");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean changeSmtpServer(String server) {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement("UPDATE mailer SET smtpServer = ? where id = ?");
            statement.setString(1, server);
            statement.setInt(2, 1);

            return statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int readSmtpPort() {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement("SELECT smtpPort FROM mailer where id = ?");
            statement.setInt(1, 1);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("smtpPort");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    @Override
    public boolean changeSmtpPort(int port) {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement("UPDATE mailer SET smtpPort = ? where id = ?");
            statement.setInt(1, port);
            statement.setInt(2, 1);

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String readSubject() {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement("SELECT subject FROM mailer where id = ?");
            statement.setInt(1, 1);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getString("subject");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




    @Override
    public boolean changeSubject(String subject) {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement("UPDATE mailer SET subject = ? where id = ?");
            statement.setString(1, subject);
            statement.setInt(2, 1);

            return statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String readContent() {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement("SELECT content FROM mailer where id = ?");
            statement.setInt(1, 1);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getString("content");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




    @Override
    public boolean changeContent(String content) {
        try {
        PreparedStatement statement = connector.getConnection().prepareStatement("UPDATE mailer SET content = ? where id = ?");
        statement.setString(1, content);
        statement.setInt(2, 1);

        return statement.execute();

        } catch (SQLException e) {
        e.printStackTrace();
        }
        return false;
    }
}

