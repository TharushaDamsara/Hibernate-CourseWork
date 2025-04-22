package edu.ijse.theserenitymentalhealththerapycenter.bo.custom;

import edu.ijse.theserenitymentalhealththerapycenter.bo.SuperBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphyPorgrammeDto;

import java.util.List;

public interface TheraphyProgrammeBo extends SuperBo {
    boolean save(TheraphyPorgrammeDto dto);

    List<TheraphyPorgrammeDto> getAll();
    boolean update(TheraphyPorgrammeDto dto);
    boolean deletebyPk(String pk);
}
