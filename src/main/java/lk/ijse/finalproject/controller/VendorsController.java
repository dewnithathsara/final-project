package lk.ijse.finalproject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.finalproject.dto.*;
import lk.ijse.finalproject.model.RegisterModel;
import lk.ijse.finalproject.model.VendorModel;
import lk.ijse.finalproject.util.Navigation;
import lk.ijse.finalproject.util.Route;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class VendorsController {
    public AnchorPane vendorPane;
    public JFXButton btnHome;
    public JFXButton btnManageVendors;

    public JFXButton btnLogOut;
    public Label lblvendorId;
    public TextField txtCategory;
    public TextField txtCompanyName;
    public TextField txtEmail;
    public TextField txtContact;
    public JFXComboBox cmbUserId;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXButton btnClear;
    public JFXButton btnUpdate;
    public JFXButton btnVendorCollabarating;
    public Label lbldate;
    public Label nopoto;
    public Label noflower;
    public Label noOfcategory;
    public Label lblTime;
    public TextField txtVendorId;
    public TextField txtUserId;
    public TextField txtSearch;
    @FXML
    private JFXListView<String> listview;

    public VendorModel vendorModel=new VendorModel();

    public RegisterModel registerModel=new RegisterModel();


    public void initialize() throws SQLException, ClassNotFoundException {
        loadAlluserId();
        generateVendorId();

        setdate();
        setCurrntTime();
        setPhotographersCount();
        setFloweristCount();
        setCategoryCount();

    }

    private void setCategoryCount() {
        try{
            int count=   vendorModel.countCategories();
            noOfcategory .setText(String.valueOf(count));

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.toString()).show();
        }
    }

    private void setFloweristCount() {
        String category="flowerists";
        try{
            int count=   vendorModel.countflowerists(category);
            noflower .setText(String.valueOf(count));

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.toString()).show();
        }

    }

    private void setPhotographersCount() {
        String category="photography";
        try{
            int count=   vendorModel.countphotographers(category);
            nopoto .setText(String.valueOf(count));

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.toString()).show();
        }

    }

    private void setCurrntTime() {
        try{
            LocalTime time=LocalTime.now();
            lblTime.setText(String.valueOf(time));
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }

    }

    private void setdate(){
        try{
            LocalDate date=LocalDate.now();
            lbldate.setText(String.valueOf(date));
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    private void listAllCategories() {
        listview.getItems().add(txtCategory.getText());
    }

    public void btnvendorcolobarationOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.VENDOR_COLLABARATING,vendorPane);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.LOGIN,vendorPane);
    }

    public void btnsaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=vendorModel.generateNextVendorId();
        String vid=txtVendorId.getText();
        String uid= String.valueOf(cmbUserId.getValue());
        String category=txtCategory.getText();
        String categoryRegex="^[A-Za-z\\s]{1,20}$";
        if (!category.matches(categoryRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid category format. Please enter a valid category.").show();
            return;
        }

        String name=txtCompanyName.getText();
        String nameRegex="^[A-Za-z\\s]{1,20}$";
        if (!name.matches(nameRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid name format. Please enter a valid name.").show();
            return;
        }
        String email=txtEmail.getText();
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid email format. Please enter a valid email address.").show();
            return;
        }
        String contact=txtContact.getText();
        String contactRegex = "^\\d{10}$";
        if (!contact.matches(contactRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid contact number format. Please enter a valid 10-digit integer.").show();
            return;
        }

        var dto=new VendorDto(vid,uid,category,name,email,contact);
        try{
            boolean isSaved=vendorModel.saveVendor(dto);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"vendors is saved").show();
                listAllCategories();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,"vendor isn't saved").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }
        listAllCategories();
       listview.getItems().add(txtCategory.getText());
    }



    private void generateVendorId(){
        try{
            String vendorId = vendorModel.generateNextVendorId();
            lblvendorId.setText(vendorId);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id=lblvendorId.getText();
        try{
            boolean isDeleted=vendorModel.deleteVendor(id);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"vendor is deleted").show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,"vendor is not deleted");
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        txtContact.setText("");
        txtEmail.setText("");
        txtCategory.setText("");
        txtCompanyName.setText("");
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        String vendorId= txtVendorId.getText();
        String category=txtCategory.getText();
        String  name = txtCompanyName.getText();
        String  email = txtEmail.getText();
       String  ContactInfo=txtContact.getText();
       String uId=txtUserId.getText();
        String categoryRegex="^[A-Za-z\\s]{1,20}$";
        if (!category.matches(categoryRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid category format. Please enter a valid category.").show();
            return;
        }
        String nameRegex="^[A-Za-z\\s]{1,20}$";
        if (!name.matches(nameRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid name format. Please enter a valid name.").show();
            return;
        }
        String contactRegex = "^\\d{10}$";
        if (!ContactInfo.matches(contactRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid contact number format. Please enter a valid 10-digit integer.").show();
            return;
        }
        var dto=new VendorDto(vendorId,uId,category,name,email,ContactInfo);
        try {
            boolean isUpdate = vendorModel.updateVendor(dto);
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "vendor is updated").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "vendor is not updated").show();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }





    private void loadAlluserId() throws SQLException, ClassNotFoundException {
        ObservableList<String> oblist = FXCollections.observableArrayList();
        try {
            List<RegisterDto>  userDto =registerModel.getAllusers();
            for (RegisterDto registerDto : userDto) {
                oblist.add(registerDto.getUid());
            }
            cmbUserId.setItems(oblist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbSearchId(ActionEvent actionEvent) {
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.TEAMLEADER_DASHBOARD,vendorPane);
    }

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String  id = txtSearch.getText();
        VendorDto dto = VendorModel.search(id);
        if (dto!=null){
            fillData(dto);
        }else{
            Notifications notifications = Notifications.create().title("Search Service").text("Not Found").position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(3));
            notifications.show();
            txtVendorId.setText("");
            txtUserId.setText("");
            txtContact.setText("");
            txtEmail.setText("");
            txtCategory.setText("");
            txtCompanyName.setText("");

        }

    }

    private void fillData(VendorDto vendorDto){
        txtCompanyName.setText(vendorDto.getName());
        txtCategory.setText(vendorDto.getCategory());
        txtContact.setText(vendorDto.getContact());
        txtUserId.setText(vendorDto.getUid());
        txtEmail.setText(vendorDto.getEmail());
        txtVendorId.setText(vendorDto.getId());


    }

}
