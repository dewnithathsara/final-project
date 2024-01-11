package lk.ijse.finalproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.finalproject.bo.BoFactory;
import lk.ijse.finalproject.bo.custom.CustomerBo;
import lk.ijse.finalproject.dao.custom.ClientDao;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.controlsfx.control.Notifications;
import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.ClientDto;
import lk.ijse.finalproject.dto.tm.ClientTm;
import lk.ijse.finalproject.dao.impl.ClientDaoImpl;
import lk.ijse.finalproject.util.Navigation;
import lk.ijse.finalproject.util.Route;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class ClientFormController {
    public JFXButton btnManageClient;
    public JFXButton btnAppointment;
    public JFXButton btnLogOut;
    public ClientDao dao = new ClientDaoImpl();
    public TableColumn colTel1;
    public JFXButton btnGenerate;
    public JFXButton btnIncome;
    public JFXButton btnInvoice;
    @FXML
    private AnchorPane ClientPane;
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnhome;
    @FXML
    private JFXButton btnmanageEmployee;
    @FXML
    private TableColumn<?, ?> colAddress;
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colTel;
    @FXML
    private TableView<ClientTm> tblCustomer;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtClientId;
    @FXML
    private TextField txtContactNo;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextField txtemail;
    CustomerBo bo=(CustomerBo) BoFactory.getBoFactory().getBOTypes(BoFactory.botypes.CUSTOMER);


    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadAllCustomers();

    }


    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.MANAGER_DASHBOARD,ClientPane);
    }

    public void btnManageEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.EMPLOYEE_MANAGE,ClientPane);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (validateFields()) {
            String clientId = txtClientId.getText();
            String name = txtName.getText();

            String email = txtemail.getText();

            String address = txtAddress.getText();
            String contact = txtContactNo.getText();

            var dto = new ClientDto(clientId, name, email, address, contact);
            try {
                boolean isSaved = bo.saveCustomer(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer is saved").showAndWait();
                    clearFields();
                    loadAllCustomers();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "CUSTOMER IS NOT SAVED").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
    private void clearFields() {
        txtClientId.setText("");
        txtName.setText("");
        txtemail.setText("");
        txtAddress.setText("");
        txtContactNo.setText("");

    }



    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (validateFields()) {
            try{
            String clientId = txtClientId.getText();
            String name = txtName.getText();
            String email = txtemail.getText();
            String address = txtAddress.getText();
            String contact = txtContactNo.getText();
            var dto = new ClientDto(clientId, name, email, address, contact);

                boolean isUpdate = bo.updateClient(dto);
                if (isUpdate) {
                    new Alert(Alert.AlertType.CONFIRMATION, "client is updated").show();
                    clearFields();
                    loadAllCustomers();
                } else {
                    new Alert(Alert.AlertType.ERROR, "client is not updated").show();
                    clearFields();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtClientId.getText();
        try {
            boolean isDelete = bo.deleteCustomer(id);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "CLIENT IS DELETED").show();
                clearFields();
                loadAllCustomers();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "CLIENT IS NOT DELETED").show();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
       Notifications notifications =Notifications.create()
               .title(" cleared")
               .text("cleard")
               .graphic(null)
               .hideAfter(Duration.seconds(5))
               .position(Pos.BOTTOM_RIGHT)
               .onAction(new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent actionEvent) {
                       System.out.println("click on the notification");
                   }
               });
       notifications.showConfirm();
    }

    public void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("client_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
    }


    public void loadAllCustomers() throws SQLException, ClassNotFoundException {

        ObservableList<ClientTm> obList = FXCollections.observableArrayList();
        List<ClientDto> dtoList = bo.getAllCustomers();
        try{

            for (ClientDto dto : dtoList) {
                obList.add(new ClientTm(dto.getcId(), dto.getCustName(), dto.getEmail(), dto.getAddress(), dto.getContact()));

            }

            tblCustomer.setItems(obList);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void btnManageClientOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.CLIENT_MANAGE, ClientPane);

    }

    public void btnAppointmentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.APPOINTMENT_MANAGE, ClientPane);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.LOGIN, ClientPane);
    }


    public void btnGenerateOnAction(ActionEvent actionEvent)  {
        try {
            InputStream resourceAsStream = getClass().getResourceAsStream("/reports/client_details.jrxml");
            JasperDesign load = JRXmlLoader.load(resourceAsStream);
            JasperReport compileReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    compileReport,
                    null,
                    DbConnection.getInstance().getConnection()
            );
            JasperViewer.viewReport(jasperPrint, false);


        }catch (JRException|SQLException|ClassNotFoundException e){
            Notifications notifications =Notifications.create()
                    .title(" Report isn't available")
                    .text("can't show the report")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            System.out.println("click on the notification");
                        }
                    });
            notifications.showConfirm();
            e.printStackTrace();
        }


    }
    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String  id = txtSearch.getText();
        ClientDto dto = bo.search(id);
        if (dto!=null){
            fillData(dto);
        }else{
            Notifications notifications = Notifications.create().title("Search Service").text("Not Found").position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(3));
            notifications.show();
            txtClientId.setText("");
            txtemail.setText("");
            txtAddress.setText("");
            txtName.setText("");
            txtContactNo.setText("");
        }

    }

    private void fillData(ClientDto clientDto){
        txtClientId.setText(clientDto.getcId());
        txtName.setText(clientDto.getCustName());
        txtemail.setText(clientDto.getEmail());
        txtAddress.setText(clientDto.getAddress());
        txtContactNo.setText(String.valueOf(clientDto.getContact()));
    }
    private boolean validateFields() {
        String clientId="^C\\d{3,}$";
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        String addressRegex = "^[A-Za-z0-9\\s.,'-]{1,1000}$";
        String  nameRegex= "^[A-Za-z\\s]{1,10}$";
        String contactRegex = "^[0-9]\\d{10}$\";";
        if (!validate(txtClientId.getText(), clientId)) {
            new Alert(Alert.AlertType.ERROR, "Invalid client id format. Please enter a valid number.").show();
            return false;
        }

        if (!validate(txtemail.getText(), emailRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid email format. Please enter a valid description.").show();
            return false;
        }
        if (!validate(txtAddress.getText(), addressRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid address format. Please enter a valid description.").show();
            return false;
        }
        if (!validate(txtName.getText(), nameRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid name format. Please enter a valid description.").show();
            return false;
        }
        /*if (!validate(txtContactNo.getText(), contactRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid contact format. Please enter a valid description.").show();
            return false;
        }*/
        return true;
    }
    private boolean validate(String input, String regex) {
        return input.matches(regex);
    }


}
