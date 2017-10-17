package pl.teamjava.hotel.models;

import lombok.Data;

@Data
public class UserModel {
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String phoneNumber;
    private boolean isBlocked;

    public UserModel(String name, String lastname, String username, String email, String phoneNumber, boolean isBlocked) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isBlocked = isBlocked;
    }
}
