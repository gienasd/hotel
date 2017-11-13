package pl.teamjava.hotel.controllers;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.StageStyle;
import org.apache.commons.codec.binary.Base64;
import pl.teamjava.hotel.models.MailerModel;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.dao.MailerDao;
import pl.teamjava.hotel.models.dao.impl.MailerDaoImpl;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.*;
import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
    private Button bt;

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


    MailerDao mailerDao = new MailerDaoImpl();
    MailerModel mailerModel = new MailerModel();

    public MailerViewController() {
        executorService = Executors.newSingleThreadExecutor();
        //  listRecipients = new ArrayList<String>();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readingData();
        mailerModel = new MailerModel(textEmailLogin.getText(), textEmailPassword.getText(), textSmtpServer.getText(), Integer.valueOf(textSmtpPort.getText()), textEmailSubject.getText(), textEmailContent.getText());

        buttonSave.setOnAction(e -> savingData());
        buttonSend.setOnMouseClicked(e -> sendMessage("lcacbcac@gmail.com", mailerModel));
        try {
            observableRecipientsList = FXCollections.observableList(mailerDao.recipientsList());
            listRecipients.setItems(observableRecipientsList);
        } catch (Exception ex) {
            Utils.createSimpleDialog("Błąd", "", "Nie udało się odczytać listy adresatów !");
        }

    }


    private boolean sendMessage(String recipient, MailerModel mailerModel) {


        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", mailerModel.getSmtpHost());
        properties.put("mail.smtp.port", String.valueOf(mailerModel.getSmtpPort()));
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.debug", "true");
//            properties.put("mail.store.protocol", "pop3");

        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailerModel.getUser(), mailerModel.getPassword());
                    }
                });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mailerModel.getUser()));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

            msg.setSubject(mailerModel.getSubject());
           List<String> lines=(Files.readAllLines(Paths.get("C:\\Users\\lukasz\\Desktop\\PROGRAM_JAVATEAM\\hotel\\src\\main\\resources\\templates\\index.html")));
            StringBuilder builder=new StringBuilder();
            lines.forEach((String s)->builder.append(s));

              msg.setContent(builder.toString(), "text/html; charset=utf-8");
            msg.saveChanges();

            Runnable runnable = () -> {
                try {
                    Transport.send(msg);
                    sendingProgressBar.setProgress(1.0);
                    Platform.runLater(() -> Utils.createSimpleDialog("Mailer", "", "Wiadomość została wysłana !"));
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            };
            executorService.execute(runnable);
            sendingProgressBar.setProgress(0.0);
            return true;


        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
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
        MailerModel mailerModel = new MailerModel(textEmailLogin.getText(), textEmailPassword.getText(), textSmtpServer.getText(), Integer.valueOf(textSmtpPort.getText()), textEmailSubject.getText(), textEmailContent.getText());
        mailerDao.changeLogin(mailerModel.getUser());
        mailerDao.changePassword(mailerModel.getPassword());
        mailerDao.changeSmtpServer(mailerModel.getSmtpHost());
        mailerDao.changeSmtpPort(mailerModel.getSmtpPort());
        mailerDao.changeSubject(mailerModel.getSubject());
        mailerDao.changeContent(mailerModel.getContent());
    }



}

