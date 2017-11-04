package pl.teamjava.hotel.controllers;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import pl.teamjava.hotel.models.Utils;

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
    private ProgressBar sendingProgressBar;

    @FXML
    private ListView<String> listRecipments;

    @FXML
    private Button buttonSend;
    @FXML
    private TextField textRecipient;

    private final String smtpHost = "poczta.interia.pl";
    private final int smtpPort = 465;
    private final String user = "hansonq@interia.pl";
    private final String pwd = "Lolek13579";
    private ObservableList<String> observableList;

    private ExecutorService executorService;
    private Session session;
    private Properties properties;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (listRecipments.:
             ) {
            
        }
        buttonSend.setOnMouseClicked(listRecipments));
    }

    public MailerViewController() {
        executorService = Executors.newSingleThreadExecutor();
        listRecipments = new ArrayList<String>();
    }



    private void sendMessage(String messageText, String recipient, String sender) {

        properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);

        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pwd);
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
}
