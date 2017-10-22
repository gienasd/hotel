package pl.teamjava.hotel.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static final String VERSION="1.0";

    public static String shaHash(String message){
        try {
            MessageDigest sha2 = MessageDigest.getInstance("SHA-256");

            byte[] bytesOfMessage = message.getBytes();
            byte[] bytesOfCryptoMessage = sha2.digest(bytesOfMessage);

            StringBuilder stringBuilder = new StringBuilder();

            for(int i = 0; i < bytesOfCryptoMessage.length; i++){
                stringBuilder.append(Integer.toHexString(0xFF & bytesOfCryptoMessage[i]));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String makeHttpRequest(String url){
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url)
                    .openConnection();
            StringBuilder builder = new StringBuilder();
            InputStream inputStream = urlConnection.getInputStream();
            int read;
            while((read = inputStream.read()) != -1){
                builder.append((char) read);
            }

            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void createSimpleDialog(String name, String header, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(name);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.show();
    }
    public  void switchView(Button button, String name){
        Stage stage = (Stage)button.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(name));
            stage.setScene(new Scene(root,600,420));
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void switchView2(Node node, String fxml, int width, int height, boolean newWindow){
        Stage stage = (Stage)node.getScene().getWindow();
        if(!newWindow) {
            Parent root =null;
            try {

                root =  FXMLLoader.load(Utils.class.getResource(fxml));
               // root = loader.load();
                stage.setScene(new Scene(root, width, height));
                stage.setResizable(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            stage.close();
            Parent root = null;
            try {
              root= FXMLLoader.load(Utils.class.getResource(fxml));
               // root = loader.load();
                Stage newStage = new Stage();
                Scene scene=new Scene(root,width,height);
                newStage.setResizable(false);
                newStage.initStyle(StageStyle.DECORATED);
                newStage.setTitle("Hotel");
                newStage.setScene(scene);
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
