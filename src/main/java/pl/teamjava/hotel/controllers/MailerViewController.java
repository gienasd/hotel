package pl.teamjava.hotel.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.dao.MailerDao;
import pl.teamjava.hotel.models.dao.impl.MailerDaoImpl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lukasz on 2017-11-01.
 */
public class MailerViewController implements Initializable {
    @FXML
    private TextField textEmailLogin;

    @FXML
    private PasswordField textEmailPassword;

    @FXML
    private TextField textEmailSubject;

    @FXML
    private TextArea textEmailContent;

    @FXML
    private Button buttonSave, buttonSend;
    @FXML
    private ProgressBar sendingProgressBar;

    @FXML
    private ListView<String> listRecipients;

    @FXML
    private TextField textRecipient, textSmtpPort, textSmtpServer;


    private ObservableList<String> observableRecipientsList;

    private ExecutorService executorService;
    private Session session;
    private Properties properties;

    MailerDao mailerDao = new MailerDaoImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textRecipient.setText(mailerDao.readLogin());
        textEmailPassword.setText(mailerDao.readPassword());
        textSmtpServer.setText(mailerDao.readSmtpServer());
        textSmtpPort.setText(String.valueOf(mailerDao.readSmtpPort()));
        textEmailSubject.setText(mailerDao.readSubject());
        textEmailContent.appendText(mailerDao.readContent());
        //    buttonSend.setOnMouseClicked(listRecipients));
    }

    public MailerViewController() {
        executorService = Executors.newSingleThreadExecutor();
        //  listRecipients = new ArrayList<String>();
    }


    private void sendMessage(String messageText, String recipient, String sender) {

        properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        //    properties.put("mail.smtp.host", smtpHost);
        //   properties.put("mail.smtp.port", smtpPort);

        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("user", "pwd");
                    }
                });


        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(recipient));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sender));
            message.setSubject("This is test Subject");
            message.setText("Checking sending emails by using JavaMail APIs");
            //Now send the message
            Runnable runnable = () -> {
                try {
                    Transport.send(message);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            };
            executorService.execute(runnable);
            Utils.createSimpleDialog("Mailer", "", "Udało sie wysłać maila");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


}

