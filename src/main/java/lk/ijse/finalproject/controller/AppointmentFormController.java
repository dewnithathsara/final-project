package lk.ijse.finalproject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.finalproject.bo.BoFactory;
import lk.ijse.finalproject.bo.custom.AppointmentBo;
import lk.ijse.finalproject.bo.custom.CustomerBo;
import lk.ijse.finalproject.dao.custom.AppointmentDao;
import lk.ijse.finalproject.dao.custom.ClientDao;
import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.AppointmentDto;
import lk.ijse.finalproject.dto.ClientDto;
import lk.ijse.finalproject.dto.tm.AppointmentTm;
import lk.ijse.finalproject.dao.impl.AppointmentDaoImpl;
import lk.ijse.finalproject.dao.impl.ClientDaoImpl;
import lk.ijse.finalproject.util.Navigation;
import lk.ijse.finalproject.util.Route;
import net.sf.jasperreports.engine.*;
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

public class AppointmentFormController {
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnClear;
    public JFXButton btnDelete;
    public JFXButton btnhome;
    public AnchorPane AppointmentPane;
    public JFXButton btnmanageEmployee;
    public JFXButton btnManageClient;
    public JFXButton btnAppointment;
    public JFXButton btnLogOut;
    public TextField txtSearch;
    public JFXButton btnMakeAppointment;
    public TextField txtStatus;
    public TextField txtDate;
    public TextField txtfee;
    public TextField txtTime;
    public DatePicker datepicker;
    public ComboBox comBoxClientId;
    public JFXTimePicker timePicker;
    public Label lblclientName;
    public Label lblName;
    public Label lblAppId;
    public Label lblApId;
    public JFXButton btnInvoice;
    public JFXButton btnClear1;
    public TextField txtedate;
    public TextField txtpaid;
    public TextField txtffee;
    public TextField txtftime;
    public TableView tblAppoinment;
    public Label lblApId1;
    public Label lblName1;
    public TextField textaid;
    public TextField textFee;
    public TextField textCLentId;
    public TextField textAppTime;
    public TextField textAppDate;
    public TextField textStatus;
    public Label lblDate;
    @FXML
    private TableColumn colAid;

    @FXML
    private TableColumn colCid;

    @FXML
    private TableColumn colDate;

    @FXML
    private TableColumn colFee;

    @FXML
    private TableColumn colStatus;

    @FXML
    private TableColumn colTiem;

    @FXML
    private TableView<AppointmentTm> tblAppointment;


    public ClientDao clientModel = new ClientDaoImpl();
    //public AppointmentDao dao = new AppointmentDaoImpl();
    AppointmentBo appointmentBo=(AppointmentBo)BoFactory.getBoFactory().getBOTypes(BoFactory.botypes.APPOINTMENT);
    CustomerBo bo=(CustomerBo) BoFactory.getBoFactory().getBOTypes(BoFactory.botypes.CUSTOMER);


