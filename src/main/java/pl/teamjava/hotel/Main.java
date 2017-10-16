package pl.teamjava.hotel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    private static String version;
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("guestView.fxml"));
        primaryStage.setTitle("Hotel ver: "+version);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(new Scene(root, 600, 430));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
