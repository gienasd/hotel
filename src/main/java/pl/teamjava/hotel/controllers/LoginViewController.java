package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.teamjava.hotel.models.Session;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.dao.UserDao;
import pl.teamjava.hotel.models.dao.impl.UserDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable{

    private UserDao userdao = new UserDaoImpl();
    private Session userSession = Session.getInstance();
    @FXML
    private TextField textLogin;

    @FXML
    private PasswordField textPassword;

    @FXML
    private Hyperlink labelRegistration;

    @FXML
    private Hyperlink labelPasswordRecovery;

    @FXML
    private Hyperlink labelAuthor;

    @FXML
    private Button buttonLogin,buttonMainPage;


    public void initialize(URL location, ResourceBundle resources) {
      //  buttonMainPage.getStylesheets().add(getClass().getClassLoader().getResource("css/btnLogin.css").toExternalForm());
// buttonLogin.getStylesheets().add(getClass().getClassLoader().getResource("css/btnLogin.css").toExternalForm());

        buttonLogin.setOnMouseClicked(e -> tryLogin());
        labelRegistration.setOnMouseClicked((e -> switchView(labelRegistration,"registerView.fxml",600,420,false)));
       Parent parent= textLogin.getParent();
       parent.addEventHandler(KeyEvent.KEY_PRESSED, e->{
           if (e.getCode() == KeyCode.ENTER) {
              tryLogin();

           }
       });

    }



    private boolean checkLoginData(){
        String login = textLogin.getText();
        String password = textPassword.getText();

        if(login.isEmpty()|| password.isEmpty()){
            Utils.createSimpleDialog("Logowanie","","Pola nie mog± byæ puste !");
        }
        if(login.length()<=3 || password.length() <=5){
            Utils.createSimpleDialog("Logowanie","","Dane za krótkie !");
            textLogin.clear();
            textPassword.clear();
        }
        return  true;
    }
    private void tryLogin()  {
        String login = textLogin.getText();
        String password = textPassword.getText();
        if(!checkLoginData()){
            return;
        }
        if(userdao.login(login,password)){

            userSession.setUsername(login);
            userSession.setLogedIn(true);

       switchView(buttonLogin,"loggedGuestView.fxml",800,500,true);

        }else{
            Utils.createSimpleDialog("Logowanie","","Podano niepoprawne dane !");
        }

    }
    private void switchView(Node node, String fxml, int width, int height, boolean newWindow){
        Stage stage = (Stage)node.getScene().getWindow();
        if(!newWindow) {
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
                stage.setScene(new Scene(root, width, height));
                stage.setResizable(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            stage.close();
            Parent root = null;
            try {

                root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
                Stage newStage = new Stage();
                Scene scene=new Scene(root,width,height);
                newStage.setResizable(false);
                newStage.initStyle(StageStyle.DECORATED);
                newStage.setTitle("Hotel");
                newStage.setScene(scene);
//
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
