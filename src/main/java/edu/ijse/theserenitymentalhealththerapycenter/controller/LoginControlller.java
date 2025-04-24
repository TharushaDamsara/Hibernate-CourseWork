package edu.ijse.theserenitymentalhealththerapycenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

    @FXML
    void Usercheck(ActionEvent event) {

    }

    @FXML
    void showPasswordCheck(ActionEvent event) {

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
