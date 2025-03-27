package edu.ijse.theserenitymentalhealththerapycenter.bo.custom;

import edu.ijse.theserenitymentalhealththerapycenter.bo.SuperBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphistDto;

import java.util.ArrayList;

public interface TheraphistBo extends SuperBo {
    ArrayList<TheraphistDto> getAll();

    boolean save(TheraphistDto theraphistDto);

    boolean update(TheraphistDto theraphistDto);

    boolean deletebypk(String pk);
}
