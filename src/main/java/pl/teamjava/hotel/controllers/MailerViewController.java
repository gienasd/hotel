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
import pl.teamjava.hotel.models.MailerModel;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.dao.MailerDao;
import pl.teamjava.hotel.models.dao.impl.MailerDaoImpl;

import javax.mail.*;
import javax.mail.internet.AddressException;
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
MailerModel mailerModel= new MailerModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       readingData();
        buttonSave.setOnAction(e->savingData());
        buttonSend.setOnMouseClicked(e->sendMessage(listRecipients.getSelectionModel().getSelectedItem(),mailerModel));
        try{
        observableRecipientsList=FXCollections.observableList(mailerDao.recipientsList());
        listRecipients.setItems(observableRecipientsList);}
        catch(Exception ex){
            Utils.createSimpleDialog("Błąd","","Nie udało się odczytać listy adresatów !");
        }

    }

    private boolean sendMessage(String selectedRecipient,MailerModel mailerModel) {

        boolean result = false;
        try {
            properties.setProperty("mail.smtp.host", mailerModel.getSmtpHost());
            properties.setProperty("mail.smtp.port", String.valueOf(mailerModel.getSmtpPort()));
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.debug", "true");
            properties.put("mail.store.protocol", "pop3");
            properties.put("mail.transport.protocol", "smtp");
            properties.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailerModel.getUser(), mailerModel.getPassword());
                }
            });


            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mailerModel.getUser()));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(selectedRecipient));
            msg.setFrom(new InternetAddress(mailerModel.getUser()));
            msg.setSubject(mailerModel.getSubject());
            String sb = "<head>" +
                    "<style type=\"text/css\">" +
                    "  .red { color: #f00; }" +
                    "</style>" +
                    "</head>" +
                    "<h1 class=\"red\">" + msg.getSubject() + "</h1>" +
                    "<p>" +
                    "Lorem ipsum dolor sit amet, <em>consectetur</em> adipisicing elit, " +
                    "sed do eiusmod tempor incididunt ut labore et dolore magna <strong>" +
                    "aliqua</strong>.</p>";
            msg.setContent(sb, "text/html; charset=utf-8");
            msg.saveChanges();

                Runnable runnable= () -> {
                    try {
                        Transport.send(msg);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                };
                executorService.execute(runnable);
                return true;





        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return  false;
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
        MailerModel mailerModel = new MailerModel(textEmailLogin.getText(),textEmailPassword.getText(),textSmtpServer.getText(),Integer.valueOf(textSmtpPort.getText()),textEmailSubject.getText(),textEmailContent.getText() );
        mailerDao.changeLogin(mailerModel.getUser());
        mailerDao.changePassword(mailerModel.getPassword());
        mailerDao.changeSmtpServer(mailerModel.getSmtpHost());
        mailerDao.changeSmtpPort(mailerModel.getSmtpPort());
        mailerDao.changeSubject(mailerModel.getSubject());
        System.out.println(mailerModel.getSubject());
      //  mailerDao.changeContent(mailerModel.getContent());
    }

    public MailerViewController() {
        executorService = Executors.newSingleThreadExecutor();
        //  listRecipients = new ArrayList<String>();
    }




}

