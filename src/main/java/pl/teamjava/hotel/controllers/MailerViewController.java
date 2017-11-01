package pl.teamjava.hotel.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import pl.teamjava.hotel.models.Utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by lukasz on 2017-11-01.
 */
public class MailerViewController implements Initializable{

    @FXML
    private ProgressBar sendingProgressBar;

    @FXML
    private ListView<String> listRecipments;

    @FXML
    private Button buttonSend;
    @FXML
    private TextField textRecipient;


    private void mailSending(String recipient){
        String destmailid = recipient;
        String sender="";
        String smtpHost="";
        String smtpPort ="25";
        final String user = "";
        final String pwd = "";
        Properties properties=new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);

        Session sessionobj = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pwd);
                    }
                });
        try {
            //Create MimeMessage object & set values
            Message messageobj = new MimeMessage(sessionobj);
            messageobj.setFrom(new InternetAddress(destmailid));
            messageobj.setRecipients(Message.RecipientType.TO,InternetAddress.parse(sender));
            messageobj.setSubject("This is test Subject");
            messageobj.setText("Checking sending emails by using JavaMail APIs");
            //Now send the message
            Transport transport=sessionobj.getTransport("smtp");
            transport.connect(smtpHost,25,user,pwd);
            Transport.send(messageobj);
            transport.close();
            Utils.createSimpleDialog("Mailer","","Udało sie wysłać maila");
        } catch (MessagingException exp) {
            throw new RuntimeException(exp);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
buttonSend.setOnMouseClicked(e->mailSending(textRecipient.getText()));
    }
}
