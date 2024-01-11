package lk.ijse.finalproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.finalproject.bo.BoFactory;
import lk.ijse.finalproject.bo.custom.EmployeeBo;
import lk.ijse.finalproject.bo.custom.EventBo;
import lk.ijse.finalproject.dao.custom.EmployeeDao;
import lk.ijse.finalproject.dto.EmployeeDto;
import lk.ijse.finalproject.dto.tm.EmployeeTm;
import lk.ijse.finalproject.dao.impl.EmployeeDaoImpl;
import lk.ijse.finalproject.util.Navigation;
import lk.ijse.finalproject.util.Route;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class EmployeeFormController {

    public AnchorPane employeePane;

    public JFXButton btnmanageEmployee;
    public JFXButton btnBack;
    public TextField txtSearch;
    public Label lblDate;
    public TextField txtEmpId;
    public TextField txtName;
    public TextField txtemail;
    public TextField txtContacNo;
    public TextField txtType;
    public JFXButton btnSave;
    public JFXButton btnhome;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnClear;
    public JFXButton btnLogOut;

    @FXML
    private TableColumn<?,?> colContact;

    @FXML
    private TableColumn <?,?> colEmail;

    @FXML
    private TableColumn <?,?> colId;

    @FXML
    private TableColumn <?,?> colName;

    @FXML
    private TableColumn <?,?> colType;
    @FXML
    private TableView<EmployeeTm> tblEmployee;

    EmployeeBo employeeBo=(EmployeeBo) BoFactory.getBoFactory().getBOTypes(BoFactory.botypes.EMPLOYEE);


    private EmployeeDao dao =new EmployeeDaoImpl();
    public void initialize() {
        setCellValueFactory();
        loadAllEmployee();
        showCurrentDate();
    }

    private void showCurrentDate() {
        try{
            LocalDate date=LocalDate.now();
            lblDate.setText(String.valueOf(date));
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }

    }
    public void datePickerOnEntered(DragEvent dragEvent) {
        
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.MANAGER_DASHBOARD,employeePane);

    }

    public void btnManageEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.EMPLOYEE_MANAGE,employeePane);

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if(validateFields()) {
            String EmpId = txtEmpId.getText();

            String name = txtName.getText();

            String email = txtemail.getText();

            String contact = txtContacNo.getText();

            String type = txtType.getText();


            var dto = new EmployeeDto(EmpId, name, email, contact, type);
            try {
                boolean isSaved = employeeBo.saveEmployee(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "employee is saved").showAndWait();
                    clearFields();
                    loadAllEmployee();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }
    }

    private void clearFields() {
        txtEmpId.setText("");
        txtName.setText("");
        txtemail.setText("");
        txtContacNo.setText("");
        txtType.setText("");
    }

   // public void btnbackOnAction(ActionEvent actionEvent) throws IOException {
    //    Navigation.navigate(Route.MANAGER_DASHBOARD, employeePane);
  //  }

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtSearch.getText();
        EmployeeDto dto = employeeBo.search(id);
        if (dto != null) {
            fillData(dto);
        } else {
            Notifications notifications = Notifications.create().title("Search Service").text("Not Found").position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(3));
            notifications.show();
            txtEmpId.setText("");
            txtemail.setText("");
            txtType.setText("");
            txtName.setText("");
            txtContacNo.setText("");
        }

    }
    private void fillData(EmployeeDto dto){
        txtEmpId.setText(dto.getEmpId());
        txtName.setText(dto.getName());
        txtemail.setText(dto.getEmail());
        txtType.setText(dto.getType());
        txtContacNo.setText(dto.getContact());
    }



    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if(validateFields()) {
            String id = txtEmpId.getText();
            String name = txtName.getText();
            String email = txtemail.getText();
            String contact = txtContacNo.getText();
            String type = txtType.getText();
            var dto = new EmployeeDto(id, name, email, contact, type);
            try {
                boolean isUpdate = employeeBo.updateEmployee(dto);
                if (isUpdate) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee is updated").showAndWait();
                    clearFields();
                    loadAllEmployee();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

            }
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id=txtEmpId.getText();
        try{
            boolean isDelete= employeeBo.deleteEmployee(id);
            if(isDelete){
                loadAllEmployee();
                new Alert(Alert.AlertType.CONFIRMATION,"Employee is deleted").show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,"Employee is not deleted").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();

    }
    public void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));


    }
    public void loadAllEmployee(){


        ObservableList<EmployeeTm> obList= FXCollections.observableArrayList();
        try{
            List<EmployeeDto> dtoList=employeeBo.getAllEmployee();
            for(EmployeeDto dto:dtoList){
                obList.add(
                        new EmployeeTm(
                            dto.getEmpId(),
                            dto.getName(),
                            dto.getEmail(),
                                dto.getContact(),
                                dto.getType()
                )
                );
            }

            tblEmployee.setItems(obList);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.LOGIN,employeePane);

    }
    private boolean validateFields() {
        String epId = "^E\\d{3,}$";
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";

        String nameRegex = "^[A-Za-z\\s]{1,10}$";
        String contactRegex = "^\\d{10}$";
        String typeRegex="^[A-Za-z\\s]{1,20}$";
        if (!validate(txtEmpId.getText(), epId)) {
            new Alert(Alert.AlertType.ERROR, "Invalid client id format. Please enter a valid number.").show();
            return false;
        }

        if (!validate(txtemail.getText(), emailRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid email format. Please enter a valid description.").show();
            return false;
        }

        if (!validate(txtName.getText(), nameRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid name format. Please enter a valid description.").show();
            return false;
        }
        if (!validate(txtContacNo.getText(), contactRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid contact format. Please enter a valid description.").show();
            return false;
        }
        if (!validate(txtType.getText(), typeRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid type format. Please enter a valid type.").show();
            return false;
        }
        return true;

    }
    private boolean validate(String input, String regex) {
        return input.matches(regex);
    }
}
