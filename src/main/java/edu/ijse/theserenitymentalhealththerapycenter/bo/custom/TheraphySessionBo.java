package edu.ijse.theserenitymentalhealththerapycenter.bo.custom;

import edu.ijse.theserenitymentalhealththerapycenter.bo.SuperBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphySessionDto;

import java.util.ArrayList;

public interface TheraphySessionBo extends SuperBo {
    ArrayList<TheraphySessionDto> getAll();

    boolean save(TheraphySessionDto dto);

    boolean update(TheraphySessionDto dto);

    boolean deleteByPk(String pk);

    ArrayList<String> getAlprgrammeIds();

    ArrayList<String> getAlpatientIds();

    ArrayList<String> getAltherapistIds();
}
