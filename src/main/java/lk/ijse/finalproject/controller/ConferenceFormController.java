package lk.ijse.finalproject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalproject.bo.BoFactory;
import lk.ijse.finalproject.bo.custom.AppointmentBo;
import lk.ijse.finalproject.bo.custom.EventBo;
import lk.ijse.finalproject.dao.custom.AppointmentDao;
import lk.ijse.finalproject.dao.custom.EventDao;
import lk.ijse.finalproject.dto.AppointmentDto;
import lk.ijse.finalproject.dto.EventDesignDto;
import lk.ijse.finalproject.dao.impl.AppointmentDaoImpl;
import lk.ijse.finalproject.dao.impl.EventDaoImpl;
import lk.ijse.finalproject.util.Navigation;
import lk.ijse.finalproject.util.Route;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ConferenceFormController {
    public AnchorPane conferencePane;
    public Label lblEvent;
    public TextField txtType;
    public TextField txtLocation;
    public JFXComboBox cmbApId;
    public Label clientId;
    public TextField txtTime;
    public TextField txtDate;
    public TextField txtTheme;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnClear;
    AppointmentBo appointmentBo=(AppointmentBo) BoFactory.getBoFactory().getBOTypes(BoFactory.botypes.APPOINTMENT);

    public EventDao eventDaoImpl =new EventDaoImpl();
    public AppointmentDao appointmentModel=new AppointmentDaoImpl();
    public JFXButton btnback;
    public TextField txtStatus;
    EventBo eventBo=(EventBo) BoFactory.getBoFactory().getBOTypes(BoFactory.botypes.EVENT);

    public void initialize() throws SQLException, ClassNotFoundException {
        generateNextEventDesignCode();
        loadAllAppointmentId();
    }
    private void loadAllAppointmentId() {
        ObservableList<String> oblist = FXCollections.observableArrayList();
        try {
            List<AppointmentDto> dtos = appointmentBo.getAllAppointment();
            for (AppointmentDto appointmentDto : dtos) {
                oblist.add(appointmentDto.getaId());
            }
            cmbApId.setItems(oblist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextEventDesignCode() throws SQLException, ClassNotFoundException {
        try{
            String eid= eventBo.generateEventDesignId();
            lblEvent.setText(eid);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        try {
            String eid = eventBo.generateEventDesignId();
            lblEvent.setText(eid);
            String type = txtType.getText();
            String location = txtLocation.getText();
            String aId = String.valueOf(cmbApId.getValue());
            LocalTime time = LocalTime.parse(txtTime.getText());
            LocalDate date = LocalDate.parse((txtDate.getText()));
            String theme = txtTheme.getText();


            String status=txtStatus.getText();


            var dto = new EventDesignDto(eid, type, location, aId, time, date, theme,status);
            boolean isAdded = eventBo.saveEvent(dto);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "conference event is added").showAndWait();

            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "conference isn't added").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = lblEvent.getText();
        String type = txtType.getText();
        String location = txtLocation.getText();
        String aId = String.valueOf(cmbApId.getValue());
        LocalTime time = LocalTime.parse(txtTime.getText());
        LocalDate date = LocalDate.parse((txtDate.getText()));
        String theme = txtTheme.getText();
        String status=txtStatus.getText();


        var dto = new EventDesignDto(id, type, location, aId, time, date, theme,status);        try {
            boolean isUpdated = eventBo.updateEvent(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "conference event is updated").showAndWait();

            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "conference isn't updated").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id=lblEvent.getText();
        try{
            boolean isDeleted= eventBo.deletEevent(id);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"conference is deleted").show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION," conference is not deleted");
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }
    private void clearFields() {
        txtDate.setText("");
        txtTime.setText("");
        txtLocation.setText("");
        txtType.setText("");
        txtTheme.setText("");
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.EVENT_DESIGN,conferencePane);
    }
    private boolean validateFields() {
        String eventRegex="^Z\\d{3,}$";
        String locationRegex="^[A-Za-z0-9\\s]{1,100}$";
        String timeRegex ="^([01]\\d|2[0-3]):[0-5]\\d$";
        String statusRegex ="^[A-Za-z]{1,20}$";
        String dateRegex="^\\d{4}-\\d{2}-\\d{2}$";

        String typeRegex= "^[A-Za-z]{1,20}$";
        String themeRegex="^[A-Za-z\\s]{1,100}$";

        if (!validate(txtTheme.getText(), themeRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid theme format. Please enter a valid number.").show();
            return false;
        }

        if (!validate(txtLocation.getText(), locationRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid description format. Please enter a valid description.").show();
            return false;
        }
        if (!validate(txtDate.getText(), dateRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid conId format. Please enter a valid description.").show();
            return false;
        }
        if (!validate(txtStatus.getText(), statusRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid clientId format. Please enter a valid description.").show();
            return false;
        }
        if (!validate(txtType.getText(), typeRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid clientId format. Please enter a valid description.").show();
            return false;
        }
        if (!validate(txtTime.getText(), timeRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid clientId format. Please enter a valid description.").show();
            return false;
        }
        if (!validate(txtDate.getText(), dateRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid clientId format. Please enter a valid description.").show();
            return false;
        }
        /*if (!validate(eventId.getText(), eventRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid clientId format. Please enter a valid description.").show();
            return false;
        }*/
        return true;
    }
    private boolean validate(String input, String regex) {
        return input.matches(regex);
    }


}


