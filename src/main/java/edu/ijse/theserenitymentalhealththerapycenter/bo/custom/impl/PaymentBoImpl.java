package edu.ijse.theserenitymentalhealththerapycenter.bo.custom.impl;

import edu.ijse.theserenitymentalhealththerapycenter.bo.BoFactory;
import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.PaymentBo;
import edu.ijse.theserenitymentalhealththerapycenter.dao.DaoFactory;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.PatientDao;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.PaymentDao;
import edu.ijse.theserenitymentalhealththerapycenter.dto.PatientDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.PaymentDto;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Patient;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Payment;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Theraphist;

import java.util.ArrayList;

public class PaymentBoImpl implements PaymentBo {
   PaymentDao paymentDao = (PaymentDao) DaoFactory.getInstance().getSuperDao(DaoFactory.daoType.Payment);

    @Override
    public ArrayList<PaymentDto> getAll() {
        return null;

    }

    @Override
    public boolean deletebypk(String pk) {
        return false;
    }

    @Override
    public boolean save(PaymentDto dto) {
        return false;
    }

    @Override
    public boolean update(PaymentDto dto) {
        return false;
    }

    public static Payment toEntity(PaymentDto paymentDTO){
//        if(paymentDTO==null)return null;
//        Payment payment = new Payment();
//        payment.setId(paymentDTO.getId());
//        payment.setAmount(paymentDTO.getAmount());
//        payment.setDate(paymentDTO.getDate());
//        payment.setStatus(paymentDTO.getStatus());
//        payment.setPatient(paymentDTO.getPatient());
        return null;
    }
}
