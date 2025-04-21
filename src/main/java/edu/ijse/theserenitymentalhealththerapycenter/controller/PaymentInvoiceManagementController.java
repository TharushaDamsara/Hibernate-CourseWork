package edu.ijse.theserenitymentalhealththerapycenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.ijse.theserenitymentalhealththerapycenter.bo.BoFactory;
import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.PaymentBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.PaymentDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.tm.PaymentTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentInvoiceManagementController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("amount"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPatients.setCellValueFactory(new PropertyValueFactory<>("patient"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("therapySessionId"));
        colTheropy.setCellValueFactory(new PropertyValueFactory<>("status"));

        datelbl.setText(LocalDate.now().toString());
        loadTabel();
       clearform();

    }

    private void clearform() {
         txtid.setText("");
        txtPayment.setText("");
        datelbl.setText("");
        cbPatients.setValue(null);
        cbTheropy.setValue(null);
        cbStatus.setValue(null);

        loadsatuts();
        loadpatients();
        loadtheropy();
    }

    private void loadtheropy() {

    }

    private void loadpatients() {
    }

    private void loadsatuts() {
     ArrayList<String> payments = new ArrayList<>();
     payments.add("Card Payment");
     payments.add("Cash Payment");
     payments.add("Other");

     ObservableList<String> list = FXCollections.observableArrayList();
     list.addAll(payments);
   cbStatus.setItems(list);

    }

    private void loadTabel() {
//        ArrayList<PaymentDto> paymentDtos = paymentBo.getAll();
//        ObservableList<PaymentTm> paymentTms = FXCollections.observableArrayList();
//        for (PaymentDto paymentDto : paymentDtos) {
//            PaymentTm paymentTm = new PaymentTm(
//                    paymentDto.getId(),
//                    paymentDto.getAmount(),
//                    paymentDto.getDate(),
//                    paymentDto.getPatient(),
//                    paymentDto.getTherapySessionId(),
//                    paymentDto.getStatus()
//            );
//            paymentTms.add(paymentTm);
//        }
//        tblPayment.setItems(paymentTms);
   }

    PaymentBo paymentBo = (PaymentBo) BoFactory.getInstance().getboType(BoFactory.botype.Payment);

    @FXML
    public Label datelbl;

    @FXML
    public TableView<PaymentTm> tblPayment;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnReport;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXComboBox<String> cbPatients;

    @FXML
    private JFXComboBox<String> cbStatus;

    @FXML
    private JFXComboBox<String> cbTheropy;
    @FXML
    private TableColumn<String, PaymentTm> colId;

    @FXML
    private TableColumn<String, PaymentTm> colPatients;

    @FXML
    private TableColumn<Double, PaymentTm> colPayment;

    @FXML
    private TableColumn<String, PaymentTm> colStatus;

    @FXML
    private TableColumn<String, PaymentTm> colTheropy;

    @FXML
    private TableColumn<String, PaymentTm> datecol;

    @FXML
    private JFXTextField txtPayment;

    @FXML
    private JFXTextField txtid;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String pk= txtid.getText();
        boolean resp = paymentBo.deletebypk(pk);
    }

    @FXML
    void btnReportOnAction(ActionEvent event) {


    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtid.getText();
        double payment = Double.parseDouble(txtPayment.getText());
        String date = datelbl.getText();
        String patients = cbPatients.getValue();
        String theropy = cbTheropy.getValue();
        String status = cbStatus.getValue();
        PaymentDto dto = new PaymentDto(id,payment,date,patients,theropy,status);
        boolean resp = paymentBo.save(dto);

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtid.getText();
        double payment = Double.parseDouble(txtPayment.getText());
        String date = datelbl.getText();
        String patients = cbPatients.getValue();
        String theropy = cbTheropy.getValue();
        String status = cbStatus.getValue();
        PaymentDto dto = new PaymentDto(id,payment,date,patients,theropy,status);
        boolean resp = paymentBo.update(dto);
    }

    @FXML
    void exit(MouseEvent event) {

    }

    public void loadpayment(MouseEvent mouseEvent) {
    }

    public void tblclicked(MouseEvent mouseEvent) {
    }
}
