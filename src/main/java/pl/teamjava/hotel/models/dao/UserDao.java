package pl.teamjava.hotel.models.dao;

import pl.teamjava.hotel.models.ManagmentModel;
import pl.teamjava.hotel.models.UserModel;

import java.util.List;

public interface UserDao {
    boolean addUser(ManagmentModel model);
    boolean login(String username, String password);
    boolean register ( String name,String lastname, String username,String email, String phoneNumber,boolean isBlocked,String accessCode,String password,boolean mailing);
    boolean loginByAccessCode(String accessCode);

    String getName();
    Integer getUserId(String username);
    String getLastName(String name);
    String getUserName(String name);
    String getMail(String name);
    String getPhoneNumber(String name);
    boolean editAccount(String name, String lastName, String username, String email, String phoneNumber, String oldname);
    List<String> getAllPlaces(String name);

}
