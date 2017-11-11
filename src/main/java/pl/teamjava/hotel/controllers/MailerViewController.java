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
        textEmailLogin.setText(mailerModel.getUser());
        textEmailPassword.setText(mailerModel.getPassword());
        textSmtpServer.setText(mailerModel.getSmtpHost());
        textSmtpPort.setText(String.valueOf(mailerModel.getSmtpPort()));
        textEmailSubject.setText(mailerModel.getSubject());
        textEmailContent.appendText(mailerModel.getContent());
    }

    private void savingData() {
        MailerModel mailerModel = new MailerModel(textEmailLogin.getText(),textEmailPassword.getText(),textSmtpServer.getText(),Integer.valueOf(textSmtpPort.getText()),textEmailSubject.getText(),textEmailContent.getText() );

    }

    public MailerViewController() {
        executorService = Executors.newSingleThreadExecutor();
        //  listRecipients = new ArrayList<String>();
    }




}

