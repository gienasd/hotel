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
   boolean changeLogin(MailerModel mailerModel,String login);
    String readPassword();
    boolean changePassword(MailerModel mailerModel,String password);
    String readSmtpServer();
    boolean changeSmtpServer(MailerModel mailerModel,String smtpServer);
    int readSmtpPort();
    boolean changeSmtpPort(MailerModel mailerModel,int smtpPort);
    String readSubject();
    boolean changeSubject(MailerModel mailerModel,String subject);
    String readContent();
    boolean changeContent(MailerModel mailerModel,String content);
}
