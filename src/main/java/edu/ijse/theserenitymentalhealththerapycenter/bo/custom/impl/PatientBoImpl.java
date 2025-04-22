package edu.ijse.theserenitymentalhealththerapycenter.bo.custom.impl;

import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.PatientBo;
import edu.ijse.theserenitymentalhealththerapycenter.dao.DaoFactory;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.PatientDao;
import edu.ijse.theserenitymentalhealththerapycenter.dto.PatientDto;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientBoImpl implements PatientBo {
    PatientDao dao = (PatientDao) DaoFactory.getInstance().getSuperDao(DaoFactory.daoType.Patient);

    @Override
    public boolean save(PatientDto patientDto) {
        Patient patient = toEntity(patientDto);
        return dao.save(patient);

    }

    @Override
    public boolean update(PatientDto patientDto) {
        Patient patient = toEntity(patientDto);
        return dao.update(patient);
    }

    @Override

    public List<PatientDto> getAll() {
        List<PatientDto> users = new ArrayList<>();
        List<Patient> all = dao.getAll();

        for (Patient patient : all) {
            users.add(new PatientDto(
                    patient.getId(),
                    patient.getName(),
                    patient.getContactInfo(),
                    patient.getGender(),
                    patient.getBirthDate()
            ));
        }
        return users;
    }


    @Override
    public boolean deletebypk(String pk) {
        return dao.deletebypk(pk);
    }
    public static Patient toEntity(PatientDto patientDTO) {
        if (patientDTO == null) {
            return null;
        }
        return new Patient(
                patientDTO.getId(),
                patientDTO.getName(),
                patientDTO.getContactInfo(),
                patientDTO.getGender(),
                patientDTO.getBirthDate(),
                null
        );
    }
}
