package pl.teamjava.hotel.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnector {
    private static final String SQL_LINK = "jdbc:mysql://5.135.218.27:3306/Hotel?useUnicode=true&characterEncoding=UTF-8";
    private static final String SQL_USER = "PawelP";
    private static final String SQL_PASS = "coseainmam";
    private static final String SQL_CLASS = "com.mysql.jdbc.Driver";

    private static MySqlConnector connector = new MySqlConnector();

    public static MySqlConnector getInstance(){
        return connector;
    }

    public MySqlConnector(){
        connect();
    }

    private Connection connection;
    public Connection getConnection(){
        return connection;
    }

    private void connect() {
        try {
            Class.forName(SQL_CLASS);
            connection = DriverManager.getConnection(SQL_LINK, SQL_USER, SQL_PASS);
            System.out.println("Połączenie udane!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

