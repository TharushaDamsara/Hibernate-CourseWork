package edu.ijse.theserenitymentalhealththerapycenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.ijse.theserenitymentalhealththerapycenter.bo.BoFactory;
import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.PatientBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.PatientDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.tm.PatientTm;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PatientsMngController implements Initializable {

    @FXML
    private ImageView ExitBnt;

    @FXML
    private DatePicker bdaypicker;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<PatientTm, String> colbday;

    @FXML
    private TableColumn<PatientTm, String> colcontact;

    @FXML
    private TableColumn<PatientTm, String> colgender;

    @FXML
    private TableColumn<PatientTm, String> colid;

    @FXML
    private TableColumn<PatientTm, String> colname;

    @FXML
    private JFXTextField contatcttxt;

    @FXML
    private ComboBox<String> genderpicker;

    @FXML
    private JFXTextField nametxt;

    @FXML
    private JFXTextField patientidtxt;

    @FXML
    private TableView<PatientTm> tblPatient;

    PatientBo patientBo = (PatientBo) BoFactory.getInstance().getboType(BoFactory.botype.Patient);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String patientid = patientidtxt.getText();
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patientid);
        boolean resp = patientBo.delete(patientDto);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String patientid = patientidtxt.getText();
        String name = nametxt.getText();
        String contact = contatcttxt.getText();
        String gender = genderpicker.getValue();
        String bday = String.valueOf(bdaypicker.getValue());

        PatientDto patientDto = new PatientDto(patientid,name,contact,gender,bday);
        boolean resp = patientBo.save(patientDto);

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String patientid = patientidtxt.getText();
        String name = nametxt.getText();
        String contact = contatcttxt.getText();
        String gender = genderpicker.getValue();
        String bday = String.valueOf(bdaypicker.getValue());
        System.out.println(gender);
        System.out.println(bday.toString());

        PatientDto patientDto = new PatientDto(patientid,name,contact,gender,bday);
        boolean resp = patientBo.update(patientDto);
    }

    @FXML
    void exit(MouseEvent event) throws IOException {
        Stage window = (Stage)btnDelete.getScene().getWindow();
        window.close();
        Parent load = FXMLLoader.load(getClass().getResource("/view/ReceptionistDashboardForm.fxml"));
        Scene scene = new Scene(load);
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colcontact.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
        colgender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colbday.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        
        reload();
        genderpick();
    }

    private void genderpick() {
       String male = "Male";
       String female = "Female";
        ArrayList<String> genderlist = new ArrayList<>();
        genderlist.add(male);
        genderlist.add(female);

        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(genderlist);
        genderpicker.setItems(list);

    }

    private void reload() {
        tabelLoad();
        patientidtxt.setText("");
        nametxt.setText("");
        contatcttxt.setText("");
        genderpicker.setValue("");
        bdaypicker.setValue(null);

    }

    private void tabelLoad() {
    ArrayList<PatientDto> patientDtos = patientBo.getAll();
    ObservableList<PatientTm> patients = FXCollections.observableArrayList();
    for (PatientDto patientDto : patientDtos) {
        PatientTm patientTm = new PatientTm(
                patientDto.getId(),
                patientDto.getName(),
                patientDto.getContactInfo(),
                patientDto.getGender(),
                patientDto.getBirthDate()
        );
        patients.add(patientTm);
    }
    tblPatient.setItems(patients);
    }
}
