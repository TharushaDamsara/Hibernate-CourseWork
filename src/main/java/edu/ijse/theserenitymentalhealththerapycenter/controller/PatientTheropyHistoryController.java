package edu.ijse.theserenitymentalhealththerapycenter.controller;

import edu.ijse.theserenitymentalhealththerapycenter.dto.PatientDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.PatientHistoryDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.tm.PatientHistoryTm;
import edu.ijse.theserenitymentalhealththerapycenter.dto.tm.PatientTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class PatientTheropyHistoryController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPatient.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colTheropy.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("theraphy"));

        loadtbl();
    }

    private void loadtbl() {
//        ArrayList<PatientHistoryDto> patientDtos = PathentT.getAll();
//        ObservableList<PatientHistoryTm> patients = FXCollections.observableArrayList();
//        for (PatientHistoryDto patientDto : patientDtos) {
//            PatientHistoryTm patientTm = new PatientHistoryTm()(
//                    patientDto.getId(),
//                    patientDto.getPayment(),
//                    patientDto.getStatus(),
//                    patientDto.getTheraphy()
//            );
//            patients.add(patientTm);
//        }
//        tblPatientTheropyHistory.setItems(patients);
//    }
    }

    @FXML
    public ImageView exitbtn;

    @FXML
    private TableColumn<String, PatientHistoryTm> colPatient;

    @FXML
    private TableColumn<Double, PatientHistoryTm> colPayment;

    @FXML
    private TableColumn<String, PatientHistoryTm> colStatus;

    @FXML
    private TableColumn<String, PatientHistoryTm> colTheropy;

    @FXML
    private TableView<PatientHistoryTm> tblPatientTheropyHistory;

    public void exit(MouseEvent mouseEvent) throws IOException {
        Stage window = (Stage)exitbtn.getScene().getWindow();
        window.close();
        Parent load = FXMLLoader.load(getClass().getResource("/view/AdminDashboardForm.fxml"));
        Scene scene = new Scene(load);
        window.setScene(scene);
        window.show();
    }
}
