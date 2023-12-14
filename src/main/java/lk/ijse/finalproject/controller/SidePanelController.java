package lk.ijse.finalproject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.AppointmentDto;
import lk.ijse.finalproject.dto.EmployeeDto;
import lk.ijse.finalproject.dto.EventConDto;
import lk.ijse.finalproject.dto.EventRoleDto;
import lk.ijse.finalproject.dto.tm.EventRoleTm;
import lk.ijse.finalproject.model.AppointmentModel;
import lk.ijse.finalproject.model.EmployeeModel;
import lk.ijse.finalproject.model.EventRoleModel;
import lk.ijse.finalproject.util.Navigation;
import lk.ijse.finalproject.util.Route;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class SidePanelController {
    public AnchorPane sidePane;
    public JFXButton btnhome;
    public JFXButton btnmanageEmployee;
    public JFXButton btnIncome;
    public JFXButton btnInvoice;
    public JFXButton btnClient;
    public JFXButton btnAppointment;
    public JFXButton btnLogOut;
    public JFXButton btnincome;
    public JFXButton btnmanageEmployee1;
    public AnchorPane fullPane;
    public JFXButton btnclient;
    public AnchorPane flipPane;
    public AnchorPane managerPane;
    public TableView tbleventRole;
    public TableColumn colEmpId;
    public TableColumn colAid;
    public TableColumn colTask;
    public TableColumn colStatus;
    public JFXComboBox cmbempId;
    public JFXComboBox cmbaId;
    public TextField txtTask;
    public TextField txtStatus;
    public JFXButton btnAssign;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnClear;
    public Label lblDate;
    public Label empCount;
    public TextField txtSearch;
    public Label lblCompletedEvent;
    public Label lblCompleted;
    public Label lbltime;
    public Label lblfee;
    public Label lblstates1;
    public Label lblstates2;
    public EventRoleModel eventRoleModel = new EventRoleModel();
    public AppointmentModel appointmentModel = new AppointmentModel();
    public EmployeeModel employeeModel = new EmployeeModel();
    public JFXComboBox cmbSAid;


    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        managerPane.getChildren().clear();
        Navigation.navigate(Route.MANAGER_DASHBOARD, fullPane);
    }

    public void btnManageEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.EMPLOYEE_MANAGE, flipPane);
    }

    public void btnIncomeOnAction(ActionEvent actionEvent) {

    }

    public void btnInvoiceOnAction(ActionEvent actionEvent) {
    }

    public void btnManageClientOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.CLIENT_MANAGE, flipPane);
    }

    public void btnAppointmentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.APPOINTMENT_MANAGE, flipPane);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.LOGIN, fullPane);
    }

    public void datePickerOnEntered(DragEvent dragEvent) {
    }

    public void initialize() {
        countAppointment();
        showDate();
        showTime();
        countEmployee();
        sumFee();
        countFullypaid();
        countHalfPaid();
        loadAllEmployeeId();
        loadAllAppointmentId();

    }

    private void loadAllEmployeeId() {
        ObservableList<String> oblist = FXCollections.observableArrayList();
        try {
            List<EmployeeDto> dtos = employeeModel.getAllEmployee();
            for (EmployeeDto employeeDto : dtos) {
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
            List<AppointmentDto> dtos = appointmentModel.getAllAppointment();
            for (AppointmentDto appointmentDto : dtos) {
                oblist.add(appointmentDto.getaId());
            }
            cmbaId.setItems(oblist);
            cmbSAid.setItems(oblist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void getAllEventRole() throws SQLException, ClassNotFoundException {
        var model = new EventRoleModel();
        String aid = String.valueOf(cmbSAid.getValue());

        ObservableList<EventRoleTm> obList = FXCollections.observableArrayList();
        try {
            List<EventRoleDto> dtoList = model.getAllEventRole(aid);
            for (EventRoleDto dto : dtoList) {
                obList.add(new EventRoleTm(dto.getEmpId(), dto.getTask(), dto.getStatus()));
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

    private void countHalfPaid() {
        try {
            int count = appointmentModel.countnotfullypaid();
            lblstates2.setText(String.valueOf(count));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void countFullypaid() {
        try {
            int count = appointmentModel.countfullypaid();
            lblstates1.setText(String.valueOf(count));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void sumFee() {
        try {
            double total = appointmentModel.sumfee();
            lblfee.setText(String.valueOf(total));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void countEmployee() {
        try {
            int count = employeeModel.countEmployee();
            empCount.setText(String.valueOf(count));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void showTime() {
        try {
            LocalTime time = LocalTime.now();
            lbltime.setText(String.valueOf(time));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    private void countAppointment() {

        try {
            int count = appointmentModel.countAppointment();
            lblCompletedEvent.setText(String.valueOf(count));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
        }
    }

    private void showDate() {
        try {
            LocalDate date = LocalDate.now();
            lblDate.setText(String.valueOf(date));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }


    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String serchId=txtSearch.getText();

        EventRoleDto dto=eventRoleModel.showallrole(serchId);
        try {
            if (dto != null) {
                fillData(dto);
                System.out.println("waikiki");
                txtSearch.setText("");
            } else {
                Notifications notification = Notifications.create().text("Search Event").text("").text("Not Found").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                notification.show();

                txtStatus.setText("");
                txtStatus.setText("");

                System.out.println("moon in the day");
            }
        } catch (Exception e) {
            System.out.println("Exception" + e);
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            System.out.println("byeon woo seok");
        }
    }
    private void fillData(EventRoleDto dto) {


        txtStatus.setText(dto.getStatus());
        txtTask.setText(dto.getStatus());
        System.out.println("yoo yoo");




}

    public void AssignBtnOnAction(ActionEvent actionEvent) {
        String empId = String.valueOf(cmbempId.getValue());
        String aid = String.valueOf(cmbaId.getValue());
        String task = txtTask.getText();
        String status = txtStatus.getText();
        var dto = new EventRoleDto(empId, aid, task, status);
        try {
            boolean isAssign = eventRoleModel.assignRole(dto);
            if (isAssign) {
                new Alert(Alert.AlertType.CONFIRMATION, "Appointment is made").show();
                clearFields();
                getAllEventRole();
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
        txtStatus.setText("");
        txtTask.setText("");
        txtSearch.setText("");

    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
        String empId = String.valueOf(cmbempId.getValue());
        String aId = String.valueOf(cmbaId.getValue());
        String task = txtTask.getText();
        String status = txtStatus.getText();

        var dto = new EventRoleDto(empId, aId, task, status);
        try {
            boolean isUpdate = eventRoleModel.updateEventRole(dto);
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "EventRole is updated").showAndWait();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
    }


    public void deleteBtnOnAction(ActionEvent actionEvent) {
        String id = colEmpId.getText();
        try {
            boolean isDeleted = eventRoleModel.DeleteEventRole(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "EventRole is deleted").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "EventRole is not deleted");
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void clearBtnOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void showDetailONAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        getAllEventRole();

    }
}