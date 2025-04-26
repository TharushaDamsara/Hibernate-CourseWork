package edu.ijse.theserenitymentalhealththerapycenter.controller;

import edu.ijse.theserenitymentalhealththerapycenter.bo.BoFactory;
import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.UserBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private Button btSignUp;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private ChoiceBox<String> choiceRole;

    @FXML
    private Label lblError;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    UserBo userBO = (UserBo) BoFactory.getInstance().getboType(BoFactory.botype.User);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceRole.getItems().setAll("Admin", "Employee");
    }

    @FXML
    void navLogInPage(ActionEvent event) throws IOException {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        String role = choiceRole.getValue();
        String confirmPassword = txtConfirmPassword.getText();

        String lastId = userBO.getLastPK().orElse("U000");
        String newId = generateNextId(lastId);

        System.out.println(newId);
        System.out.println(userName);
        System.out.println(password);
        System.out.println(role);
        System.out.println(confirmPassword);

        if (userName.isEmpty() || password.isEmpty() || role == null || confirmPassword.isEmpty()) {
            lblError.setText("Please fill all the fields");
            return;
        }

        if (password.length() < 8) {
            lblError.setText("Password must be at least 8 characters");
            return;
        }

        if (!password.equals(confirmPassword)) {
            lblError.setText("Password does not match");
            return;
        }

        if (userBO.cheackUser(userName)) {
            lblError.setText("User already exists");
            return;
        }

        UserDto userDTO = new UserDto();
        userDTO.setId(newId);
        userDTO.setUsername(userName);
        userDTO.setPassword(password);
        userDTO.setRole(role);

        boolean result = userBO.saveUser(userDTO);

        if (result) {
            mainAnchor.getChildren().clear();
            mainAnchor.getChildren().add(FXMLLoader.load(getClass().getResource("/view/LogIn.fxml")));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("User sign up failed");
            alert.show();
        }
    }

    private String generateNextId(String currentId) {
        if (currentId == null || currentId.isEmpty()) {
            return "U001";
        }
        int numericPart = Integer.parseInt(currentId.substring(1)); // remove 'U'
        numericPart++; // increment
        return String.format("U%03d", numericPart); // format like U001, U002
    }
}
