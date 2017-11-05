package pl.teamjava.hotel.models;

import javafx.collections.ObservableList;

/**
 * Created by lukasz on 2017-11-05.
 */
public class MailerModel {
    private String user;
    private  String password;
    private String smtpHost;
    private int smtpPort;

    private ObservableList<String> observableRecipientsList;

    public MailerModel(String user, String password, String smtpHost, int smtpPort) {
        this.user = user;
        this.password = password;
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    public MailerModel() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }
}
