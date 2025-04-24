package edu.ijse.theserenitymentalhealththerapycenter.bo.custom.impl;

import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.TheraphistBo;
import edu.ijse.theserenitymentalhealththerapycenter.dao.DaoFactory;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.TheraphistDao;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphistDto;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Theraphist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TheraphistBoImpl implements TheraphistBo {
    TheraphistDao theraphistDao = (TheraphistDao) DaoFactory.getInstance().getSuperDao(DaoFactory.daoType.Therapist);

    @Override
    public List<TheraphistDto> getAll() {
        List<TheraphistDto> theraphistDtos = new ArrayList<>();
        List<Theraphist> theraphists = theraphistDao.getAll();
        for (Theraphist theraphist : theraphists) {
            TheraphistDto theraphistDto = new TheraphistDto();
            theraphistDto.setId(theraphist.getId());
            theraphistDto.setName(theraphist.getName());
            theraphistDto.setSpecialization(theraphist.getSpecialization());
            theraphistDtos.add(theraphistDto);
        }
        return theraphistDtos;
    }

    @Override
    public Optional<String> getLastPK() {
        return Optional.empty();
    }

    @Override
    public boolean save(TheraphistDto theraphistDto) {
        Theraphist theraphist = toEntity(theraphistDto);
        return theraphistDao.save(theraphist);
    }

    @Override
    public boolean update(TheraphistDto theraphistDto) {
        Theraphist theraphist = toEntity(theraphistDto);
        return theraphistDao.update(theraphist);
    }

    @Override
    public boolean deletebypk(String pk) {
        return theraphistDao.deletebypk(pk);
    }

    public static Theraphist toEntity(TheraphistDto theraphistDto){
    if(theraphistDto == null){
        return null;
    }
    Theraphist theraphist = new Theraphist();
    theraphist.setId(theraphistDto.getId());
    theraphist.setName(theraphistDto.getName());
    theraphist.setSpecialization(theraphistDto.getSpecialization());
    return theraphist;
        }
}
