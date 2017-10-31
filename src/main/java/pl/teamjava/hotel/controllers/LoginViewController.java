package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private PasswordField textPassword;

    @FXML
    private Hyperlink labelRegistration, labelAdmin;
    ;

    @FXML
    private Hyperlink labelPasswordRecovery;


    @FXML
    private Button buttonLogin, buttonMainPage;
    @FXML
    private VBox vBoxAccesCode, vBoxLogin;

    @FXML
    private PasswordField textAccessCode;

    public void initialize(URL location, ResourceBundle resources) {
        vBoxAccesCode.setVisible(false);
        buttonMainPage.setOnMouseClicked(e -> Utils.switchView2(buttonLogin, "/mainView.fxml", 600, 600, true, StageStyle.UTILITY));
        buttonLogin.setOnMouseClicked(e -> tryLogin());
        labelRegistration.setOnMouseClicked((e -> Utils.switchView2(labelRegistration, "/registerView.fxml", 600, 420, false, StageStyle.UTILITY)));
        labelAdmin.setOnMouseClicked(e -> access());
        Parent parent = textLogin.getParent();
        parent.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER) {
                tryLogin();
            }
        });
    }

    private void access() {
        if (labelAdmin.getText().equals("Okno logowania")) {
            vBoxLogin.setVisible(true);
            vBoxAccesCode.setVisible(false);
            labelAdmin.setText("Panel administracyjny");
        } else {
            vBoxLogin.setVisible(false);
            vBoxAccesCode.setVisible(true);
            labelAdmin.setText("Okno logowania");
        }
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

            Utils.switchView2(buttonLogin, "/reservationGuestView.fxml", 800, 500, true, StageStyle.DECORATED);

        } else {
            Utils.createSimpleDialog("Logowanie", "", "Podano niepoprawne dane !");
        }

    }

}
