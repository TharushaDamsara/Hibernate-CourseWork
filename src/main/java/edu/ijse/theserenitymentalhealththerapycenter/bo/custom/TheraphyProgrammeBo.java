package edu.ijse.theserenitymentalhealththerapycenter.bo.custom;

import edu.ijse.theserenitymentalhealththerapycenter.bo.SuperBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphyPorgrammeDto;

import java.util.ArrayList;

public interface TheraphyProgrammeBo extends SuperBo {
    boolean save(TheraphyPorgrammeDto dto);

    ArrayList<TheraphyPorgrammeDto> getAll();
    boolean update(TheraphyPorgrammeDto dto);
    boolean deletebyPk(String pk);
}
