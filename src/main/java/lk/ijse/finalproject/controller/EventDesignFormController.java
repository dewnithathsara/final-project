package lk.ijse.finalproject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalproject.dto.EventDesignDto;
import lk.ijse.finalproject.model.EventDesignModel;
import lk.ijse.finalproject.util.Navigation;
import lk.ijse.finalproject.util.Route;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class EventDesignFormController {
    public AnchorPane eventdesignPane;
    public JFXButton btnHome;
    public JFXButton btnConsulting;
    public JFXButton btnLogOut;
    public AnchorPane eventBtnpane;
    public JFXButton btnWedding;
    public JFXButton btnAnniversary;
    public JFXButton btnbirthday;
    public JFXButton btnconcert;
    public JFXButton btnfestival;
    public JFXButton btnConference;
    public JFXButton btnnEducational;
    public JFXButton btnSports;
    public AnchorPane eventSelectPane;

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.DESIGNER_DASHBOARD,eventdesignPane);

    }

    public void btnConsultingOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.CLIENT_CONSULTING,eventdesignPane);
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.LOGIN,eventdesignPane);

    }

    public void btnWeddingOnAction(ActionEvent actionEvent) throws IOException {
      //eventdesignPane.getChildren().clear();
        Navigation.navigate(Route.WEDDING_DESIGN,eventdesignPane);
    }

    public void btnAniversayOnAction(ActionEvent actionEvent) throws IOException {
        //eventdesignPane.getChildren().removeAll(eventBtnpane);
        Navigation.navigate(Route.ANNIVERSARY_DESIGN,eventdesignPane);
    }

    public void btnBirthdayOnAction(ActionEvent actionEvent) throws IOException {
       // eventdesignPane.getChildren().removeAll(eventBtnpane);
        Navigation.navigate(Route.BIRTHDAY,eventdesignPane);

    }

    public void btnConcertOnAction(ActionEvent actionEvent) throws IOException {
        //eventdesignPane.getChildren().removeAll(eventBtnpane);
        Navigation.navigate(Route.CONCERTS,eventdesignPane);

    }

    public void btnFestivalOnAction(ActionEvent actionEvent) throws IOException {
        //eventdesignPane.getChildren().removeAll(eventBtnpane);
        Navigation.navigate(Route.FESTIVAL,eventdesignPane);

    }

    public void btnConferenceOnAction(ActionEvent actionEvent) throws IOException {
        //eventdesignPane.getChildren().removeAll(eventBtnpane);
        Navigation.navigate(Route.CONFERENCE,eventdesignPane);

    }

    public void btnEducationalOnAction(ActionEvent actionEvent) throws IOException {
       // eventdesignPane.getChildren().removeAll(eventBtnpane);
        Navigation.navigate(Route.EDUCATIONAL,eventdesignPane);

    }

    public void btnSportsOnAction(ActionEvent actionEvent) throws IOException {
        //eventdesignPane.getChildren().clear();
        Navigation.navigate(Route.SPORTS,eventdesignPane);

    }
}