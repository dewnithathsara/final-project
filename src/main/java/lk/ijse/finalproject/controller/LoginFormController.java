package lk.ijse.finalproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.finalproject.bo.BoFactory;
import lk.ijse.finalproject.bo.custom.EventBo;
import lk.ijse.finalproject.bo.custom.UserBo;
import lk.ijse.finalproject.dto.LoginDto;
import lk.ijse.finalproject.dao.LoginModel;
import lk.ijse.finalproject.util.Navigation;
import lk.ijse.finalproject.util.Route;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    public AnchorPane loginPane;
    public JFXButton btnLogin;
    public JFXButton btnSignUp;
    public Text txtForgetPassword;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    @FXML


   // private LoginModel loginModel= new LoginModel();
    UserBo userBo=(UserBo) BoFactory.getBoFactory().getBOTypes(BoFactory.botypes.USER);



    public void btnSignUpOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.REGISTER,loginPane);
      //  Button button = new Button("Click Me");



    }
    public void btnLoginOnAction(ActionEvent actionEvent) {
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        if (username.trim().isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Username and password can't be blank").show();
        } else {
            var dto = new LoginDto(username, password);

            try {
                boolean isLogin = userBo.userLogin(dto);
                System.out.println(isLogin);
                System.out.println(username);

                if (isLogin) {
                   // checkCredentials(username);
                    new Alert(Alert.AlertType.CONFIRMATION, "User Logged in").showAndWait();
                    checkCredentials(username);

                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid credentials. Please try again.").show();
                }
            } catch (SQLException e) {
                System.out.println(" Meke SQl ex eka Mokdda: "+e.getMessage());
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println(" Meke Class Not Found ex eka Mokdda: "+e.getMessage());
                new Alert(Alert.AlertType.ERROR, "Class Not Found: " + e.getMessage()).show();
            }
        }
    }


    private void checkCredentials(String username) {
        switch (username) {
            case "Manager":
                System.out.println("login pane");
                try {
                    Navigation.navigate(Route.MANAGER_DASHBOARD, loginPane);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "Designer":
                try {
                    Navigation.navigate(Route.DESIGNER_DASHBOARD, loginPane);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "TeamLeader":
                try {
                    Navigation.navigate(Route.TEAMLEADER_DASHBOARD, loginPane);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.out.println("Invalid username: " + username);
        }
    }

    private void clearFields() {
        txtUserName.setText("");
        txtPassword.setText("");
    }

    public void forgetOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Forget Password");

        // Load the ForgetPassword.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgetPassword.fxml"));
        Parent root = loader.load();

        // Set the loaded FXML file as the content of the pop-up
        Scene scene = new Scene(root);
        popupStage.setScene(scene);

        // Access the controller of the ForgetPassword.fxml if needed
        ForgotPasswordController controller = loader.getController();

        // Show the pop-up
        popupStage.showAndWait();
    }
}



