package edu.ijse.theserenitymentalhealththerapycenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.ijse.theserenitymentalhealththerapycenter.bo.BoFactory;
import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.TheraphySessionBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.PatientDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphistDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphyPorgrammeDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphySessionDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.tm.TheraphySessionTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TherapySessionSchedulingController implements Initializable {

    private final TheraphySessionBo bo = (TheraphySessionBo) BoFactory.getInstance().getboType(BoFactory.botype.TherapySession);

    @FXML
    private JFXButton btnDelete, btnSave, btnUpdate;

    @FXML
    private JFXComboBox<String> cbPatient, cbTheropy, cbTheropyprogramme;

    @FXML
    private TableColumn<String, TheraphySessionTm> colDate, colStatus, colTime, colid, colpatient, coltherapy, colthrpyprgrm,colFee;


    @FXML
    private DatePicker dpSessionDate;

    @FXML
    private ImageView exitbtn;

    @FXML
    private TableView<TheraphySessionTm> tbtTherapyShedule;

    @FXML
    private JFXTextField txtid, txtstaus;

    @FXML
    private TextField txttime;

    @FXML
    private Label txtpayment;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        coltherapy.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        colpatient.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colthrpyprgrm.setCellValueFactory(new PropertyValueFactory<>("therapyProgramId"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        loadCombos();
        loadTable();
        clearForm();
    }

    private void loadCombos() {
        loadTherapistCombo();
        loadPatientCombo();
        loadProgrammeCombo();
    }

    private void loadTherapistCombo() {
        ArrayList<String> ids = bo.getAltherapistIds();
        cbTheropy.setItems(FXCollections.observableArrayList(ids));
    }

    private void loadPatientCombo() {
        ArrayList<String> ids = bo.getAlpatientIds();
        cbPatient.setItems(FXCollections.observableArrayList(ids));
    }

    private void loadProgrammeCombo() {
        ArrayList<String> ids = bo.getAlprgrammeIds();
        cbTheropyprogramme.setItems(FXCollections.observableArrayList(ids));
    }

    private void loadTable() {

        List<TheraphySessionDto> dtos = bo.getAll();
        ObservableList<TheraphySessionTm> tms = FXCollections.observableArrayList();

        for (TheraphySessionDto dto : dtos) {
            String therapistId = dto.getTherapist() != null ? dto.getTherapist().getId() : "N/A";
            String patientId = dto.getPatient() != null ? dto.getPatient().getId() : "N/A";
            String programId = dto.getTherapyProgram() != null ? dto.getTherapyProgram().getProgramId() : "N/A";

            tms.add(new TheraphySessionTm(dto.getId(), dto.getDate(), dto.getTime(), dto.getStatus(), therapistId, patientId, programId,dto.getFee()));

        }

        tbtTherapyShedule.setItems(tms);
    }

    private void clearForm() {
        txtid.clear();
        txtstaus.clear();
        txttime.clear();
        cbTheropy.setValue(null);
        cbPatient.setValue(null);
        cbTheropyprogramme.setValue(null);
        dpSessionDate.setValue(null);

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
      getnarateNextId();
        loadTable();
    }

    private void getnarateNextId() {
        txtid.setText(String.valueOf(bo.getLastPK().orElse("Eror")));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String pk = txtid.getText();
        if (pk != null && !pk.isEmpty()) {
            bo.deleteByPk(pk);
        }
        clearForm();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtid.getText();
        String date = String.valueOf(dpSessionDate.getValue());
        String time = txttime.getText();
        String status = txtstaus.getText();

        String therapistId = cbTheropy.getValue();
        String patientId = cbPatient.getValue();
        String programId = cbTheropyprogramme.getValue();

        TheraphistDto therapist = new TheraphistDto();
        therapist.setId(therapistId);

        PatientDto patient = new PatientDto();
        patient.setId(patientId);

        TheraphyPorgrammeDto program = new TheraphyPorgrammeDto();
        program.setProgramId(programId);

        double fee = Double.parseDouble(txtpayment.getText());

        TheraphySessionDto dto = new TheraphySessionDto(id, date, time, status, therapist, patient, program,fee);

        bo.save(dto);
        clearForm();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtid.getText();
        String date = String.valueOf(dpSessionDate.getValue());
        String time = txttime.getText();
        String status = txtstaus.getText();

        String therapistId = cbTheropy.getValue();
        String patientId = cbPatient.getValue();
        String programId = cbTheropyprogramme.getValue();

        TheraphistDto therapist = new TheraphistDto();
        therapist.setId(therapistId);

        PatientDto patient = new PatientDto();
        patient.setId(patientId);

        TheraphyPorgrammeDto program = new TheraphyPorgrammeDto();
        program.setProgramId(programId);

        double fee = Double.parseDouble(txtpayment.getText());
        TheraphySessionDto dto = new TheraphySessionDto(id, date, time, status, therapist, patient, program,fee);

        bo.update(dto);
        clearForm();
    }

    @FXML
    void exit(MouseEvent event) throws IOException {
        Stage window = (Stage)exitbtn.getScene().getWindow();
        window.close();
        Parent load = FXMLLoader.load(getClass().getResource("/view/ReceptionistDashboardForm.fxml"));
        Scene scene = new Scene(load);
        window.setScene(scene);
        window.show();
    }

    @FXML
    void tblcliked(MouseEvent mouseEvent) {
        TheraphySessionTm selectedItem = tbtTherapyShedule.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtid.setText(selectedItem.getId());
            txttime.setText(selectedItem.getTime());
            txtstaus.setText(selectedItem.getStatus());
            dpSessionDate.setValue(LocalDate.parse(selectedItem.getDate()));
            cbTheropyprogramme.setValue(selectedItem.getTherapyProgramId());
            cbPatient.setValue(selectedItem.getPatientId());
            cbTheropy.setValue(selectedItem.getTherapistId());

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    public void getFee(ActionEvent actionEvent) {
        String selectedItem = cbTheropyprogramme.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            double fee = bo.getFee(selectedItem);
            txtpayment.setText(String.valueOf(fee));
        }
    }

}
