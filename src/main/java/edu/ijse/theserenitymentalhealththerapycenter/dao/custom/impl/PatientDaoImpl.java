package edu.ijse.theserenitymentalhealththerapycenter.dao.custom.impl;

import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.PatientDao;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Patient;

import java.util.ArrayList;

public class PatientDaoImpl implements PatientDao {
    @Override
    public boolean save(Patient patient) {
        return false;
    }

    @Override
    public boolean update(Patient patient) {
        return false;
    }

    @Override
    public ArrayList<Patient> getAll() {
        return null;
    }

    @Override
    public boolean deletebypk(String pk) {
        return false;
    }
}
