package lk.ijse.finalproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import lk.ijse.finalproject.dto.ClientDto;
import lk.ijse.finalproject.dto.ServiceDto;
import lk.ijse.finalproject.dto.tm.ServiceTm;
import lk.ijse.finalproject.model.ClientModel;
import lk.ijse.finalproject.model.ServiceModel;
import lk.ijse.finalproject.util.Navigation;
import lk.ijse.finalproject.util.Route;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class ServiceFormController {
    public AnchorPane servicePane;
    public JFXButton btnHome;
    public JFXButton btnVendorManage;
    public JFXButton btnServiceManage;
    public Label serviceId;
    public TextField txtPackageName;
    public TextField txtDescription;
    public TextField txtPrice;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnClear;
    public Label lbldate;
    public Label lblTime;
    public TextField txtServiceId;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> coldescriprion;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private TableColumn<?, ?> colprice;
    @FXML
    private TableView<ServiceTm> tblservice;
    @FXML
    private JFXButton btngenerate;
    @FXML
    private TextField txtSearch;

    public ServiceModel serviceModel=new ServiceModel();

    public JFXButton btnLogOut;
    public JFXButton btnCollabarating;


    public void initialize() throws SQLException, ClassNotFoundException {
        generateServiceId();
        setCellValueFactory();
       getAllService();
       getCurrentDate();
       getCurrentTime();
    }


    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String  id = txtSearch.getText();
        ServiceDto dto = serviceModel.search(id);
        if (dto!=null){
            fillData(dto);
        }else{
            Notifications notifications = Notifications.create().title("Search Service").text("Not Found").position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(3));
            notifications.show();
            serviceId.setText("");
            txtDescription.setText("");
            txtPackageName.setText("");
            txtPrice.setText("");
        }

    }

    private void fillData(ServiceDto service){
        txtServiceId.setText(String.valueOf(txtServiceId));
        txtServiceId.setText(service.getSid());
        txtDescription.setText(service.getDescription());
        txtPackageName.setText(service.getPackageName());
        txtPrice.setText(String.valueOf(service.getPrice()));
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

    private void generateServiceId() {
        try{
            String sId=serviceModel.generateSid();
            serviceId.setText(sId);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnGenerateOnAction(ActionEvent event) {

    }


    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       if(validateFields()) {
           String sId = serviceModel.generateSid();
           String sid=txtServiceId.getText();

           String packageName = txtPackageName.getText();
           String description = txtDescription.getText();
           double price = Double.parseDouble(txtPrice.getText());
           var dto = new ServiceDto(sid, packageName, description, price);
           try {
               boolean isSaved = serviceModel.saveService(dto);
               if (isSaved) {
                   new Alert(Alert.AlertType.CONFIRMATION, "Service is saved").show();
                   clearFields();
                   getAllService();
               } else {
                   new Alert(Alert.AlertType.CONFIRMATION, "Service is not saved").show();
               }
           } catch (Exception e) {
               new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
           }
       }
    }

    private void clearFields() {
        txtServiceId.getText();
        txtDescription.setText("");
       txtPackageName.setText("");
       txtPrice.setText("");
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        if(validateFields()) {
            String serviceId = txtServiceId.getText();
            String name = txtPackageName.getText();
            String desc = txtDescription.getText();
            double price = Double.parseDouble(txtPrice.getText());

            var dto = new ServiceDto(serviceId, name, desc, price);
            try {
                boolean isUpdate = serviceModel.updateService(dto);
                if (isUpdate) {
                    new Alert(Alert.AlertType.CONFIRMATION, "service is updated").show();
                    clearFields();
                    getAllService();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "service is not updated").show();
                    clearFields();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }
    }
    public void getAllService() throws SQLException, ClassNotFoundException {
        var model = new ServiceModel();
        ObservableList<ServiceTm> obList = FXCollections.observableArrayList();
        List<ServiceDto> dtoList = model.getservices();
        try{

            for (ServiceDto dto : dtoList) {
                obList.add(new ServiceTm(dto.getSid(), dto.getPackageName(), dto.getDescription(),dto.getPrice()));

            }

            tblservice.setItems(obList);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colname.setCellValueFactory(new PropertyValueFactory<>("package_name"));
        coldescriprion.setCellValueFactory(new PropertyValueFactory<>("description"));
        colprice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id=serviceId.getText();
        try{
            boolean isDeleted=serviceModel.deleteService(id);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"service is deleted").show();
                getAllService();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,"service is not deleted");
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnHomeOnaction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.TEAMLEADER_DASHBOARD,servicePane);
    }

    public void btnVendorOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.VENDORS,servicePane);
    }

    public void btnServiceOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.SERVICE,servicePane);
    }

    public void btnVendorCollabarating(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.VENDOR_COLLABARATING,servicePane);
    }

    public void btnLOgOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.LOGIN,servicePane);
    }
    private boolean validateFields() {
        String serviceRegex="^S\\d{3,}$";
        String nameRegex = "^[A-Za-z\\s]{1,100}";
        String descRegex = "^[A-Za-z\\s]{1,1000}$";
        String  priceREgex="^\\d+(\\.\\d{1,2})?$"; ;


        if (!validate(txtServiceId.getText(), serviceRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid serviceId format. Please enter a valid number.").show();
            return false;
        }

        if (!validate(txtPackageName.getText(), nameRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid packagename format. Please enter a valid description.").show();
            return false;
        }
        if (!validate(txtDescription.getText(), descRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid description format. Please enter a valid description.").show();
            return false;
        }
        if (!validate(txtPrice.getText(), priceREgex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid price format. Please enter a valid description.").show();
            return false;
        }

        return true;
    }
    private boolean validate(String input, String regex) {
        return input.matches(regex);
    }

}
