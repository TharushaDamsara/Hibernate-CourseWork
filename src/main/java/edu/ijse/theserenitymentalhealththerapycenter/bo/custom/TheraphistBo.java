package edu.ijse.theserenitymentalhealththerapycenter.bo.custom;

import edu.ijse.theserenitymentalhealththerapycenter.bo.SuperBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphistDto;

import java.util.List;
import java.util.Optional;

public interface TheraphistBo extends SuperBo {
    List<TheraphistDto> getAll();
    public Optional<String> getLastPK();
    boolean save(TheraphistDto theraphistDto);

    boolean update(TheraphistDto theraphistDto);

    boolean deletebypk(String pk);
}