    public void initialize() throws SQLException, ClassNotFoundException {

        generateAppointmentId();
        getAllClientId();
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


    private void generateAppointmentId() {
        try {
            String appId = appointmentBo.generateNextAppId();
            System.out.println("marry me");
            lblApId.setText(appId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String appId =textaid .getText();
        String status = txtStatus.getText();
        double fee = Double.parseDouble(txtfee.getText());
        LocalTime time = LocalTime.parse(txtTime.getText());
        LocalDate date = LocalDate.parse(txtDate.getText());
        String cId= String.valueOf(textCLentId.getText());


        var dto = new AppointmentDto(appId, fee, status, date, time, cId);

        try {
            boolean isupdate = appointmentBo.updateAppointment(dto);
            if (isupdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "appointment is updated").show();
                clearFields();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "appointment is not updated").show();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = textaid.getText();
        try {
            boolean isDeleted = appointmentBo.deleteAppointment(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Appointment is deleted").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Appointment is not deleted");
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.MANAGER_DASHBOARD, AppointmentPane);
    }

    public void btnManageClientOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.CLIENT_MANAGE, AppointmentPane);
    }

    public void btnAppointmentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.APPOINTMENT_MANAGE, AppointmentPane);
    }

    public void btnLogOutAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.LOGIN, AppointmentPane);
    }

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String serchId=txtSearch.getText();

        AppointmentDto dto=appointmentBo.showAllAppointments(serchId);
        try {
            if (dto != null) {
                fillData(dto);
                System.out.println("waikiki");
                txtSearch.setText("");
            } else {
                Notifications notification = Notifications.create().text("Search Event").text("").text("Not Found").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                notification.show();
                textaid.setText("");
                txtDate.setText("");
                txtTime.setText("");
                textCLentId.setText("");
                txtStatus.setText("");
                txtfee.setText("");

                System.out.println("moon in the day");
            }
        } catch (Exception e) {
            System.out.println("Exception" + e);
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        System.out.println("byeon woo seok");
    }
}
    private void fillData(AppointmentDto dto) {

        textaid.setText(dto.getaId());
        txtfee.setText(String.valueOf(dto.getFee()));
        txtStatus.setText(dto.getStatus());
        textCLentId.setText(dto.getcId());
        txtTime.setText(String.valueOf(dto.getTime()));
        txtDate.setText(String.valueOf(dto.getDate()));
        System.out.println("yoo yoo");


    }



    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnManageEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.EMPLOYEE_MANAGE, AppointmentPane);
    }

    public void btnMakeAppointOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String aId = appointmentBo.generateNextAppId();
        System.out.println(aId + "444444555555");
        textaid.setText(aId);
        System.out.println(aId + "9999999");

        String status = txtStatus.getText();
        String feeText = txtfee.getText();
        String feeRegex = "^[0-9]+(\\.[0-9]{1,2})?$";
        if (!feeText.matches(feeRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid fee format. Please enter a valid decimal number.").show();
            return;
        }
        double fee = Double.parseDouble(feeText);
        String cId = String.valueOf(comBoxClientId.getValue());
        textCLentId.setText(cId);
        System.out.println(aId + "4444444444444");
        System.out.println(cId);
        String timeText = txtTime.getText();
        String timeRegex = "^\\d{2}:\\d{2}$";
        if (!timeText.matches(timeRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid time format. Please enter a valid time (HH:mm).").show();
            return;
        }
        LocalTime time = LocalTime.parse(timeText);
        String dateText = txtDate.getText();
        String dateRegex = "^\\d{4}-\\d{2}-\\d{2}$";
        // System.out.println(date);
        if (!dateText.matches(dateRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid date format. Please enter a valid date (yyyy-MM-dd).").show();
            return;
        }
        LocalDate date = LocalDate.parse(dateText);

        var dto = new AppointmentDto(aId, fee, status, date, time, cId);
        System.out.println(dto.getaId() + "********");
        System.out.println(dto.getStatus() + "********");
        try {
            boolean isMakeAppointment = appointmentBo.makeAppointment(dto);
            if (isMakeAppointment) {
                new Alert(Alert.AlertType.CONFIRMATION, "Appointment is made").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Appointment is not made").show();
            }
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.CONFIRMATION, e.getMessage()).show();
        }


    }

    private void clearFields() {
        txtDate.setText("");
        txtfee.setText("");
        txtTime.setText("");
        txtStatus.setText("");
    }

    public void cmbClientOnAction(ActionEvent actionEvent) {
        String id = (String) comBoxClientId.getValue();

        try {
            ClientDto clientDto = bo.searchId(id);
            lblclientName.setText(clientDto.getCustName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void getAllClientId() throws SQLException, ClassNotFoundException {
        ObservableList <String> oblist= FXCollections.observableArrayList();
        try{
            List<ClientDto> clientDtos=bo.getAllCustomers();
            for(ClientDto dto:clientDtos){
                oblist.add(dto.getcId());
            }
            comBoxClientId.setItems(oblist);
        }catch(Exception e){
            throw new RuntimeException(e);
        }



    }


    public void btnLogOutOnAction(ActionEvent actionEvent) {
    }

    public void btnIncomeOnAction(ActionEvent actionEvent) {
    }

    public void btnInvoiceOnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        try {
            InputStream resourceAsStream = getClass().getResourceAsStream("/reports/invoice.jrxml");
            JasperDesign load = JRXmlLoader.load(resourceAsStream);
            JasperReport compileReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    compileReport,
                    null,
                    DbConnection.getInstance().getConnection()
            );
            JasperViewer.viewReport(jasperPrint, false);


        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}


