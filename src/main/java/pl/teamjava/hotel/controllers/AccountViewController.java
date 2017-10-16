package pl.teamjava.hotel.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.teamjava.hotel.models.Session;
import pl.teamjava.hotel.models.dao.UserDao;
import pl.teamjava.hotel.models.dao.impl.UserDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static pl.teamjava.hotel.models.GuestUtils.*;

public class AccountViewController implements Initializable {

    @FXML
    TextField textName, textLastName, textUserName, textMail, textPhoneNumber;

    @FXML
    PasswordField textPassword;

    @FXML
    ListView<String> listViewUser;

    @FXML
    Button buttonBack;

    private UserDao userDao = new UserDaoImpl();
    private ObservableList<String> historyList;
    private Session userSession = Session.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editableData(textName, textLastName, textUserName, textMail, textPhoneNumber);
        loadData(textName, textLastName, textUserName, textMail, textPhoneNumber);
        updateAllData(textName, textLastName, textUserName, textMail, textPhoneNumber);
        buttonBack.setOnMouseClicked(s -> tryBack(buttonBack));
        showHistory(historyList,listViewUser);
    }

    private void updateAllData(TextField name, TextField lastName, TextField username, TextField mail, TextField phoneNumber) {
        updateData(name);
        updateData(lastName);
        updateData(username);
        updateData(mail);
        updateData(phoneNumber);
    }


    private void updateData(TextField field) {
        field.setOnMouseClicked(s -> {
            if(s.getClickCount() >= 2){
                field.setEditable(true);
            }
        });

        field.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue){
                userDao.editAccount(textName.getText(),textLastName.getText(),textUserName.getText(),textMail.getText(),textPhoneNumber.getText(),userSession.getAccessCode());
                loadData(textName, textLastName, textUserName, textMail, textPhoneNumber);
                field.setEditable(false);
            }
        });
    }

    private void tryBack(Button button) {
        Stage stage = (Stage)button.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("reservationGuestView.fxml"));
            stage.setScene(new Scene(root,600,400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
