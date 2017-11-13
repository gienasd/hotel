package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import pl.teamjava.hotel.models.Utils;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by lukasz on 2017-11-09.
 */
public class PasswordRecoveryController implements Initializable {

    @FXML
    private Button buttonMainPage;



    @FXML
    private TextField textEmail;

    @FXML
    private Button buttonSend;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
buttonMainPage.setOnMouseClicked(e->Utils.logoutToMainPage(buttonMainPage));
        buttonSend.setOnMouseClicked(e->System.out.println(Utils.passwordGenerator()));//e->sending(textEmail.getText(), Utils.passwordGenerator()));
        System.out.println(Utils.passwordGenerator());
    }
    private void sending(String recipient,String password){

    }
}
