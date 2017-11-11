package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by lukasz on 2017-11-09.
 */
public class PasswordRecoveryController implements Initializable {

    @FXML
    private Button buttonMainPage;

    @FXML
    private VBox vBoxLogin;

    @FXML
    private TextField textEmail;

    @FXML
    private Button buttonSend;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        buttonSend.setOnMouseClicked(e->sending(textEmail.getText()));
    }
    private void sending(String recipient){

    }
}
