package lk.ijse.finalproject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalproject.bo.BoFactory;
import lk.ijse.finalproject.bo.custom.CollabaratingBo;
import lk.ijse.finalproject.bo.custom.CustomerBo;
import lk.ijse.finalproject.bo.custom.EventBo;
import lk.ijse.finalproject.bo.custom.VendorsBo;
import lk.ijse.finalproject.dao.custom.CollabaratingDao;
import lk.ijse.finalproject.dao.custom.EventDao;
import lk.ijse.finalproject.dao.custom.VendorsDao;
import lk.ijse.finalproject.dto.CollabaratingDto;
import lk.ijse.finalproject.dto.EventDesignDto;
import lk.ijse.finalproject.dto.tm.CollabaratingTm;
import lk.ijse.finalproject.dao.impl.CollabaratingDaoImpl;
import lk.ijse.finalproject.dao.impl.EventDaoImpl;
import lk.ijse.finalproject.dao.impl.VendorsDaoImpl;
import lk.ijse.finalproject.util.Navigation;
import lk.ijse.finalproject.util.Route;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TeamLeaderDashBoardController {
    public AnchorPane teamLeaderDashboard;
    public JFXButton btnHome;
    public JFXButton btnVendors;
    public JFXButton btnService;

    public JFXButton btnLogOut;
    public JFXButton btnCollabarating;
    public Label lbldate;
    public Label lblTime;
    public Label vendorCount;
    public VendorsDao vendorsDaoImpl =new VendorsDaoImpl();
    public AnchorPane teamLeaderPane;
    public AnchorPane inpane;
    public AnchorPane outPane;
    public PieChart vendorsPieChart;
    public TextField txtsearch;
    @FXML
    private JFXListView<String> listview;
    @FXML
    private TableView<CollabaratingTm> tblCollabaraing;

    public TableColumn colSid;
    public TableColumn colVid;
    public TableColumn colFeedback;
    public TableColumn colTime;
    public TableColumn colDate;
    public TableColumn colPrice;

    public JFXComboBox cmbEvent;

    public EventDao eventDao=new EventDaoImpl();
    public CollabaratingDao dao=new CollabaratingDaoImpl();

    EventBo bo=(EventBo) BoFactory.getBoFactory().getBOTypes(BoFactory.botypes.EVENT);
    CollabaratingBo collabaratingBo=(CollabaratingBo) BoFactory.getBoFactory().getBOTypes(BoFactory.botypes.COLLABORATION);
    VendorsBo vendorsBo=(VendorsBo) BoFactory.getBoFactory().getBOTypes(BoFactory.botypes.VENDOR);



    public void initialize() throws SQLException, ClassNotFoundException {
        getCurrentDate();
        getCurrentTime();
        getVendorCount();
        loadAllEventId();
        setCellValueFactory();
        showPieChart();
        //loadAllCollabarting();
       // setCellValueFactory();


    }

    private void showPieChart() throws SQLException, ClassNotFoundException {
       /* try {
            ObservableList<PieChart.Data> pieChartData = vendorModel.getCategory();
            vendorsPieChart.setData(pieChartData);
        } catch (Exception e) {
            e.printStackTrace();
        }*/



    }

    public void setCellValueFactory() {
        colSid.setCellValueFactory(new PropertyValueFactory<>("Sid"));
        colVid.setCellValueFactory(new PropertyValueFactory<>("Vid"));
        colFeedback.setCellValueFactory(new PropertyValueFactory<>("feedback"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
    public void loadAllCollabarting() {

      String eid= String.valueOf(cmbEvent.getValue());
        ObservableList<CollabaratingTm> obList = FXCollections.observableArrayList();
        try {
            List<CollabaratingDto> dtoList = collabaratingBo.getAllCollabarting(eid);
            for (CollabaratingDto dto : dtoList) {
                obList.add(new CollabaratingTm(dto.getsId(), dto.getvId(), dto.getDesc(), dto.getTime(), dto.getDate(),dto.getPrice()));
            }
            tblCollabaraing.setItems(obList);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    private void loadAllEventId() {
        ObservableList<String> oblist = FXCollections.observableArrayList();
        try {
            List<EventDesignDto> dto =bo.getAllevents();
            for (EventDesignDto dtos : dto) {
                oblist.add(dtos.getEid());
            }
            cmbEvent.setItems(oblist);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void getVendorCount() {
        try{
            int count=   vendorsBo.countVendors();
            vendorCount .setText(String.valueOf(count));

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.toString()).show();
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
            lbldate.setText(String.valueOf(date));
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.TEAMLEADER_DASHBOARD,teamLeaderPane);
    }

    public void btnManageVendors(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.VENDORS,outPane);
    }

    public void btnServiceOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.SERVICE,outPane);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.LOGIN,teamLeaderPane);
    }

    public void btnCollabaratingOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.VENDOR_COLLABARATING,outPane);
    }

    public void cmbEventOnAction(ActionEvent actionEvent) {
        loadAllCollabarting();

    }

    public void searchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String category=txtsearch.getText();
        List<String> vendorNames = vendorsBo.getAllVendorsByCatgory(category);
        listview.getItems().clear();
        listview.getItems().addAll(vendorNames);
    }
}
