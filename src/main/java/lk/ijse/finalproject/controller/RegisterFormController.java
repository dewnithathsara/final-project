package lk.ijse.finalproject.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.finalproject.bo.BoFactory;
import lk.ijse.finalproject.bo.custom.UserBo;
import lk.ijse.finalproject.dto.RegisterDto;
import lk.ijse.finalproject.dao.RegisterModel;
import lk.ijse.finalproject.util.Navigation;
import lk.ijse.finalproject.util.Route;


import java.io.IOException;
import java.sql.SQLException;

public class RegisterFormController {

    @FXML
    public TextField txtName;
    public JFXButton btnSignup;
    public AnchorPane registerPane;
    public AnchorPane pane;
    public Label userId;
    public Text txtLogin;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;

    
    //private RegisterModel registerModel=new RegisterModel();
    UserBo userBo=(UserBo) BoFactory.getBoFactory().getBOTypes(BoFactory.botypes.USER);

    public Stage stage;

    public void initialize(){
        generateUserId();
    }


    //go to login page action??
    @FXML
    public void btnSignupOnActrion(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        String userId1 = userBo.generateUserId();
        userId.setText(userId1);
        String name = txtName.getText();
        String nameRegex = "^[A-Za-z\\s]{1,20}$";
        String username = txtUserName.getText();
        String usernameRegex = "^[a-zA-Z0-9_]{3,20}$";
        String email = txtEmail.getText();
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String password = txtPassword.getText();
        String passwordRegex = "^.{8,}$";
        /*String password1= txtPassword1.getText();*/
        if (name.matches(nameRegex) && username.matches(usernameRegex) && email.matches(emailRegex) && password.matches(passwordRegex)) {
            if (username.trim().isEmpty() || password.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Sign up is unsuccessful. Please fill in all required fields.").show();
            } else {
                var dto = new RegisterDto(userId1, name, username, email, password);

                try {
                    boolean isRegister = userBo.registerUser(dto);
                    if (isRegister) {
                        new Alert(Alert.AlertType.CONFIRMATION, "User registered successfully.").showAndWait();
                        clearFields();
                        Navigation.navigate(Route.LOGIN, registerPane);
                    }
                } catch (SQLException | IOException e) {
                    new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
                }
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "invalid input");
        }
    }
    private void generateUserId() {
        try {
            String uId = userBo.generateUserId();
            userId.setText(uId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {

        txtName.setText("");
        txtUserName.setText("");
        txtEmail.setText("");
        txtPassword.setText("");

    }

    public void txtMouseClicked(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Route.LOGIN,registerPane);
    }
}
