package edu.ijse.theserenitymentalhealththerapycenter.bo.custom;

import edu.ijse.theserenitymentalhealththerapycenter.bo.SuperBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.PaymentDto;

import java.util.ArrayList;

public interface PaymentBo extends SuperBo {

    ArrayList<PaymentDto> getAll();

    boolean deletebypk(String pk);

    boolean save(PaymentDto dto);

    boolean update(PaymentDto dto);
}
