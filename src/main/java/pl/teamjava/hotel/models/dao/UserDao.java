package pl.teamjava.hotel.models.dao;

import pl.teamjava.hotel.models.ManagmentModel;
import pl.teamjava.hotel.models.UserModel;

import java.util.List;

public interface UserDao {
    boolean addUser(ManagmentModel model);
    boolean login(String name, String password);
    boolean register (String username, String name,String lastname, String email,String password, String telephone,String accessCode,boolean mailing);
    boolean loginByAccessCode(String accessCode);
    String getName();
    String getLastName(String name);
    String getUserName(String name);
    String getMail(String name);
    String getPhoneNumber(String name);
    boolean editAccount(String name, String lastName, String username, String email, String phoneNumber, String oldname);
    List<String> getAllPlaces(String name);

}
