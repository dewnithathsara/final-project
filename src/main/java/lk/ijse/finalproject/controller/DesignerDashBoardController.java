package lk.ijse.finalproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalproject.dto.EventDesignDto;

import lk.ijse.finalproject.dto.tm.EventTm;
import lk.ijse.finalproject.model.EventDesignModel;
import lk.ijse.finalproject.model.EventModel;
import lk.ijse.finalproject.util.Navigation;
import lk.ijse.finalproject.util.Route;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DesignerDashBoardController {

    public AnchorPane designerDashBoard;
    public JFXButton btnHome;
    public JFXButton btnConsulting;
    public JFXButton btnLogOut;
    public JFXButton btnEventDesign;
    public AnchorPane isCompleted;
    public Label lblcompeleted;
    public EventDesignModel eventDesignModel=new EventDesignModel();
    public Label lblDate;
    public Label lblTime;
    public Label lblfee;
    public PieChart piechart;
    public Label lblfistType;
    public AnchorPane dpane;
    public AnchorPane inPane;
    public AnchorPane designerPane;
    public DatePicker datePicker;



    @FXML
    private TableColumn<?, ?> colEid;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> coltheme;
    @FXML
    private TableView<EventTm> tblEventTabl;




    public EventModel eventModel=new EventModel();

    public void initialize(){

        getPopularEvent();
        getCompledtedEventCount();
        getCurrentDate();
        getCurrentTime();
        calculatefee();
        setCellValueFactory();
       // getAllCurrentEvent();

        

    }
    private void setCellValueFactory() {
        colEid.setCellValueFactory(new PropertyValueFactory<>("eid"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));

        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("event_status"));




    }
    private void getAllCurrentEvent() {
        var model = new EventDesignModel();
        LocalDate date=datePicker.getValue();
        ObservableList<EventTm> obList = FXCollections.observableArrayList();
        try {
            List<EventDesignDto> dtoList = model.getAllCurrentEvent(date);
            for (EventDesignDto dto : dtoList) {
                obList.add(new EventTm(dto.getEid(),dto.getType(), dto.getLocation(),dto.getTime(),dto.getTheme(),dto.getStatus()));
            }
            tblEventTabl.setItems(obList);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private void getPopularEvent() {
        try{
            String type=eventDesignModel.popularEvent();
            lblfistType.setText(type);
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    private void calculatefee() {
        try {
            double fee = eventModel.total();
            lblfee.setText(String.valueOf(fee));
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
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
            lblDate.setText(String.valueOf(date));
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    private void getCompledtedEventCount() {
        String event_status="completed";
        try{
            int count=   eventDesignModel.countCompletedEvent(event_status);
            lblcompeleted .setText(String.valueOf(count));
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.toString()).show();
        }

    }


    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.DESIGNER_DASHBOARD,designerPane);
    }

    public void btnConsultingOnAction(ActionEvent actionEvent) throws IOException {
       // inPane.getChildren().clear();
        System.out.println("Navigation");
        Navigation.navigate(Route.CLIENT_CONSULTING,dpane);

    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        //designerDashBoard.getChildren().clear();
        Navigation.navigate(Route.LOGIN,designerPane);
    }

    public void btnEventOnAction(ActionEvent actionEvent) throws IOException {
       // inPane.getChildren().clear();
        Navigation.navigate(Route.EVENT_DESIGN,dpane);
    }

    public void filterEventsOnAction(ActionEvent actionEvent) {

    }

    public void filterbyDateOnActoin(ActionEvent actionEvent) {
        getAllCurrentEvent();
    }
}
