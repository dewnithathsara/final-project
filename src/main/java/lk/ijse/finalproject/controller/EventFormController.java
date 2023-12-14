package lk.ijse.finalproject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.finalproject.dto.AppointmentDto;
import lk.ijse.finalproject.dto.ClientDto;
import lk.ijse.finalproject.dto.EventConDto;
import lk.ijse.finalproject.model.AppointmentModel;
import lk.ijse.finalproject.model.ClientModel;
import lk.ijse.finalproject.model.EventModel;
import lk.ijse.finalproject.util.Navigation;
import lk.ijse.finalproject.util.Route;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class EventFormController {
    public AnchorPane consultingPane;
    public JFXButton btnHome;
    public JFXButton btnConsulting;
    public JFXButton btnLogOut;
    public JFXComboBox cmbAid;
    public Label lbelClientId;
    public Label lblClientName;
    public TextField txtLocation;
    public Label lblConId;
    public TextField txtFee;
    public TextField txtDescription;
    public TextField txtType;
    public JFXButton btnSave;

    public EventModel eventModel = new EventModel();
    public AppointmentModel appointmentModel = new AppointmentModel();
    public ClientModel clientModel = new ClientModel();
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnClear;
    public JFXButton btnEventDesign;
    public TextField txtsearch;
    public TextField txtSFee;
    public TextField txtsDesc;
    public TextField txtScid;
    public TextField txtSConId;


    public void initialize() {
        generateConId();
        loadAllComboAppoimentId();


    }


    private void generateConId() {
        try {
            String conId = eventModel.generateNextCode();
            lblConId.setText(conId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.DESIGNER_DASHBOARD, consultingPane);
    }

    public void btnConsultingOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.CLIENT_CONSULTING, consultingPane);
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.LOGIN, consultingPane);
    }

    public void cmbSearchIdOnAction(ActionEvent actionEvent) {
        String aId = (String) cmbAid.getValue();
        try {
            AppointmentDto appointmentDto = appointmentModel.searchId(aId);
            lbelClientId.setText(appointmentDto.getcId());
            ClientDto clientDto = appointmentModel.getCustomerInfo(appointmentDto.getcId());

            lblClientName.setText(clientDto.getCustName());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void loadAllComboAppoimentId() {
        ObservableList<String> oblist = FXCollections.observableArrayList();
        try {
            List<AppointmentDto> appointmentDtos = appointmentModel.getAllAppointment();
            for (AppointmentDto appointmentDto : appointmentDtos) {
                oblist.add(appointmentDto.getaId());
            }
            cmbAid.setItems(oblist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void lblClientNameOnDragDetected(MouseEvent mouseEvent) {

    }

    public void btnSveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (validateFields()) {
            // Proceed with the rest of the process
            String conId = eventModel.generateNextCode();
            lblConId.setText(conId);
            double fee = Double.parseDouble(txtFee.getText());
            String description = txtDescription.getText();
            String cId = lbelClientId.getText();
            var dto = new EventConDto(conId, fee, description, cId);
            try {
                boolean isSaved = eventModel.saveConsulting(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Consulting success").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    private boolean validateFields() {
        String clientId="^C\\d{3,}$";
        String conRegex="^D\\d{3,}$";
        String feeRegex =  "^[0-9]+(\\.[0-9]{1,2})?$";
        String descriptionRegex = "^[A-Za-z0-9\\s]{1,1000}$";
        if (!validate(txtFee.getText(), feeRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid fee format. Please enter a valid number.").show();
            return false;
        }

        if (!validate(txtDescription.getText(), descriptionRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid description format. Please enter a valid description.").show();
            return false;
        }
        if (!validate(lblConId.getText(), conRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid conId format. Please enter a valid conid.").show();
            return false;
        }
        if (!validate(lbelClientId.getText(), clientId)) {
            new Alert(Alert.AlertType.ERROR, "Invalid clientId format. Please enter a valid client.").show();
            return false;
        }
        return true;
    }
    private boolean validate(String input, String regex) {
        return input.matches(regex);
    }



    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if(validateFields()) {
            System.out.println("btn Update");


            String conId = txtSConId.getText();
            double fee = Double.parseDouble((txtSFee.getText()));
            String desc = txtsDesc.getText();
            String cid = txtScid.getText();
            System.out.println("whaaaaaaa");
            getAllDetails(conId);

            try {
                var dto = new EventConDto(conId, fee, desc, cid);
                eventModel.search(dto.getConId());
                fillData(dto);
                boolean isupdated = eventModel.updateConsulting(dto);
                if (isupdated) {
                    System.out.println("update is updated");
                    new Alert(Alert.AlertType.CONFIRMATION, "Consulting is updated").show();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Consulting isn't updated").show();
                }

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = lblConId.getText();
        try {
            boolean isDeleted = eventModel.deleteEventCon(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "consulting is deleted").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "consulting is not deleted").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();

    }

    private void clearFields() {
        txtSConId.setText("");
        txtsearch.setText("");
        txtDescription.setText("");
        txtSFee.setText("");
        txtsDesc.setText("");
        txtScid.setText("");
        txtFee.setText("");

    }

    public void btnEventDesignOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.EVENT_DESIGN, consultingPane);
    }

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtsearch.getText();
        EventConDto dto = eventModel.search(id);
        System.out.println("twinkiling watermelon");
        try {
            if (dto != null) {
                fillData(dto);
                System.out.println("waikiki");
                txtsearch.setText("");
            } else {
                Notifications notification = Notifications.create().text("Search Event").text("").text("Not Found").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                notification.show();
                txtSConId.setText("");
                txtScid.setText("");
                txtSFee.setText("");
                txtsDesc.setText("");
                System.out.println("moon in the day");
            }
        } catch (Exception e) {
            System.out.println("Exception" + e);
            throw new RuntimeException();
        }
        System.out.println("byeon woo seok");
    }


    private void fillData(EventConDto dto) throws SQLException, ClassNotFoundException {

        System.out.println("RYU shi oh");
        txtSConId.setText(dto.getConId());
        txtScid.setText(dto.getcId());
        System.out.println("KIM DO HA");
        txtSFee.setText(String.valueOf(dto.getFee()));
        txtsDesc.setText(dto.getDescription());
        System.out.println("perfect marraige revenge");
    }


    private void getAllDetails(String id) throws SQLException, ClassNotFoundException {
        System.out.println("whaaaaaaa");
        EventConDto eventConDto = eventModel.getAllDetails(id);
        txtSConId.setText(eventConDto.getConId());
        txtScid.setText(eventConDto.getcId());
        System.out.println("KIM DO HA");
        txtSFee.setText(String.valueOf(eventConDto.getFee()));
        txtsDesc.setText(eventConDto.getDescription());


    }
}
