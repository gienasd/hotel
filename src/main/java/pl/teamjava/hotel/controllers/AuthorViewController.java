package pl.teamjava.hotel.controllers;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pl.teamjava.hotel.models.Utils;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthorViewController implements Initializable {
    @FXML
    private Hyperlink labelAuthor1;

    @FXML
    private Hyperlink labelAuthor2;

    @FXML
    private Hyperlink labelAuthor3;

    @FXML
    private Hyperlink labelAuthor4;

    public void initialize(URL location, ResourceBundle resources) {
        labelAuthor1.setOnMouseClicked(e -> author1());
        labelAuthor2.setOnMouseClicked(e -> author2());
        labelAuthor3.setOnMouseClicked(e -> author3());
        labelAuthor4.setOnMouseClicked(e -> author4());

    }
    private void author1() {
        Utils.createSimpleDialog("O Autorach :", "Agata Anaszewicz", "Junior Java Developer.Pasjonatka programowania... ");
    }

    private void author2() {
        Utils.createSimpleDialog("O Autorach :", "Paweł Przystarz", "Junior Java Developer.Pasjonat programowania... ");

    }

    private void author3() {

        Utils.createSimpleDialog("O Autorach :", "Lukasz Czereda", "Junior Java Developer.Pasjonat programowania... ");


    }

    private void author4() {
        Utils.createSimpleDialog("O Autorach :", "Lukasz Stafański", "Junior Java Developer.Pasjonat programowania... ");


    }

}
