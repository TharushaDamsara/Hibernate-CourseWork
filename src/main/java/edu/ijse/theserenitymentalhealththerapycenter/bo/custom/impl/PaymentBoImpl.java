package edu.ijse.theserenitymentalhealththerapycenter.bo.custom.impl;

import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.PaymentBo;
import edu.ijse.theserenitymentalhealththerapycenter.dao.DaoFactory;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.PatientDao;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.PaymentDao;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.TheraphySessionDao;
import edu.ijse.theserenitymentalhealththerapycenter.dto.PatientDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.PaymentDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphySessionDto;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Patient;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Payment;
import edu.ijse.theserenitymentalhealththerapycenter.entity.TheraphySession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentBoImpl implements PaymentBo {
   PaymentDao paymentDao = (PaymentDao) DaoFactory.getInstance().getSuperDao(DaoFactory.daoType.Payment);
   PatientDao patientDao = (PatientDao) DaoFactory.getInstance().getSuperDao(DaoFactory.daoType.Patient);
   TheraphySessionDao theraphySessionDao = (TheraphySessionDao) DaoFactory.getInstance().getSuperDao(DaoFactory.daoType.TherapySession);

    @Override
    public Optional<String> getLastPK() {
        return paymentDao.getLastPK();
    }

    @Override
    public List<PaymentDto> getAll() {
        List<Payment> payments = paymentDao.getAll();
        List<PaymentDto> paymentDtos = new ArrayList<PaymentDto>();
        for (Payment payment : payments) {

            PatientDto patientDto = new PatientDto();
            patientDto.setId(payment.getId());

            TheraphySessionDto theraphySessionDto = new TheraphySessionDto();
            theraphySessionDto.setId(payment.getTherapySession().getId());


            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setId(payment.getId());
            paymentDto.setAmount(payment.getAmount());
            paymentDto.setDate(payment.getDate());
            paymentDto.setPatient(patientDto);
            paymentDto.setTherapySession(theraphySessionDto);
            paymentDto.setStatus(payment.getStatus());

            paymentDtos.add(paymentDto);
            }
        return paymentDtos;
        }


    @Override
    public boolean deletebypk(String pk) {
        return paymentDao.deletebypk(pk);
    }

    @Override
    public boolean save(PaymentDto dto) {
        Payment entity = toEntity(dto);
        return paymentDao.save(entity);
    }

    @Override
    public boolean update(PaymentDto dto) {
        Payment entity = toEntity(dto);
        return paymentDao.update(entity);
    }

    @Override
    public ArrayList<String> gettheraphyIds() {
      ArrayList<TheraphySession> ids =  theraphySessionDao.gettherapyids();
      ArrayList<String> id = new ArrayList<>();
      for (TheraphySession session : ids) {
          id.add(session.getId());
      }
        return id;
    }

    @Override
    public ArrayList<String> getpatientIds() {
        ArrayList<Patient> alpatientIds = patientDao.getAlpatientIds();
        ArrayList<String> id = new ArrayList<>();
        for (Patient patient : alpatientIds) {
            id.add(patient.getId());
        }
        return id;
    }

    @Override
    public Double getpayment(String selectedItem) {

        return theraphySessionDao.getPayment(selectedItem);
    }


    public static Payment toEntity(PaymentDto paymentDTO){
        if (paymentDTO == null) return null;

        Patient patient = new Patient();
        patient.setId(paymentDTO.getPatient().getId());

        TheraphySession session = new TheraphySession();
        session.setId(paymentDTO.getTherapySession().getId());

        return new Payment(
                paymentDTO.getId(),
                paymentDTO.getAmount(),
                paymentDTO.getDate(),
                paymentDTO.getStatus(),
                patient,
                session
        );
    }
}
