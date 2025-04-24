package edu.ijse.theserenitymentalhealththerapycenter.dao.custom;

import edu.ijse.theserenitymentalhealththerapycenter.dao.CrudDao;
import edu.ijse.theserenitymentalhealththerapycenter.dto.PatientDto;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Patient;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Theraphist;

import java.util.ArrayList;

public interface PatientDao extends CrudDao<Patient> {
    Patient getDetails(String selectedItem);
    ArrayList<Patient> getAlpatientIds();

}
