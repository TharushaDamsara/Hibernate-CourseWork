package edu.ijse.theserenitymentalhealththerapycenter.bo.custom.impl;

import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.TheraphyProgrammeBo;
import edu.ijse.theserenitymentalhealththerapycenter.dao.DaoFactory;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.TheraphyProgrammeDao;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphyPorgrammeDto;
import edu.ijse.theserenitymentalhealththerapycenter.entity.TheraphyPorgramme;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TheraphyProgrammeBoImpl implements TheraphyProgrammeBo {
    TheraphyProgrammeDao theraphyProgrammeDao = (TheraphyProgrammeDao) DaoFactory.getInstance().getSuperDao(DaoFactory.daoType.TherapyProgramme);

    @Override
    public boolean save(TheraphyPorgrammeDto dto) {
        TheraphyPorgramme theraphyPorgramme = toEntity(dto);
        return theraphyProgrammeDao.save(theraphyPorgramme);
    }

    @Override
    public Optional<String> getLastPK() {
        return Optional.empty();
    }

    @Override
    public List<TheraphyPorgrammeDto> getAll() {
      List<TheraphyPorgrammeDto> theraphyPorgrammeDtos = new ArrayList<>();
        List<TheraphyPorgramme> theraphyPorgrammes = theraphyProgrammeDao.getAll();
        for (TheraphyPorgramme t : theraphyPorgrammes) {
            TheraphyPorgrammeDto dto = new TheraphyPorgrammeDto();
            dto.setProgramId(t.getProgramId());
            dto.setName(t.getName());
            dto.setFee(t.getFee());
            dto.setDuration(t.getDuration());
            theraphyPorgrammeDtos.add(dto);
        }
        return theraphyPorgrammeDtos;
    }

    @Override
    public boolean update(TheraphyPorgrammeDto dto) {
        TheraphyPorgramme t = toEntity(dto);
        return theraphyProgrammeDao.update(t);
    }

    @Override
    public boolean deletebyPk(String pk) {
        return theraphyProgrammeDao.deletebypk(pk);
    }
    public TheraphyPorgramme toEntity(TheraphyPorgrammeDto dto) {
        if (dto == null) return null;
        TheraphyPorgramme entity = new TheraphyPorgramme();
        entity.setProgramId(dto.getProgramId());
        entity.setName(dto.getName());
        entity.setFee(dto.getFee());
        entity.setDuration(dto.getDuration());
        return entity;
    }
}
