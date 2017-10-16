package pl.teamjava.hotel.models;

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

    public UserModel() {
    }

    public UserModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isBlocked() {
        return isBlocked;
    }
    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
