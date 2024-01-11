package lk.ijse.finalproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalproject.dto.RegisterDto;
import lk.ijse.finalproject.dao.RegisterModel;
import lk.ijse.finalproject.util.Navigation;
import lk.ijse.finalproject.util.Route;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class ForgotPasswordController {
    public AnchorPane forgetPasswordPane;
    public TextField txtuserName;
    public TextField txtMail;
    public JFXButton btnCancel;
    public JFXButton btnContinue;
    public RegisterModel registerModel = new RegisterModel();

    // creating first MimeBodyPart object
    BodyPart messageBodyPart1 = new MimeBodyPart();
    // creating MultiPart object
    Multipart multipartObject = new MimeMultipart();
    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.LOGIN, forgetPasswordPane);
    }
    public void btnContinueOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String username = txtuserName.getText();
        String mail = txtMail.getText();
        var dto = new RegisterDto(username, mail);
        String password = registerModel.searchPassword(dto).getPassword();
        boolean isMatched = registerModel.matchmails();
        if (isMatched) {
            String to = "dnemasha@gmail.com";
            String from = "dnemasha@gmail.com";
            String messageText = "Please update your password.";

            Properties properties = System.getProperties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            // Create a Session object
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("dnemasha@gmail.com", "asup vpfz prxo nuom  ");
                }
            });
            session.setDebug(true);
            try {
                //create a default mimemessage object
                MimeMessage mimeMessage = new MimeMessage(session);
                //set from: set the header feild of header
                mimeMessage.setFrom(new InternetAddress(from));
                //set To:
                mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to)));
                mimeMessage.setSubject("Your Password");
                System.out.println(password);
                BodyPart messageBodyPart1 = new MimeBodyPart();
                // creating MultiPart object
                Multipart multipartObject = new MimeMultipart();

                messageBodyPart1.setText("Your Password: " + password + "\n\nThank you\nAdmin");
                multipartObject.addBodyPart(messageBodyPart1);
                // set body of the email.
                mimeMessage.setContent(multipartObject);
                Transport.send(mimeMessage);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}