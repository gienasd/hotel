package pl.teamjava.hotel.models;

public class ManagmentModel extends UserModel {
    private String accessCode;
    private String password;

    public ManagmentModel(String name, String lastname, String username, String email, String phoneNumber, boolean isBlocked, String accessCode,String password) {
        super(name, lastname, username, email, phoneNumber, isBlocked);
        this.accessCode = accessCode;
        this.password = Utils.shaHash(password);
    }

    public String getAccessCode() {
        return accessCode;
    }
    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Utils.shaHash(password);
    }
}
