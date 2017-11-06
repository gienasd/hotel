package pl.teamjava.hotel;

import com.sun.javafx.css.Style;
import com.sun.javafx.css.StyleManager;
import com.sun.javafx.css.Stylesheet;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.teamjava.hotel.models.Utils;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        StyleManager.getInstance().addUserAgentStylesheet("/css/main.css");
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainView.fxml"));
        primaryStage.setTitle("Hotel ver: "+ Utils.VERSION);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);

    }
}
