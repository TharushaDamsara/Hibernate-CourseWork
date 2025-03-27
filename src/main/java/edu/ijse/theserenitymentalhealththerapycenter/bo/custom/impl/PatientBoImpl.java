package edu.ijse.theserenitymentalhealththerapycenter.bo.custom.impl;

import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.PatientBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.PatientDto;

import java.util.ArrayList;

public class PatientBoImpl implements PatientBo {

    @Override
    public boolean save(PatientDto patientDto) {
        return false;
    }

    @Override
    public boolean update(PatientDto patientDto) {
        return false;
    }

    @Override
    public ArrayList<PatientDto> getAll() {
        return null;
    }

    @Override
    public boolean deletebypk(String pk) {
        return false;
    }
}
