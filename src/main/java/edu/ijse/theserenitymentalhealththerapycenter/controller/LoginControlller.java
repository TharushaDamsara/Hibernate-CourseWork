package edu.ijse.theserenitymentalhealththerapycenter.controller;

import edu.ijse.theserenitymentalhealththerapycenter.bo.BoFactory;
import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.UserBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.UserDto;
import edu.ijse.theserenitymentalhealththerapycenter.util.PasswordEncript.PasswordUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginControlller  {

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox showPasswordCheck;

    @FXML
    private Hyperlink signuplink;

    @FXML
    private TextField usernameField;

    UserBo userBO = (UserBo) BoFactory.getInstance().getboType(BoFactory.botype.User);


    @FXML
    void Usercheck(ActionEvent event) throws IOException {
        String userName = usernameField.getText();
        String password = passwordField.getText();

        if (userName.isEmpty() || password.isEmpty()) {
            System.out.println("Empty");
            return;
        }

        boolean result = userBO.cheackUser(userName);

        if (result) {
            UserDto userDTO = userBO.cheackPassword(userName);

            String role = userDTO.getRole();
            String hashedDTO = userDTO.getPassword();

            System.out.println("In controller" + hashedDTO);
            System.out.println(role);

            boolean isPasswordValid = PasswordUtils.verifyPassword(password, hashedDTO);

            if (!isPasswordValid) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid password");
                alert.show();
            } else {
                if (role.equals("Admin")) {
                    loginPane.getChildren().clear();
                    loginPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/AdminDashboardForm.fxml")));
                } else if (role.equals("Employee")) {
                    loginPane.getChildren().clear();
                    loginPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/ReceptionistDashboardForm.fxml")));
                }
            }
        }
    }

    @FXML
    void showPasswordCheck(ActionEvent event) throws IOException {

    }

    @FXML
    void signin(MouseEvent event) throws IOException {
        Stage window = (Stage)loginButton.getScene().getWindow();
        window.close();
        Parent load = FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"));
        Scene scene = new Scene(load);
        window.setScene(scene);
        window.show();
    }

}
