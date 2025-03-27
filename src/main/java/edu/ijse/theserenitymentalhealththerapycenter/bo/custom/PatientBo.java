package edu.ijse.theserenitymentalhealththerapycenter.bo.custom;

import edu.ijse.theserenitymentalhealththerapycenter.bo.SuperBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.PatientDto;

import java.util.ArrayList;

public interface PatientBo extends SuperBo {
    boolean save(PatientDto patientDto);
    boolean update(PatientDto patientDto);
    ArrayList<PatientDto> getAll();
    boolean deletebypk(String pk);
}
