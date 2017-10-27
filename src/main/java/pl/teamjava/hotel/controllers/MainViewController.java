package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;
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
        alert.getDialogPane().setContent(node("authorView.fxml"));

        alert.setContentText("JAVATEAM 2017\nKontakt : javateam@java.com");

        alert.showAndWait();
    }
    private Node node(String fxmlPath){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(fxmlPath));
           // Stage stageRoot = (Stage)buttonLogin.getScene().getWindow();
            //stageRoot.close();

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Hotel ver: "+ Utils.VERSION);
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.setScene(new Scene(root, 600, 430));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
Node node =root;
        return node;
    }
    private void registerOpen() {
        try {
          Utils.switchView2(buttonLogin,"/registerView.fxml",600,430,true,StageStyle.UTILITY);
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("registerView.fxml"));
//            Stage stageRoot = (Stage)buttonLogin.getScene().getWindow();
//            stageRoot.close();
//
//            Stage primaryStage = new Stage();
//            primaryStage.setTitle("Hotel ver: "+ Utils.VERSION);
//            primaryStage.initStyle(StageStyle.UTILITY);
//            primaryStage.setScene(new Scene(root, 600, 430));
//            primaryStage.setResizable(false);
//            primaryStage.show();
//

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loginOpen() {
        try {
            Utils.switchView2(buttonLogin,"/loginView.fxml",600,430,true,StageStyle.UTILITY);
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("loginView.fxml"));
//            Stage stageRoot = (Stage)buttonLogin.getScene().getWindow();
//            stageRoot.close();
//
//            Stage primaryStage = new Stage();
//            primaryStage.setTitle("Hotel ver: "+ Utils.VERSION);
//            primaryStage.initStyle(StageStyle.UTILITY);
//            primaryStage.setScene(new Scene(root, 600, 430));
//            primaryStage.setResizable(false);
//            primaryStage.show();
//

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enterAsGuest() {
       try {
            Utils.switchView2(buttonLogin,"/guestView.fxml",800,600,true,StageStyle.DECORATED);
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("guestView.fxml"));
//            Stage stageRoot = (Stage)buttonLogin.getScene().getWindow();
//            stageRoot.close();
//
//            Stage primaryStage = new Stage();
//            primaryStage.setTitle("Hotel ver: "+ Utils.VERSION);
//            primaryStage.initStyle(StageStyle.DECORATED);
//            Scene scene=new Scene(root,800,600);
//            scene.getStylesheets().add(getClass().getClassLoader().getResource("css/main.css").toExternalForm());
//            primaryStage.setScene(scene);
//            primaryStage.setResizable(false);
//            primaryStage.show();
//

       } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
