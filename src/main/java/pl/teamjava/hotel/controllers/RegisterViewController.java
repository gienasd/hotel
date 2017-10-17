package pl.teamjava.hotel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pl.teamjava.hotel.models.Utils;
import pl.teamjava.hotel.models.dao.UserDao;
import pl.teamjava.hotel.models.dao.impl.UserDaoImpl;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by lukasz on 2017-10-01.
 */
public class RegisterViewController implements Initializable {

    private UserDao userdao = new UserDaoImpl();
    @FXML
    private Hyperlink labelAuthorR;

    @FXML
    private Button buttonRegister,buttonMainPageR;

    @FXML
    private TextField textUsernameR,textNameR,textLastnameR,textEmailR,textCodeR,TelephoneR;

    @FXML
    private PasswordField textPasswordR,textPasswordRepeatR;

    @FXML
    private CheckBox checkBoxTermsAccept,checkBoxEmailsAccept;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonMainPageR.setOnMouseClicked(e-> Utils.switchView2(buttonMainPageR,"/mainView.fxml",600,600,true));
        buttonRegister.setOnMouseClicked(e->tryRegister());

    }

    private void tryRegister() {
        String username = textUsernameR.getText();
        String name = textNameR.getText();
        String lastname = textLastnameR.getText();
        String email = textEmailR.getText();
        String password = textPasswordR.getText();
        String telephone = TelephoneR.getText();
        String accessCode = textCodeR.getText();
        boolean mailing = checkBoxEmailsAccept.isSelected();


        if(!checkRegisterData()){
            return;
        }

        if(userdao.register(username,name,lastname,email,password,telephone,accessCode,mailing)){
            Utils.createSimpleDialog("Rejestracja", "","Zarejestrowałeś się poprawnie");
        }else{
            Utils.createSimpleDialog("Rejestracja", "","Podany login już istnieje");
            textNameR.clear();
            textPasswordR.clear();
        }
    }

    private boolean checkRegisterData() {
        return false;
    }
}
