package pl.teamjava.hotel.models.dao;

import java.util.List;

/**
 * Created by lukasz on 2017-11-04.
 */
public interface MailerDao {
  List<String>recipientsList();
   String readLogin();
   boolean changeLogin(String login);
    String readPassword();
    boolean changePassword(String password);
    String readSmtpServer();
    boolean changeSmtpServer(String server);
    int readSmtpPort();
    boolean changeSmtpPort(int port );
    String readSubject();
    boolean changeSubject(String subject);
    String readContent();
    boolean changeContent(String content);
}
