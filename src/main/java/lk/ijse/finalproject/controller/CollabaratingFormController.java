package lk.ijse.finalproject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.*;
import lk.ijse.finalproject.model.CollabaratingModel;
import lk.ijse.finalproject.model.EventDesignModel;
import lk.ijse.finalproject.model.ServiceModel;
import lk.ijse.finalproject.model.VendorModel;
import lk.ijse.finalproject.util.Navigation;
import lk.ijse.finalproject.util.Route;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.Notifications;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.BreakIterator;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CollabaratingFormController {
    public AnchorPane collabaratingPane;
    public JFXButton btnHome;
    public JFXButton btnVendors;
    public JFXButton btnService;
    public JFXButton btnCollaborating;
    public JFXButton btnLogOut;
    public JFXComboBox cmbEventId;
    public JFXComboBox cmbVendorId;
    public JFXComboBox cmbServiceId;
    public TextField txtDte;
    public TextField txtTime;
    public TextField txtDescription;
    public TextField txtPrice;
    public JFXButton btnSave;
    public JFXButton btnSend;
    public JFXButton btnDelete;
    public JFXButton btnClear;
    public JFXButton btnCancel;
    public EventDesignModel eventDesignModel=new EventDesignModel();
    public ServiceModel serviceModel=new ServiceModel();
    public VendorModel vendorModel=new VendorModel();
    public CollabaratingModel collabaratingModel=new CollabaratingModel();
    public JFXButton btnUpdate;
    public JFXComboBox cmbEmail;
    public TableView tblBestCollabartion;
    public TableColumn colService;
    public TableColumn colvendors;
    public JFXButton btnprint;
    public Label lbldate;
    public Label lblTime;
    public TextField eid;
    public TextField vid;
    public TextField sid;
    public Label lblVendorId;
    public Label lblNAme;
    public TextField txtsearch;


    //  BodyPart messageBodyPart1 = new MimeBodyPart();
    // creating MultiPart object
    //Multipart multipartObject = new MimeMultipart();


    public void initialize() throws SQLException, ClassNotFoundException {
        loadAllVendorsId();
        loadAllServiceId();
        loadAllEventId();
        loadAllEmail();
        getCurrentDate();
        getCurrentTime();

    }

    private void getCurrentTime() {
        try{
            LocalTime time=LocalTime.now();
            lblTime.setText(String.valueOf(time));
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }

    }
    private void getCurrentDate() {
        try{
            LocalDate date=LocalDate.now();
            lbldate.setText(String.valueOf(date));
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    private void loadAllEventId() throws SQLException, ClassNotFoundException {
        ObservableList<String> oblist = FXCollections.observableArrayList();
        try {
            ArrayList<EventDesignDto> dtos = eventDesignModel.getAllCurrentEvents();
            for (EventDesignDto dto : dtos) {
                oblist.add(dto.getEid());
            }
            cmbEventId.setItems(oblist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    private void loadAllServiceId() {
        ObservableList<String> oblist = FXCollections.observableArrayList();
        try {
            List<ServiceDto> dto =serviceModel.getAllServices();
            for (ServiceDto dtos : dto) {
                oblist.add(dtos.getSid());
            }
            cmbServiceId.setItems(oblist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    private void loadAllVendorsId() {
        ObservableList<String> oblist = FXCollections.observableArrayList();
        try {
            List<VendorDto> dto =vendorModel.getAllVendors();
            for (VendorDto dtos : dto) {
                oblist.add(dtos.getId());
            }
            cmbVendorId.setItems(oblist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    private void loadAllEmail() {
        ObservableList<String> oblist = FXCollections.observableArrayList();
        try {
            List<VendorDto> dto =vendorModel.getAllVendors();
            for (VendorDto dtos : dto) {
                oblist.add(dtos.getEmail());
            }
            cmbEmail.setItems(oblist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.TEAMLEADER_DASHBOARD,collabaratingPane);
    }

    public void btnManageVendors(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.VENDORS,collabaratingPane);
    }

    public void btnServiceOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.SERVICE,collabaratingPane);

    }

    public void btnCollabaratingOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.VENDOR_COLLABARATING,collabaratingPane);

    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.LOGIN,collabaratingPane);

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
      String sId= String.valueOf(cmbServiceId.getValue());
      String eId=String.valueOf(cmbEventId.getValue());
      String vId= String.valueOf(cmbVendorId.getValue());
      String desc=txtDescription.getText();
        String descRegex = "^[A-Za-z\\s]{1,200}$";
        if (!desc.matches(descRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid  description format. Please enter a valid description.").show();
            return;
        }
      LocalTime time= LocalTime.parse(txtTime.getText());
        System.out.println("methand");
      LocalDate date= LocalDate.parse(txtDte.getText());
        System.out.println("mekad");
      double price= Double.parseDouble(txtPrice.getText());
        System.out.println("mokkd");


        var dto=new CollabaratingDto(sId,eId,vId,desc,time,date,price);
      System.out.println("jjjj");
     // Connection connection=null;
      try{
          //connection=
          boolean isSaved=collabaratingModel.saveCollabaring(dto);
          if(isSaved){
              new Alert(Alert.AlertType.CONFIRMATION,"save collabarating").showAndWait();
              System.out.println("mmmmm");
          }
          System.out.println("iiii");
      }catch (Exception e){
          new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
          System.out.println("nnnn");
      }
        System.out.println("oooo");
    }

    public void btnSendOnAction(ActionEvent actionEvent) throws MessagingException {

        try{
        String to = String.valueOf(cmbEmail.getValue());
        String from = "dnemasha@gmail.com";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.user", "Dewni");
        properties.put("mail.smtp.password", "asup vpfz prxo nuom");


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("dnemasha@gmail.com", "asup vpfz prxo nuom ");
            }
        });
        session.setDebugOut(new PrintStream(System.out));

        session.setDebug(true);


        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(from));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        mimeMessage.setSubject("invitation");

        Multipart multipartObject = new MimeMultipart();

        MimeBodyPart messageBodyPart1 = new MimeBodyPart();
        messageBodyPart1.setText("we are inviting to collaborate with us for our new event. we are hoping to hear from you. send me  email (dnemasha@gmail.com) if you are interested in this event");

        multipartObject.addBodyPart(messageBodyPart1);

        mimeMessage.setContent(multipartObject);

        Transport.send(mimeMessage);

        Notifications.create()
                .title("Sending Email")
                .text("Email sent successfully")
                .hideAfter(Duration.seconds(5))
                .graphic(new ImageView(new Image("file:lk/ijse/finalproject/icon/confetti.jpeg")))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(event -> System.out.println("Click on the notification"))
                .showConfirm();
    }catch(Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id= String.valueOf(cmbEventId.getValue());
        try{
            boolean isDeleted=collabaratingModel.deleteCollabaration(id);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"collabaration is deleted").show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,"collabaration is not deleted");
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();

    }
    private void clearFields() {
        txtDescription.setText("");
        txtTime.setText("");
        txtDte.setText("");
        txtPrice.setText("");
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (validateFields()) {

            String sid = String.valueOf(cmbServiceId.getValue());
            String vid = String.valueOf(cmbVendorId.getValue());
            String eid = String.valueOf(cmbEventId.getValue());
            String desc = txtDescription.getText();
            LocalTime time = LocalTime.parse(txtTime.getText());
            LocalDate date = LocalDate.parse(txtDte.getText());
            double price = Double.parseDouble(txtPrice.getText());
            var dto = new CollabaratingDto(sid, vid, eid, desc, time, date, price);
            try {
                boolean isUpadted = collabaratingModel.updateCollobarating(dto);
                if (isUpadted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "collobaration is updated").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "colloboration  is not updated").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
    public void btnPrintOnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        try {
            InputStream resourceAsStream = getClass().getResourceAsStream("/reports/vendor_collabaration.jrxml");
            JasperDesign load = JRXmlLoader.load(resourceAsStream);
            JasperReport compileReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    compileReport,
                    null,
                    DbConnection.getInstance().getConnection()
            );
            JasperViewer.viewReport(jasperPrint, false);
        }catch (JRException|SQLException|ClassNotFoundException e){
            e.printStackTrace();
        }



    }

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

            String  id = txtsearch.getText();
            CollabaratingDto dto = collabaratingModel.search(id);
            if (dto!=null){
                fillData(dto);
            }else{
                Notifications notifications = Notifications.create().title("Search Service").text("Not Found").position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(3));
                notifications.show();
                sid.setText("");
                eid.setText("");
                vid.setText("");
                txtDescription.setText("");
                txtDte.setText("");
                txtTime.setText("");
                txtPrice.setText("");
            }

        }

        private void fillData(CollabaratingDto collabaratingDto){
            eid.setText(collabaratingDto.geteId());
            sid.setText(collabaratingDto.getsId());
            vid.setText(collabaratingDto.getvId());
            txtTime.setText(String.valueOf(collabaratingDto.getTime()));
            txtDte.setText(String.valueOf(collabaratingDto.getDate()));
            txtPrice.setText(String.valueOf(collabaratingDto.getPrice()));
            txtDescription.setText(collabaratingDto.getDesc());

    }
    private boolean validateFields() {
        String eidRegex = "^Z\\d{3,}$";
        String sidRegex ="^S\\d{3,}$";
        String vidRegex ="^V\\d{3,}$";
        String descRegex= "^[A-Za-z0-9\\s]{1,1000}$";

        String price ="^\\d+(\\.\\d{1,2})?$";
        String dater ="^\\d{4}-\\d{2}-\\d{2}$";
        String timer="^([01]\\d|2[0-3]):[0-5]\\d$";
        if (!validate(eid.getText(), eidRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid client id format. Please enter a valid number.").show();
            return false;
        }

        if (!validate(sid.getText(), sidRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid email format. Please enter a valid description.").show();
            return false;
        }

        if (!validate(vid.getText(), vidRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid name format. Please enter a valid description.").show();
            return false;
        }
        if (!validate(txtDescription.getText(), descRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid contact format. Please enter a valid description.").show();
            return false;
        }
        if (!validate(txtPrice.getText(), price)) {
            new Alert(Alert.AlertType.ERROR, "Invalid type format. Please enter a valid description.").show();
            return false;
        }
        if (!validate(txtDte.getText(), dater)) {
            new Alert(Alert.AlertType.ERROR, "Invalid type format. Please enter a valid description.").show();
            return false;
        }
        if (!validate(txtTime.getText(), timer)) {
            new Alert(Alert.AlertType.ERROR, "Invalid type format. Please enter a valid description.").show();
            return false;
        }

        return true;

    }
    private boolean validate(String input, String regex) {
        return input.matches(regex);
    }
}
