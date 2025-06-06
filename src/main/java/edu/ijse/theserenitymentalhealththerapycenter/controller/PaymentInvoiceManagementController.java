package edu.ijse.theserenitymentalhealththerapycenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.ijse.theserenitymentalhealththerapycenter.bo.BoFactory;
import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.PaymentBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.PatientDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.PaymentDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphySessionDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.tm.PaymentTm;
import edu.ijse.theserenitymentalhealththerapycenter.util.AlertsPack.CustomAlerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentInvoiceManagementController implements Initializable {

    private PatientDto patientDto;
    private TheraphySessionDto theraphySessionDto;
    private final PaymentBo paymentBo = (PaymentBo) BoFactory.getInstance().getboType(BoFactory.botype.Payment);

    @FXML
    private Label datelbl;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private JFXButton btnDelete, btnReport, btnSave, btnUpdate;

    @FXML
    private JFXComboBox<String> cbPatients, cbStatus, cbTheropy;

    @FXML
    private TableColumn<PaymentTm, String> colId;

    @FXML
    private TableColumn<PaymentTm, String> colPatients;

    @FXML
    private TableColumn<PaymentTm, Double> colPayment;

    @FXML
    private TableColumn<PaymentTm, String> colStatus;

    @FXML
    private TableColumn<PaymentTm, String> colTheropy;

    @FXML
    private TableColumn<PaymentTm, String> datecol;

    @FXML
    private JFXTextField txtPayment, txtid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("amount"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPatients.setCellValueFactory(new PropertyValueFactory<>("patient"));
        colTheropy.setCellValueFactory(new PropertyValueFactory<>("therapySessionId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        colStatus.setVisible(true); // Make sure the column is visible

        datelbl.setText(LocalDate.now().toString());
        loadTabel();
        clearform();

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }


    private void clearform() {
        txtid.setText("");
        txtPayment.setText("");
        datelbl.setText(LocalDate.now().toString());
        cbPatients.setValue(null);
        cbTheropy.setValue(null);
        cbStatus.setValue(null);
        loadTabel();
        loadsatuts();
        loadpatients();
        loadtheropy();
        genarateId();
    }

    private void genarateId() {
        txtid.setText(String.valueOf(paymentBo.getLastPK().orElse("Eror")));
    }

    private void loadtheropy() {
        ArrayList<String> ids = paymentBo.gettheraphyIds();
        cbTheropy.setItems(FXCollections.observableArrayList(ids));
    }

    private void loadpatients() {
        ArrayList<String> ids = paymentBo.getpatientIds();
        cbPatients.setItems(FXCollections.observableArrayList(ids));
    }

    private void loadsatuts() {
        ObservableList<String> list = FXCollections.observableArrayList("Card Payment", "Cash Payment", "Other");
        cbStatus.setItems(list);
    }

    private void loadTabel() {
        List<PaymentDto> paymentDtos = paymentBo.getAll();
        ObservableList<PaymentTm> paymentTms = FXCollections.observableArrayList();
        for (PaymentDto paymentDto : paymentDtos) {
            PaymentTm paymentTm = new PaymentTm(
                    paymentDto.getId(),
                    paymentDto.getAmount(),
                    paymentDto.getDate(),
                    paymentDto.getPatient().getId(),
                    paymentDto.getTherapySession().getId(),
                    paymentDto.getStatus()
            );
            paymentTms.add(paymentTm);
        }
        tblPayment.setItems(paymentTms);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String pk = txtid.getText();
        boolean resp = paymentBo.deletebypk(pk);
        if (resp) {
            loadTabel();
            clearform();
            CustomAlerts.delete();
        }
        else {
            showError(" somthing Wrong Can not be deleted ");
        }
    }

    @FXML
    void btnReportOnAction(ActionEvent event) {
        // Add report generation logic here if needed
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtid.getText();
        String paymentText = txtPayment.getText();

        // Check if the payment field is empty or invalid
        if (paymentText == null || paymentText.trim().isEmpty()) {
            showError("Payment field cannot be empty");
            return;
        }

        double payment = 0;
        try {
            payment = Double.parseDouble(paymentText);  // Try parsing the input
        } catch (NumberFormatException e) {
            // Handle the exception if input is not a valid number
            showError("Invalid payment amount");
            return;  // Exit method if invalid
        }

        String date = datelbl.getText();
        String patients = cbPatients.getValue();
        String theropy = cbTheropy.getValue();
        String status = cbStatus.getValue();
        patientDto = new PatientDto();
        patientDto.setId(patients);

        theraphySessionDto = new TheraphySessionDto();
        theraphySessionDto.setId(theropy);

    if (id.isEmpty()||payment==0||date==null||patients==null||theropy==null||status == null) {
        showError(" fields cannot be empty");
        return;
    }
        PaymentDto dto = new PaymentDto(id, payment, date, patientDto, theraphySessionDto, status);
        boolean resp = paymentBo.save(dto);
        if (resp) {
            loadTabel();
            clearform();
            CustomAlerts.saved();
            System.out.println(status);
        }
        else {
            showError(" something Wrong Can not be saved ");
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtid.getText();
        String paymentText = txtPayment.getText();

        // Check if the payment field is empty or invalid
        if (paymentText == null || paymentText.trim().isEmpty()) {
            showError("Payment field cannot be empty");
            return;
        }

        double payment = 0;
        try {
            payment = Double.parseDouble(paymentText);  // Try parsing the input
        } catch (NumberFormatException e) {
            // Handle the exception if input is not a valid number
            showError("Invalid payment amount");
            return;  // Exit method if invalid
        }

        String date = datelbl.getText();
        String patients = cbPatients.getValue();
        String theropy = cbTheropy.getValue();
        String status = cbStatus.getValue();

        patientDto = new PatientDto();
        patientDto.setId(patients);

        theraphySessionDto = new TheraphySessionDto();
        theraphySessionDto.setId(theropy);

        if (id.isEmpty()||payment==0||date==null||patients==null||theropy==null||status == null) {
            showError("fields cannot be empty");
            return;
        }
            PaymentDto dto = new PaymentDto(id, payment, date, patientDto, theraphySessionDto, status);
        boolean resp = paymentBo.update(dto);
        if (resp) {
            loadTabel();
            clearform();
            CustomAlerts.update();
        }
        else {
            showError(" something Wrong Can not be updated ");
        }
    }

    @FXML
    void exit(MouseEvent event) throws IOException {
        Stage window = (Stage)btnUpdate.getScene().getWindow();
        window.close();
        Parent load = FXMLLoader.load(getClass().getResource("/view/ReceptionistDashboardForm.fxml"));
        Scene scene = new Scene(load);
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void tblclicked(MouseEvent mouseEvent) {
        PaymentTm selectedItem = tblPayment.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtid.setText(selectedItem.getId());
            txtPayment.setText(String.valueOf(selectedItem.getAmount()));
            datelbl.setText(selectedItem.getDate());
            cbStatus.setValue(selectedItem.getStatus());
            cbPatients.setValue(selectedItem.getPatient());
            cbTheropy.setValue(selectedItem.getTherapySessionId());

            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
            btnSave.setDisable(true);
        }
    }

    public void loadpayment(MouseEvent mouseEvent) {
    }

    // Method to show error alerts
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void getpayment(ActionEvent actionEvent) {
        String selectedItem = cbTheropy.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
           Double payment = paymentBo.getpayment(selectedItem);
           txtPayment.setText(String.valueOf(payment));
        }
    }
}
