package pl.teamjava.hotel.models;

import lombok.Data;

@Data
public class ManagmentModel extends UserModel {
    private String accessCode;
    private String password;
    boolean  mailing;

    public ManagmentModel(String name, String lastname, String username, String email, String phoneNumber, boolean isBlocked, String accessCode,String password,boolean mailing) {
        super(name, lastname, username, email, phoneNumber, isBlocked);
        this.accessCode = accessCode;
        this.password = Utils.shaHash(password);
        this.mailing=mailing;

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

    public boolean getMailing() {
        return mailing;
    }

    public void setMailing(boolean mailing) {
        this.mailing = mailing;
    }
}
