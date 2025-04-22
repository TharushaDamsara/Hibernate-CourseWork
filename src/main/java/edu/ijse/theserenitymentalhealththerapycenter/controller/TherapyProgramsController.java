package edu.ijse.theserenitymentalhealththerapycenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.ijse.theserenitymentalhealththerapycenter.bo.BoFactory;
import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.TheraphyProgrammeBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphyPorgrammeDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.tm.TheraphyPorgrammeTm;
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
import java.util.List;
import java.util.ResourceBundle;

public class TherapyProgramsController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));

        clearFoarm();
    }

    private void clearFoarm() {
        txtid.setText("");
        txtName.setText("");
        txtCost.setText("");
        txtDuration.setText("");

        loadtbl();
    }

    private void loadtbl() {
        List<TheraphyPorgrammeDto> dtos = bo.getAll();
        ObservableList<TheraphyPorgrammeTm> Tms = FXCollections.observableArrayList();
        for (TheraphyPorgrammeDto dto : dtos) {
            TheraphyPorgrammeTm tm = new TheraphyPorgrammeTm(
                    dto.getProgramId(),
                    dto.getName(),
                    dto.getDuration(),
                    dto.getFee()
            );
            Tms.add(tm);
        }
        tblTherapyprogram.setItems(Tms);
    }
TheraphyProgrammeBo bo = (TheraphyProgrammeBo) BoFactory.getInstance().getboType(BoFactory.botype.TherapyProgramme);
    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnReport;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<Double, TheraphyPorgrammeTm> colCost;

    @FXML
    private TableColumn<String, TheraphyPorgrammeTm> colId;

    @FXML
    private TableColumn<String, TheraphyPorgrammeTm> colDuration;

    @FXML
    private TableColumn<String, TheraphyPorgrammeTm> colName;

    @FXML
    private ImageView exitbtn;

    @FXML
    private TableView<TheraphyPorgrammeTm> tblTherapyprogram;

    @FXML
    private JFXTextField txtCost;

    @FXML
    private JFXTextField txtid;

    @FXML
    private JFXTextField txtDuration;

    @FXML
    private JFXTextField txtName;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String pk = txtid.getText();
        boolean resp = bo.deletebyPk(pk);
        clearFoarm();
    }

    @FXML
    void btnReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtid.getText();
        String name = txtName.getText();
        String duration = txtDuration.getText();
        double cost = Double.parseDouble(txtCost.getText());
         TheraphyPorgrammeDto dto = new TheraphyPorgrammeDto(id, name, duration, cost);
        boolean resp = bo.save(dto);
        clearFoarm();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtid.getText();
        String name = txtName.getText();
        String duration = txtDuration.getText();
        double cost = Double.parseDouble(txtCost.getText());
        TheraphyPorgrammeDto dto = new TheraphyPorgrammeDto(id, name, duration, cost);
        boolean resp = bo.update(dto);
        clearFoarm();
    }

    @FXML
    void exit(MouseEvent event) throws IOException {
        Stage window = (Stage)btnDelete.getScene().getWindow();
        window.close();
        Parent load = FXMLLoader.load(getClass().getResource("/view/AdminDashboardForm.fxml"));
        Scene scene = new Scene(load);
        window.setScene(scene);
        window.show();
    }

    public void tblclicked(MouseEvent mouseEvent) {
        TheraphyPorgrammeTm selectedItem = tblTherapyprogram.getSelectionModel().getSelectedItem();
        txtid.setText(selectedItem.getProgramId());
        txtName.setText(selectedItem.getName());
        txtDuration.setText(selectedItem.getDuration());
        txtCost.setText(String.valueOf(selectedItem.getFee()));


    }
}
