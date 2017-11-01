package pl.teamjava.hotel.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.teamjava.hotel.models.Session;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.dao.UserDao;
import pl.teamjava.hotel.models.dao.impl.UserDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    private UserDao userdao = new UserDaoImpl();
    private Session userSession = Session.getInstance();
    @FXML
    private TextField textLogin;
@FXML
Label liii;
    @FXML
    private PasswordField textPassword,textAccessCode;

    @FXML
    private Hyperlink labelRegistration, labelAdmin;


    @FXML
    private Hyperlink labelPasswordRecovery;


    @FXML
    private Button buttonLogin, buttonMainPage,buttonLoginByAccessCode;;
    @FXML
    private VBox vBoxAccesCode, vBoxLogin;



    public void initialize(URL location, ResourceBundle resources) {
        vBoxAccesCode.setVisible(false);
        buttonMainPage.setOnMouseClicked(e -> Utils.switchView2(buttonLogin, "/mainView.fxml", 600, 600, true, StageStyle.UTILITY));
        buttonLogin.setOnMouseClicked(e -> tryLogin());
        buttonLoginByAccessCode.setOnMouseClicked(e->tryLoginByAccessCode());
        labelRegistration.setOnMouseClicked((e -> Utils.switchView2(labelRegistration, "/registerView.fxml", 600, 420, false, StageStyle.UTILITY)));
        labelAdmin.setOnMouseClicked(e -> accessCode());
        Parent parent = textLogin.getParent();
        parent.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if ((e.getCode() == KeyCode.ENTER) ){
                tryLogin();
            }
        });
        Parent parent2 = textAccessCode.getParent();
        parent.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if ((e.getCode() == KeyCode.ENTER) ){
                tryLoginByAccessCode();
            }
        });
       // labelAdmin.textProperty().bind(liii.textProperty());
       // BooleanProperty pro=new SimpleBooleanProperty(accessCode());

    }

    private void tryLoginByAccessCode() {
        Alert alert=new Alert(Alert.AlertType.ERROR,"błąd",ButtonType.APPLY);
        alert.showAndWait();
    }

    private boolean accessCode() {
        if (labelAdmin.getText().equals("Panel administracyjny")) {
            labelAdmin.setText("Okno logowania");
            vBoxLogin.setVisible(false);
            vBoxAccesCode.setVisible(true);
            return true;
        } else if  (labelAdmin.getText().equals("Okno logowania"))
            vBoxLogin.setVisible(true);
            vBoxAccesCode.setVisible(false);
            labelAdmin.setText("Panel administracyjny");

            return false;


    }

    private boolean checkLoginData() {
        String login = textLogin.getText();
        String password = textPassword.getText();

        if (login.isEmpty() || password.isEmpty()) {
            Utils.createSimpleDialog("Logowanie", "", "Pola nie mogą być puste !");
        }
        if (login.length() <= 3 || password.length() <= 5) {
            Utils.createSimpleDialog("Logowanie", "", "Dane za krótkie !");
            textLogin.clear();
            textPassword.clear();
        }
        return true;
    }

    private void tryLogin() {
        String login = textLogin.getText();
        String password = textPassword.getText();
        if (!checkLoginData()) {
            return;
        }
        if (userdao.login(login, password)) {

            userSession.setUsername(login);
            userSession.setLogedIn(true);

            Utils.switchView2(buttonLogin, "/reservationView.fxml", 600, 420, true, StageStyle.DECORATED);

        } else {
            Utils.createSimpleDialog("Logowanie", "", "Podano niepoprawne dane !");
        }

    }

}
