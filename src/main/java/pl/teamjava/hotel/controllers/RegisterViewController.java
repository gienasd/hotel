package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.StageStyle;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.dao.UserDao;
import pl.teamjava.hotel.models.dao.impl.UserDaoImpl;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lukasz on 2017-10-01.
 */
public class RegisterViewController implements Initializable {

    private UserDao userdao = new UserDaoImpl();


    @FXML
    private Button buttonRegister, buttonMainPageR;

    @FXML
    private TextField textUsernameR, textNameR, textLastnameR, textEmailR, textAccessCodeR, textPhoneNumberR;

    @FXML
    private PasswordField textPasswordR, textPasswordRepeatR;

    @FXML
    private CheckBox checkBoxTermsAccept, checkBoxEmailsAccept;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonMainPageR.setOnMouseClicked(e -> Utils.switchView2(buttonMainPageR, "mainView.fxml", 600, 600, true, StageStyle.DECORATED,true));
        buttonRegister.setOnMouseClicked(e -> tryRegister());

    }

    private void tryRegister() {
        String username = textUsernameR.getText();
        String name = textNameR.getText();
        String lastname = textLastnameR.getText();
        String email = textEmailR.getText();
        String password = textPasswordR.getText();
        String phoneNumber = textPhoneNumberR.getText();
        String accessCode = textAccessCodeR.getText();
        boolean mailing = checkBoxEmailsAccept.isSelected();


        if (!checkRegisterData()) {
            return;
        }

        if (userdao.register( name, lastname,username, email,  phoneNumber,false, accessCode, password,mailing)) {
            Utils.createSimpleDialog("Rejestracja", "", "Zarejestrowałeś się poprawnie");
            textUsernameR.clear();
            textNameR.clear();
            textLastnameR.clear();
            textEmailR.clear();
            textPasswordR.clear();
            textPasswordRepeatR.clear();
            textPhoneNumberR.clear();
            textAccessCodeR.clear();
            checkBoxTermsAccept.setSelected(false);
             checkBoxEmailsAccept.setSelected(false);
        }
    }

    private boolean checkRegisterData() {
        String username = textUsernameR.getText();
        String name = textNameR.getText();
        String lastname = textLastnameR.getText();
        String email = textEmailR.getText();
        String password = textPasswordR.getText();
        String passwordRepeat = textPasswordRepeatR.getText();
        String phoneNumber = textPhoneNumberR.getText();
        //String accessCode = textAccessCodeR.getText();
        boolean terms = checkBoxTermsAccept.isSelected();
        Pattern namePattern= Pattern.compile("^[\\p{L} .'-]+$");
        Matcher nameMatcher=namePattern.matcher(name);
        Matcher lastnameMatcher = namePattern.matcher(lastname);
        if(!nameMatcher.matches()||!lastnameMatcher.matches()){
            Utils.createSimpleDialog("Rejestracja", "", "Błędnie wypełnione pola : \"Imię\" lub \"Nazwisko\"");
            return false;
        }
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
        Pattern teleponePattern= Pattern.compile("\\d{13}|\\d{9}|\\+\\d{11}");
        Matcher phoneMatcher = teleponePattern.matcher(phoneNumber);
        if(!phoneMatcher.matches()&&(!(textPhoneNumberR.getText().isEmpty()))){
            Utils.createSimpleDialog("Rejestracja", "", "Podałeś błędny numer telefonu");
            return false;
        }
            return true;
        }
    }
