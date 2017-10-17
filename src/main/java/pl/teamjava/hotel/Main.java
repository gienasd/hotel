package pl.teamjava.hotel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.teamjava.hotel.models.Utils;

public class Main extends Application {
    ;
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("managmentView.fxml"));
        primaryStage.setTitle("Hotel ver: "+ Utils.VERSION);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(new Scene(root, 600, 430));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
/// LOGIN : admin , HASLO : admin123