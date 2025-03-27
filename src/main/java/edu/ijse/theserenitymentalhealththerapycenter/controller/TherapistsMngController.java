package edu.ijse.theserenitymentalhealththerapycenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.ijse.theserenitymentalhealththerapycenter.bo.BoFactory;
import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.TheraphistBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphistDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.tm.TheraphistTm;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Theraphist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TherapistsMngController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("specialization"));

        loadtbl();
        clearform();
    }

    private void clearform() {
    txtid.setText("");
    txtName.setText("");
    txtStatus.setText("");

    loadtbl();
    }

    private void loadtbl() {
                ArrayList<TheraphistDto> theropistDtos = bo.getAll();
        ObservableList<TheraphistTm> therapistTms = FXCollections.observableArrayList();
        for (TheraphistDto therapisttDto :theropistDtos) {
            TheraphistTm therapistTm = new TheraphistTm(
                    therapisttDto.getId(),
                    therapisttDto.getName(),
                    therapisttDto.getSpecialization()
            );
            therapistTms.add(therapistTm);
        }
        tblTherapist.setItems(therapistTms);
    }

TheraphistBo bo = (TheraphistBo) BoFactory.getInstance().getboType(BoFactory.botype.Therapist);

    @FXML
    private ImageView Exitbtn;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnReport;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<String, TheraphistTm> colName;

    @FXML
    private TableColumn<String, TheraphistTm> colStatus;
    @FXML
    private TableColumn<String, TheraphistTm> colid;

    @FXML
    private TableView<TheraphistTm> tblTherapist;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtStatus;

    @FXML
    private JFXTextField txtid;


    @FXML
    void Exit(MouseEvent event) throws IOException {
        Stage window = (Stage)btnDelete.getScene().getWindow();
        window.close();
        Parent load = FXMLLoader.load(getClass().getResource("/view/AdminDashboardForm.fxml"));
        Scene scene = new Scene(load);
        window.setScene(scene);
        window.show();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String pk = txtid.getText();
        boolean resp = bo.deletebypk(pk);
    }

    @FXML
    void btnReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtid.getText();
        String name = txtName.getText();
        String spec = txtStatus.getText();

        TheraphistDto theraphistDto = new TheraphistDto(id, name, spec);
        boolean resp = bo.save(theraphistDto);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtid.getText();
        String name = txtName.getText();
        String spec = txtStatus.getText();

        TheraphistDto theraphistDto = new TheraphistDto(id, name, spec);
        boolean resp = bo.update(theraphistDto);
    }

}
