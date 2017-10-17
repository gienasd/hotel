package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.teamjava.hotel.models.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private Button buttonEnterAsGuest, buttonRegister, buttonLogin;


    @FXML
    private Hyperlink labelAuthor;

    public void initialize(URL location, ResourceBundle resources) {
        buttonEnterAsGuest.setOnMouseClicked(e -> enterAsGuest());
        buttonLogin.setOnMouseClicked(e -> loginOpen());
        buttonRegister.setOnMouseClicked(e -> registerOpen());
        labelAuthor.setOnMouseClicked(e -> authorsOpen());

    }

    private void authorsOpen() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hotel");
        alert.setHeaderText("O Autorach");

        alert.setContentText("JAVATEAM 2017\nKontakt : javateam@java.com");

        alert.showAndWait();
    }

    private void registerOpen() {
        try {

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("registerView.fxml"));
            Stage stageRoot = (Stage)buttonLogin.getScene().getWindow();
            stageRoot.close();

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Hotel ver: "+ Utils.VERSION);
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.setScene(new Scene(root, 600, 430));
            primaryStage.setResizable(false);
            primaryStage.show();
//

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loginOpen() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("loginView.fxml"));
            Stage stageRoot = (Stage)buttonLogin.getScene().getWindow();
            stageRoot.close();

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Hotel ver: "+ Utils.VERSION);
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.setScene(new Scene(root, 600, 430));
            primaryStage.setResizable(false);
            primaryStage.show();
//

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void enterAsGuest() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("guestView.fxml"));
            Stage stageRoot = (Stage)buttonLogin.getScene().getWindow();
            stageRoot.close();

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Hotel ver: "+ Utils.VERSION);
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.setResizable(false);
            primaryStage.show();
//

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
