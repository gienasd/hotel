package pl.teamjava.hotel.models.dao;

import pl.teamjava.hotel.models.MailerModel;

import java.util.List;

/**
 * Created by lukasz on 2017-11-04.
 */
public interface MailerDao {
  List<String>recipientsList();
 // List<String> mailer(MailerModel mailerModel);
   String readLogin();
  boolean changeLogin(String login);
    String readPassword();
    boolean changePassword(String password);
    String readSmtpServer();
    boolean changeSmtpServer(String smtpServer);
    int readSmtpPort();
    boolean changeSmtpPort(int smtpPort);
    String readSubject();
    boolean changeSubject(String subject);
    String readContent();
    boolean changeContent(String content);
}
