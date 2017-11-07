package pl.teamjava.hotel.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.dao.MailerDao;
import pl.teamjava.hotel.models.dao.impl.MailerDaoImpl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
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
    private TextField textSmtpPort, textSmtpServer;
    @FXML
    private Menu menuSettings;


    private ObservableList<String> observableRecipientsList;

    private ExecutorService executorService;
    private Session session;
    private Properties properties;

    MailerDao mailerDao = new MailerDaoImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readingData();
        buttonSave.setOnMouseClicked(e->savingData());
        buttonSend.setOnMouseClicked(e->sendMessage(listRecipients.getSelectionModel().getSelectedItem()));
        try{
        observableRecipientsList=FXCollections.observableList(mailerDao.recipientsList());
        listRecipients.setItems(observableRecipientsList);}
        catch(Exception ex){
            Utils.createSimpleDialog("Błąd","","Nie udało się odczytać listy adresatów !");
        }

    }

    private void readingData() {
        textEmailLogin.setText(mailerDao.readLogin());
        textEmailPassword.setText(mailerDao.readPassword());
        textSmtpServer.setText(mailerDao.readSmtpServer());
        textSmtpPort.setText(String.valueOf(mailerDao.readSmtpPort()));
        textEmailSubject.setText(mailerDao.readSubject());
        textEmailContent.appendText(mailerDao.readContent());
    }

    private void savingData() {
        mailerDao.changeLogin(textEmailLogin.getText());
        mailerDao.changePassword(textEmailPassword.getText());
        mailerDao.changeSmtpServer(textSmtpServer.getText());
        mailerDao.changeSmtpPort(Integer.valueOf(textSmtpPort.getText()));
        mailerDao.changeSubject(textEmailSubject.getText());
        mailerDao.changeContent(textEmailContent.getText());
    }

    public MailerViewController() {
        executorService = Executors.newSingleThreadExecutor();
        //  listRecipients = new ArrayList<String>();
    }


    private void sendMessage(String recepient) {

        properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", textSmtpServer.getText());
           properties.put("mail.smtp.port", textSmtpPort.getText());

        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(textEmailLogin.getText(), textEmailPassword.getText());
                    }
                });


        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(textEmailLogin.getText()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("lcacbcac@gmail.com"));
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

