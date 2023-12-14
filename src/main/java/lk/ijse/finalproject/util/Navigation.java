package lk.ijse.finalproject.util;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Navigation {
    public static AnchorPane pane;

    public static void navigate(Route route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route) {
            case REGISTER:
                window.setTitle("Register Form");
                initUI("RegisterForm.fxml");
                break;
            case LOGIN:
                window.setTitle("Login Form");
                initUI("LoginForm.fxml");
                break;
            case MANAGER_DASHBOARD:
                window.setTitle("manager dashboard");
                initUI("SidePanel.fxml");
                break;
            case DESIGNER_DASHBOARD:
                window.setTitle("designer dashboard");
                initUI("DesignerDashBoard.fxml");
                break;
            case TEAMLEADER_DASHBOARD:
                window.setTitle("team leader dashboard");
                initUI("TeamLeaderDashBoard.fxml");
                break;
            case EMPLOYEE_MANAGE:
                window.setTitle("Employee manage");
                initUI("EmployeeForm.fxml");
                break;
            case CLIENT_MANAGE:
                window.setTitle("Client manage");
                initUI("ClientForm.fxml");
                break;
            case APPOINTMENT_MANAGE:
                window.setTitle("Appointment manage");
                initUI("AppointmentForm.fxml");
                break;
            case CLIENT_CONSULTING:
                window.setTitle("Client Consulting");
                System.out.println("Switch Case Event Form FXML");
                initUI("EventForm.fxml");
                break;
            case SERVICE:
                window.setTitle("Service manage");
                initUI("ServiceForm.fxml");
                break;
            case VENDOR_COLLABARATING:
                window.setTitle("VENDOR Collabarate");
                initUI("CollabaratingForm.fxml");
                break;
            case EVENT_DESIGN:
                window.setTitle("Event Design");
                initUI("EventDesignForm.fxml");
                break;
            case WEDDING_DESIGN:
                window.setTitle("Wedding Design");
                window.centerOnScreen();
                initUI("WeddingDesign.fxml");
                break;
            case ANNIVERSARY_DESIGN:
                window.setTitle("Anniversary design");
                initUI("AnniveersaryDesign.fxml");
                break;
            case VENDORS:
                window.setTitle("vendor page");
                initUI("VendorCollaboratingForm.fxml");
                break;
            case BIRTHDAY:
                window.setTitle("birthday");
                initUI("BirthdayForm.fxml");
                break;
            case CONCERTS:
                window.setTitle("concerts");
                initUI("ConcertForm.fxml");
                break;
            case CONFERENCE:
                window.setTitle("CONFERENCE");
                initUI("ConferenceForm.fxml");
                break;
            case EDUCATIONAL:
                window.setTitle("educational");
                initUI("EducationalForm.fxml");
                break;
            case FESTIVAL:
                window.setTitle("festival");
                initUI("FestivalForm.fxml");
                break;
            case SPORTS:
                window.setTitle("sports");
                initUI("SportsForm.fxml");
                break;
            case FORGETPASSWORD:
                window.setTitle("forgot password");
                initUI("ForgotPassword.fxml");
                break;
            default:

                System.out.println("Mukuth Natho");
        }
    }

    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/finalproject/view/" + location)));
    }


}
