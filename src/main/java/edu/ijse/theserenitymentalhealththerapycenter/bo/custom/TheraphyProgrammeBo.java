package edu.ijse.theserenitymentalhealththerapycenter.bo.custom;

import edu.ijse.theserenitymentalhealththerapycenter.bo.SuperBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphyPorgrammeDto;

import java.util.List;
import java.util.Optional;

public interface TheraphyProgrammeBo extends SuperBo {
    boolean save(TheraphyPorgrammeDto dto);
    public Optional<String> getLastPK();
    List<TheraphyPorgrammeDto> getAll();
    boolean update(TheraphyPorgrammeDto dto);
    boolean deletebyPk(String pk);
}
