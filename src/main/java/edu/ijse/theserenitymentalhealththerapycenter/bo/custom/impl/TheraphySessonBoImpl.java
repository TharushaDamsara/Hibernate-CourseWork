package edu.ijse.theserenitymentalhealththerapycenter.bo.custom.impl;

import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.TheraphySessionBo;
import edu.ijse.theserenitymentalhealththerapycenter.dao.DaoFactory;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.PatientDao;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.TheraphistDao;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.TheraphyProgrammeDao;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.TheraphySessionDao;
import edu.ijse.theserenitymentalhealththerapycenter.dto.PatientDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphistDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphyPorgrammeDto;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphySessionDto;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Patient;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Theraphist;
import edu.ijse.theserenitymentalhealththerapycenter.entity.TheraphyPorgramme;
import edu.ijse.theserenitymentalhealththerapycenter.entity.TheraphySession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TheraphySessonBoImpl implements TheraphySessionBo {
    TheraphySessionDao theraphySessionDao = (TheraphySessionDao) DaoFactory.getInstance().getSuperDao(DaoFactory.daoType.TherapySession);
    TheraphyProgrammeDao theraphyProgrammeDao = (TheraphyProgrammeDao) DaoFactory.getInstance().getSuperDao(DaoFactory.daoType.TherapyProgramme);
    TheraphistDao theraphistDao = (TheraphistDao) DaoFactory.getInstance().getSuperDao(DaoFactory.daoType.Therapist);
    PatientDao patientDao = (PatientDao) DaoFactory.getInstance().getSuperDao(DaoFactory.daoType.Patient);

    @Override
    public List<TheraphySessionDto> getAll() {
        List<TheraphySession> theraphySessions = theraphySessionDao.getAll();
        List<TheraphySessionDto> theraphySessionDtos = new ArrayList<>();

        for (TheraphySession theraphySession : theraphySessions) {
            TheraphySessionDto theraphySessionDto = new TheraphySessionDto();
            theraphySessionDto.setId(theraphySession.getId());
            theraphySessionDto.setDate(theraphySession.getDate());
            theraphySessionDto.setTime(theraphySession.getTime());
            theraphySessionDto.setStatus(theraphySession.getStatus());

            // Handle therapist
            if (theraphySession.getTherapist() != null) {
                Theraphist theraphist = theraphistDao.getDetails(theraphySession.getTherapist().getId());
                if (theraphist != null) {
                    TheraphistDto therapistDto = new TheraphistDto(
                            theraphist.getId(),
                            theraphist.getName(),
                            theraphist.getSpecialization()
                    );
                    theraphySessionDto.setTherapist(therapistDto);
                }
            }

            // Handle patient
            if (theraphySession.getPatient() != null) {
                Patient patient = patientDao.getDetails(theraphySession.getPatient().getId());
                if (patient != null) {
                    PatientDto patientDto = new PatientDto(
                            patient.getId(),
                            patient.getName(),
                            patient.getContactInfo(),
                            patient.getGender(),
                            patient.getBirthDate()
                    );
                    theraphySessionDto.setPatient(patientDto);
                }
            }

            // Handle therapy program
            if (theraphySession.getTherapyProgram() != null) {
                TheraphyPorgramme programme = theraphyProgrammeDao.getDetails(theraphySession.getTherapyProgram().getProgramId());
                if (programme != null) {
                    TheraphyPorgrammeDto programmeDto = new TheraphyPorgrammeDto(
                            programme.getProgramId(),
                            programme.getName(),
                            programme.getDuration(),
                            programme.getFee()
                    );
                    theraphySessionDto.setTherapyProgram(programmeDto);
                }

            }
            theraphySessionDto.setFee(theraphySession.getFee());
            theraphySessionDtos.add(theraphySessionDto);
        }

        return theraphySessionDtos;
}

    @Override
    public Optional<String> getLastPK() {
        return theraphySessionDao.getLastPK();
    }

    @Override
    public boolean save(TheraphySessionDto dto) {
        TheraphySession entity = toEntity(dto);
        return theraphySessionDao.save(entity);
    }

    @Override
    public boolean update(TheraphySessionDto dto) {
        TheraphySession entity = toEntity(dto);
        return theraphySessionDao.update(entity);
    }

    @Override
    public boolean deleteByPk(String pk) {
        return theraphySessionDao.deletebypk(pk);
    }

    @Override
    public ArrayList<String> getAlprgrammeIds() {
        ArrayList<TheraphyPorgramme> alprgrammeIds = theraphyProgrammeDao.getAlprgrammeIds();
        ArrayList<String> alprgrammeIdList = new ArrayList<>();
        for (TheraphyPorgramme p : alprgrammeIds) {
            alprgrammeIdList.add(p.getProgramId());
        }
        return alprgrammeIdList;
    }

    @Override
    public ArrayList<String> getAlpatientIds() {
        ArrayList<Patient> alpatientIds = patientDao.getAlpatientIds();
        ArrayList<String> alpatientIdList = new ArrayList<>();
        for (Patient p : alpatientIds) {
            alpatientIdList.add(p.getId());
        }
        return alpatientIdList;
    }

    @Override
    public ArrayList<String> getAltherapistIds() {
        ArrayList<Theraphist> altherapistIds = theraphistDao.getAltherapistIds();
        ArrayList<String> altherapistIdList = new ArrayList<>();
        for (Theraphist p : altherapistIds) {
            altherapistIdList.add(p.getId());
        }
        return altherapistIdList;
    }

    @Override
    public double getFee(String selectedItem) {
        return theraphyProgrammeDao.getFee(selectedItem);
    }

    public TheraphySession toEntity(TheraphySessionDto dto) {
        if (theraphySessionDao == null) {
            return null;
        }
        TheraphySession tps = new TheraphySession();
        tps.setId(dto.getId());
        tps.setDate(dto.getDate());
        tps.setTime(dto.getTime());
        tps.setStatus(dto.getStatus());

        tps.setTherapist(dto.getTherapist()!=null? new Theraphist(
                dto.getTherapist().getId(),
                dto.getTherapist().getName(),
                dto.getTherapist().getSpecialization(),
                null
        ): null);

        tps.setPatient(dto.getPatient()!=null? new Patient(
                dto.getPatient().getId(),
                dto.getPatient().getName(),
                dto.getPatient().getContactInfo(),
                dto.getPatient().getGender(),
                dto.getPatient().getBirthDate(),
                null
        ): null);

        tps.setTherapyProgram(dto.getTherapyProgram()!=null? new TheraphyPorgramme(
              dto.getTherapyProgram().getProgramId(),
              dto.getTherapyProgram().getName(),
              dto.getTherapyProgram().getDuration(),
              dto.getTherapyProgram().getFee(),
              null
        ):null);

        tps.setFee(dto.getFee());

        return tps;
    }
}
