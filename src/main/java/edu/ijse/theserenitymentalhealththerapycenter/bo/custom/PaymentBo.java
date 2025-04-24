package edu.ijse.theserenitymentalhealththerapycenter.bo.custom;

import edu.ijse.theserenitymentalhealththerapycenter.bo.SuperBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.PaymentDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PaymentBo extends SuperBo {

    public Optional<String> getLastPK();
    List<PaymentDto> getAll();

    boolean deletebypk(String pk);

    boolean save(PaymentDto dto);

    boolean update(PaymentDto dto);

    ArrayList<String> gettheraphyIds();

    ArrayList<String> getpatientIds();
}
