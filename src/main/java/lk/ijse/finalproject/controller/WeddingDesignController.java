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
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.finalproject.bo.BoFactory;
import lk.ijse.finalproject.bo.custom.AppointmentBo;
import lk.ijse.finalproject.bo.custom.CustomerBo;
import lk.ijse.finalproject.bo.custom.EventBo;
import lk.ijse.finalproject.dao.custom.AppointmentDao;
import lk.ijse.finalproject.dao.custom.EventDao;
import lk.ijse.finalproject.dto.AppointmentDto;
import lk.ijse.finalproject.dto.EventDesignDto;
import lk.ijse.finalproject.dao.impl.AppointmentDaoImpl;
import lk.ijse.finalproject.dao.impl.EventDaoImpl;
import lk.ijse.finalproject.util.Navigation;
import lk.ijse.finalproject.util.Route;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class WeddingDesignController {

    public AnchorPane weedingDesignPane;
    public Label lblEvent;
    public TextField txtType;
    public TextField txtLocation;
    public JFXComboBox cmbApId;
    public Label clientId;
    public TextField txtTime;
    public TextField txtDate;
    public TextField txtTheme;
    public JFXButton btnSave;

    public EventDao eventDaoImpl =new EventDaoImpl();
    public AppointmentDao dao=new AppointmentDaoImpl();
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnClear;
    public JFXButton btnback;
    public TextField txtStatus;
    public TextField txtSearch;
    public TextField eventId;
    AppointmentBo appointmentBo=(AppointmentBo) BoFactory.getBoFactory().getBOTypes(BoFactory.botypes.APPOINTMENT);
    CustomerBo bo=(CustomerBo) BoFactory.getBoFactory().getBOTypes(BoFactory.botypes.CUSTOMER);
    EventBo eventBo=(EventBo) BoFactory.getBoFactory().getBOTypes(BoFactory.botypes.EVENT);


    public void initialize()  {
        generateNextEventDesignCode();
        loadAllAppointmentId();
    }

    private void generateNextEventDesignCode()  {

        String eid= null;
        try {
            eid = eventBo.generateEventDesignId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        lblEvent.setText(eid);

    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(validateFields()) {

            try {
                String eid = eventBo.generateEventDesignId();
                lblEvent.setText(eid);
                String type = txtType.getText();
                String location = txtLocation.getText();
                String aId = String.valueOf(cmbApId.getValue());
                LocalTime time = LocalTime.parse(txtTime.getText());
                LocalDate date = LocalDate.parse((txtDate.getText()));
                String theme = txtTheme.getText();
                String status = txtStatus.getText();


                var dto = new EventDesignDto(eid, type, location, aId, time, date, theme, status);

                boolean isAdded = eventBo.saveEvent(dto);
                if (isAdded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "wedding event is added").showAndWait();

                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "event isn't added").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
    public void loadAllAppointmentId(){
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
    public void cmbClientOnAction(ActionEvent actionEvent) {
        String id = (String) cmbApId.getValue();

        try {
            AppointmentDto appointmentDto = appointmentBo.searchId(id);
            clientId.setText(appointmentDto.getcId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if(validateFields()){
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
                new Alert(Alert.AlertType.CONFIRMATION, "wedding event is updated").showAndWait();

            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "event isn't updated").show();
            }
        } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id=lblEvent.getText();
        try{
            boolean isDeleted= eventBo.deletEevent(id);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Wedding is deleted").show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION," Wedding is not deleted");
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
        txtStatus.setText("");

    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.EVENT_DESIGN,weedingDesignPane);
    }

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String serchId=txtSearch.getText();

        EventDesignDto dto= eventBo.showAllAppointments(serchId);
        try {
            if (dto != null) {
                fillData(dto);
                System.out.println("waikiki");
                txtSearch.setText("");
            } else {
                Notifications notification = Notifications.create().text("Search Event").text("").text("Not Found").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                notification.show();
                eventId.setText("");
                txtStatus.setText("");
                txtTheme.setText("");
                txtType.setText("");
                txtLocation.setText("");
                txtTime.setText("");
                txtDate.setText("");


                System.out.println("moon in the day");
            }
        } catch (Exception e) {
            System.out.println("Exception" + e);
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            System.out.println("byeon woo seok");
        }
    }
    private void fillData(EventDesignDto dto) {
        txtLocation.setText(dto.getaId());
        txtTime.setText(String.valueOf(dto.getTime()));

        txtStatus.setText(dto.getStatus());
        txtDate.setText(String.valueOf(dto.getDate()));
        txtType.setText(dto.getType());
        eventId.setText(dto.getEid());
        txtTheme.setText(dto.getTheme());

    }
    private boolean validateFields() {
       // String eventRegex = "^Z\\d{3,}$";
        String locationRegex = "^[A-Za-z0-9\\s]{1,100}$";
        String timeRegex = "^([01]\\d|2[0-3]):[0-5]\\d$";
        String statusRegex = "^[A-Za-z]{1,20}$";
        String dateRegex = "^\\d{4}-\\d{2}-\\d{2}$";

        String typeRegex = "^[A-Za-z]{1,20}$";
        String themeRegex = "^[A-Za-z\\s]{1,100}$";

        if (!validate(txtTheme.getText(), themeRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid theme format. Please enter a valid number.").show();
            return false;
        }

        if (!validate(txtLocation.getText(), locationRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid location format. Please enter a valid description.").show();
            return false;
        }

        if (!validate(txtStatus.getText(), statusRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid txtStatus format. Please enter a valid status.").show();
            return false;
        }
        if (!validate(txtType.getText(), typeRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid type format. Please enter a valid type.").show();
            return false;
        }
        if (!validate(txtTime.getText(), timeRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid time format. Please enter a valid time.").show();
            return false;
        }
        if (!validate(txtDate.getText(), dateRegex)) {
            new Alert(Alert.AlertType.ERROR, "Invalid date format. Please enter a valid date.").show();
            return false;
        }
return  true;
    }
    private boolean validate(String input, String regex) {
        return input.matches(regex);
    }


}
