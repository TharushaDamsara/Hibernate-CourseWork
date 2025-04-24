package edu.ijse.theserenitymentalhealththerapycenter.bo.custom;

import edu.ijse.theserenitymentalhealththerapycenter.bo.SuperBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.PatientDto;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientBo extends SuperBo {
    public Optional<String> getLastPK();
    boolean save(PatientDto patientDto);
    boolean update(PatientDto patientDto);
    List<PatientDto> getAll();
    boolean deletebypk(String pk);
}
