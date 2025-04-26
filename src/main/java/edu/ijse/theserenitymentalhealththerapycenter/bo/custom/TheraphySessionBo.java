package edu.ijse.theserenitymentalhealththerapycenter.bo.custom;

import edu.ijse.theserenitymentalhealththerapycenter.bo.SuperBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphySessionDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TheraphySessionBo extends SuperBo {
    List<TheraphySessionDto> getAll();
    public Optional<String> getLastPK();
    boolean save(TheraphySessionDto dto);

    boolean update(TheraphySessionDto dto);

    boolean deleteByPk(String pk);

    ArrayList<String> getAlprgrammeIds();

    ArrayList<String> getAlpatientIds();

    ArrayList<String> getAltherapistIds();

    double getFee(String selectedItem);
}
