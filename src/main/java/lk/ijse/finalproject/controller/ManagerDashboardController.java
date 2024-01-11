package lk.ijse.finalproject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalproject.bo.BoFactory;
import lk.ijse.finalproject.bo.custom.AppointmentBo;
import lk.ijse.finalproject.bo.custom.CustomerBo;
import lk.ijse.finalproject.bo.custom.EmployeeBo;
import lk.ijse.finalproject.bo.custom.EventRoleBo;
import lk.ijse.finalproject.dao.custom.AppointmentDao;
import lk.ijse.finalproject.dao.custom.EmployeeDao;
import lk.ijse.finalproject.dto.AppointmentDto;
import lk.ijse.finalproject.dto.EmployeeDto;
import lk.ijse.finalproject.dto.EventRoleDto;
import lk.ijse.finalproject.dto.tm.EventRoleTm;
import lk.ijse.finalproject.dao.impl.AppointmentDaoImpl;
import lk.ijse.finalproject.dao.impl.EmployeeDaoImpl;
import lk.ijse.finalproject.dao.impl.EventRoleDaoImpl;
import lk.ijse.finalproject.util.Navigation;
import lk.ijse.finalproject.util.Route;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ManagerDashboardController {
    public AnchorPane root;
    public AnchorPane managerPane;

    public TextField txtSearch;
    public Label lblDate;

    public AnchorPane sigithi;
    public JFXButton btnhome;
    public JFXButton btnmanageEmployee;

    public JFXButton btnManageClient;
    public JFXButton btnLogout;
    public JFXButton btnAppointment;
    public Label lblCompletedEvent;
    public Label lblCompleted;

   //public AppointmentDao dao=new AppointmentDaoImpl();
    public Label lbltime;
    public Label lblfee;
    public Label lblstates1;
    public Label lblstates2;
    public Label empCount;

    //public EmployeeDao employeeDaoImpl =new EmployeeDaoImpl();
    public JFXListView listview;
    public JFXComboBox cmbempId;
    public JFXComboBox cmbaId;
    public TextField txtTask;
    public TextField txtStatus;
    public JFXButton btnAssign;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnClear;
    public TableView tblEventRole;
    public JFXComboBox cmbSAid;
    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTask;
    @FXML
    private TableView<EventRoleTm> tbleventRole;
    //public EventRoleDaoImpl eventRoleDaoImpl =new EventRoleDaoImpl();
    public JFXButton btnhome1;
    public JFXButton btnmanageEmployee1;
    public JFXButton btnLogOut;
    public JFXButton btnincome;
    public JFXButton btnInvoice;
    public JFXButton btnclient;
    public JFXComboBox cmbSelectAid;
    AppointmentBo bo=(AppointmentBo) BoFactory.getBoFactory().getBOTypes(BoFactory.botypes.APPOINTMENT);
    EmployeeBo employeeBo=(EmployeeBo) BoFactory.getBoFactory().getBOTypes(BoFactory.botypes.EMPLOYEE);
    EventRoleBo eventRoleBo=(EventRoleBo) BoFactory.getBoFactory().getBOTypes(BoFactory.botypes.CUSTOMER);



    public void initialize(){
        countAppointment();
        showDate();
        showTime();
        countEmployee();
        sumFee();
        countFullypaid();
        countHalfPaid();
        loadAllEmployeeId();
        loadAllAppointmentId();
        setCellValueFactory();
        getAllEventRole();
    }

    private void getAllEventRole() {
        //var model = new EventRoleDaoImpl();
        String aid= String.valueOf(cmbSAid.getValue());
        ObservableList<EventRoleTm> obList = FXCollections.observableArrayList();
        try {
            List<EventRoleDto> dtoList = eventRoleBo.getAllEventRole(aid);
            for (EventRoleDto dto : dtoList) {
                obList.add(new EventRoleTm(dto.getEmpId(),dto.getTask(), dto.getStatus()));
            }
            tbleventRole.setItems(obList);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private void setCellValueFactory() {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colTask.setCellValueFactory(new PropertyValueFactory<>("task"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

    }


    private void loadAllEmployeeId() {
        ObservableList<String> oblist = FXCollections.observableArrayList();
        try {
            List<EmployeeDto> dtos = employeeBo.getAllEmployee();
            for (EmployeeDto employeeDto   : dtos) {
                oblist.add(employeeDto.getEmpId());
            }
            cmbempId.setItems(oblist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void loadAllAppointmentId() {
        ObservableList<String> oblist = FXCollections.observableArrayList();
        try {
            List<AppointmentDto> dtos = bo.getAllAppointment();
            for (AppointmentDto appointmentDto   : dtos) {
                oblist.add(appointmentDto.getaId());
            }
          cmbaId.setItems(oblist);
            cmbSAid.setItems(oblist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private void countHalfPaid() {
        try{
            int count=bo.countnotfullypaid();
            lblstates2.setText(String.valueOf(count));
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void countFullypaid() {
        try{
            int count=bo.countfullypaid();
            lblstates1.setText(String.valueOf(count));
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void sumFee() {
        try{
            double total=bo.sumfee();
            lblfee.setText(String.valueOf(total));
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void countEmployee() {
        try {
            int count= employeeBo.countEmployee();
            empCount.setText(String.valueOf(count));
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    private void showTime() {
        try{
            LocalTime time=LocalTime.now();
            lbltime.setText(String.valueOf(time));
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    public void countAppointment() {

        try{
       int count=   bo.countAppointment();
         lblCompletedEvent .setText(String.valueOf(count));
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.toString()).show();
        }
    }
    private void showDate(){
        try{
            LocalDate date=LocalDate.now();
            lblDate.setText(String.valueOf(date));
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    public void datePickerOnEntered(DragEvent dragEvent) {
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.MANAGER_DASHBOARD, managerPane);
    }

    public void btnManageEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.EMPLOYEE_MANAGE, managerPane);
    }

    public void btnManageClientOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.CLIENT_MANAGE,managerPane);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.LOGIN,managerPane);
    }

    public void btAppointmentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.APPOINTMENT_MANAGE,managerPane);
    }

    public void AssignBtnOnAction(ActionEvent actionEvent) {
        String empId = String.valueOf(cmbempId.getValue());
        String aid = String.valueOf(cmbaId.getValue());
        String task=txtTask.getText();
        String status=txtStatus.getText();
        var dto = new EventRoleDto(empId, aid, task, status);
        try{boolean isAssign = eventRoleBo.assignRole(dto);
        if (isAssign) {
            new Alert(Alert.AlertType.CONFIRMATION, "Appointment is made").show();
            clearFields();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "Appointment is not made").show();
        }



    } catch (RuntimeException e) {
        new Alert(Alert.AlertType.CONFIRMATION, e.getMessage()).show();
    } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {

    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
        String empId= String.valueOf(cmbempId.getValue());
        String aId= String.valueOf(cmbaId.getValue());
        String task=txtTask.getText();
        String status= txtStatus.getText();

        var dto=new EventRoleDto(empId,aId,task,status);
        try{
            boolean isUpdate= eventRoleBo.updateEventRole(dto);
            if(isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"EventRole is updated").showAndWait();
                clearFields();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }
    }



    public void deleteBtnOnAction(ActionEvent actionEvent) {
        String id=colEmpId.getText();
        try{
            boolean isDeleted= eventRoleBo.DeleteEventRole(id);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"EventRole is deleted").show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,"EventRole is not deleted");
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    public void clearBtnOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnincome(ActionEvent actionEvent) {
    }

    public void btnInvoice(ActionEvent actionEvent) {
    }

    public void showAllEventDetailsOnAction(ActionEvent actionEvent) {
        getAllEventRole();
    }
}
