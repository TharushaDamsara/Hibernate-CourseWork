package edu.ijse.theserenitymentalhealththerapycenter.dao.custom.impl;


import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.PaymentDao;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Payment;

import java.util.ArrayList;

public class PaymentDaoImpl implements PaymentDao {
    @Override
    public boolean save(Payment payment) {
        return false;
    }

    @Override
    public boolean update(Payment payment) {
        return false;
    }

    @Override
    public ArrayList<Payment> getAll() {
        return null;
    }

    @Override
    public boolean deletebypk(String pk) {
        return false;
    }
}
