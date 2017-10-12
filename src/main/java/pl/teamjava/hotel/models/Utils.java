package pl.teamjava.hotel.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

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
                    .openConnection();//otwiera połączenie do strony
            StringBuilder builder = new StringBuilder();
            InputStream inputStream = urlConnection.getInputStream();
            int read = 0;
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

}
