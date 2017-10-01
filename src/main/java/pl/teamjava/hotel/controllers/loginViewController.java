package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class loginViewController implements Initializable{
    @FXML
    private TextField textLogin;

    @FXML
    private PasswordField textPassword;

    @FXML
    private Hyperlink labelRegistration;

    @FXML
    private Hyperlink labelPasswordRecovery;

    @FXML
    private Hyperlink labelAuthor;

    @FXML
    private Button buttonLogin,buttonMainPage;


    public void initialize(URL location, ResourceBundle resources) {

    }
}
