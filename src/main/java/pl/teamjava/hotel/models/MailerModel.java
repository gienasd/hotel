package pl.teamjava.hotel.models;

import javafx.collections.ObservableList;

import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

/**
 * Created by lukasz on 2017-11-05.
 */
public class MailerModel {

    private String user;
    private String password;
    private String smtpHost;
    private int smtpPort;
    private String subject;
    private String content;
    private List<String> recepients;


    public MailerModel(String user, String password, String smtpHost, int smtpPort, String subject, String content) {

        this.user = user;
        this.password = password;
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.subject = subject;
        this.content = content;

    }

    public MailerModel() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    private void sendMessage(String recepient) {


    }
}