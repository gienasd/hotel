package pl.teamjava.hotel.models.dao;

import pl.teamjava.hotel.models.ManagmentModel;

public interface UserDao {
    boolean addUser(ManagmentModel model);
    boolean login(String name, String password);
    boolean loginByAccessCode(String accessCode);
}
