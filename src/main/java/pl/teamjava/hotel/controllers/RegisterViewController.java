package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.dao.UserDao;
import pl.teamjava.hotel.models.dao.impl.UserDaoImpl;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by lukasz on 2017-10-01.
 */
public class RegisterViewController implements Initializable {

    private UserDao userdao = new UserDaoImpl();
    @FXML
    private Hyperlink labelAuthorR;

    @FXML
    private Button buttonRegister, buttonMainPageR;

    @FXML
    private TextField textUsernameR, textNameR, textLastnameR, textEmailR, textCodeR, textTelephoneR;

    @FXML
    private PasswordField textPasswordR, textPasswordRepeatR;

    @FXML
    private CheckBox checkBoxTermsAccept, checkBoxEmailsAccept;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonMainPageR.setOnMouseClicked(e -> Utils.switchView2(buttonMainPageR, "/mainView.fxml", 600, 600, true));
        buttonRegister.setOnMouseClicked(e -> checkRegisterData());//tryRegister());

    }

    private void tryRegister() {
        String username = textUsernameR.getText();
        String name = textNameR.getText();
        String lastname = textLastnameR.getText();
        String email = textEmailR.getText();
        String password = textPasswordR.getText();
        String telephone = textTelephoneR.getText();
        String accessCode = textCodeR.getText();
        boolean mailing = checkBoxEmailsAccept.isSelected();


        if (!checkRegisterData()) {
            return;
        }

        if (userdao.register(username, name, lastname, email, password, telephone, accessCode, mailing)) {
            Utils.createSimpleDialog("Rejestracja", "", "Zarejestrowałeś się poprawnie");
        } else {
            Utils.createSimpleDialog("Rejestracja", "", "Podany login już istnieje");
            textNameR.clear();
            textPasswordR.clear();
        }
    }

    private boolean checkRegisterData() {
        String username = textUsernameR.getText();
        String name = textNameR.getText();
        String lastname = textLastnameR.getText();
        String email = textEmailR.getText();
        String password = textPasswordR.getText();
        String passwordRepeat = textPasswordRepeatR.getText();
        String telephone = textTelephoneR.getText();
        String accessCode = textCodeR.getText();
        boolean terms = checkBoxTermsAccept.isSelected();
        if (username.isEmpty() || name.isEmpty() || lastname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Utils.createSimpleDialog("Rejestracja", "", "Pola nie mogą być puste !");
            return false;
        }
        if (username.length() <= 3 || password.length() <= 5) {
            Utils.createSimpleDialog("Rejestracja", "", "Dane są za krótkie !");
            return false;
        }
        if (!password.equals(passwordRepeat)) {
            Utils.createSimpleDialog("Rejestracja", "", "Hasła nie są identyczne !");
            return false;
        }
        if (!terms) {
            Utils.createSimpleDialog("Rejestracja", "", "Musisz zaakceptować regulamin !");
            return false;

        }
        if (!Utils.emailVeryfiction(email)) {

                Utils.createSimpleDialog("Rejestracja", "", "Podałeś błędny adres e-mail");
                return false;
            }
            return true;
        }
    }
