package edu.ijse.theserenitymentalhealththerapycenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.ijse.theserenitymentalhealththerapycenter.bo.BoFactory;
import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.TheraphySessionBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphySessionDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.tm.TheraphySessionTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TherapySessionSchedulingController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        coltherapy.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        colpatient.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colthrpyprgrm.setCellValueFactory(new PropertyValueFactory<>("therapyProgramId"));

        loadtbl();
        clearForm();
    }

    private void clearForm() {
    }

    private void loadtbl() {
        ArrayList<TheraphySessionDto> dtos = bo.getAll();
        ObservableList<TheraphySessionTm> tms = FXCollections.observableArrayList();
        for (TheraphySessionDto dto :dtos) {
            TheraphySessionTm tm = new TheraphySessionTm(
                    dto.getId(),
                    dto.getDate(),
                    dto.getTime(),
                    dto.getStatus(),
                    dto.getTherapistId(),
                    dto.getPatientId(),
                    dto.getTherapyProgramId()
            );
            tms.add(tm);
        }
        tbtTherapyShedule.setItems(tms);
    }

    TheraphySessionBo bo = (TheraphySessionBo) BoFactory.getInstance().getboType(BoFactory.botype.TherapySession);

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXComboBox<String> cbPatient;

    @FXML
    private JFXComboBox<String> cbTheropy;

    @FXML
    private JFXComboBox<String> cbTheropyprogramme;

    @FXML
    private TableColumn<String, TheraphySessionTm> colDate;

    @FXML
    private TableColumn<String, TheraphySessionTm> colStatus;

    @FXML
    private TableColumn<String, TheraphySessionTm> colTime;

    @FXML
    private TableColumn<String, TheraphySessionTm> colid;

    @FXML
    private TableColumn<String, TheraphySessionTm> colpatient;

    @FXML
    private TableColumn<String, TheraphySessionTm> coltherapy;

    @FXML
    private TableColumn<String, TheraphySessionTm> colthrpyprgrm;

    @FXML
    private DatePicker dpSessionDate;

    @FXML
    private ImageView exitbtn;

    @FXML
    private TableView<TheraphySessionTm> tbtTherapyShedule;

    @FXML
    private JFXTextField txtid;

    @FXML
    private JFXTextField txtstaus;

    @FXML
    private TextField txttime;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String pk = txtid.getText();
        boolean resp= bo.deleteByPk(pk);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtid.getText();
        String date = String.valueOf(dpSessionDate.getValue());
        String time = txttime.getText();
        String sttus = txtstaus.getText();
        String theropist = cbTheropy.getValue();
        String patient = cbPatient.getValue();
        String programme = cbTheropyprogramme.getValue();

        TheraphySessionDto dto = new TheraphySessionDto(id, date, time, sttus, theropist, patient, programme);
        boolean resp= bo.save(dto);


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtid.getText();
        String date = String.valueOf(dpSessionDate.getValue());
        String time = txttime.getText();
        String sttus = txtstaus.getText();
        String theropist = cbTheropy.getValue();
        String patient = cbPatient.getValue();
        String programme = cbTheropyprogramme.getValue();

        TheraphySessionDto dto = new TheraphySessionDto(id, date, time, sttus, theropist, patient, programme);
        boolean resp= bo.update(dto);
    } 

    @FXML
    void exit(MouseEvent event) {

    }

}
