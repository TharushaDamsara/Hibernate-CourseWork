package edu.ijse.theserenitymentalhealththerapycenter.bo.custom.impl;

import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.TheraphySessionBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.TheraphySessionDto;

import java.util.ArrayList;

public class TheraphySessonBoImpl implements TheraphySessionBo {
    @Override
    public ArrayList<TheraphySessionDto> getAll() {
        return null;
    }

    @Override
    public boolean save(TheraphySessionDto dto) {
        return false;
    }

    @Override
    public boolean update(TheraphySessionDto dto) {
        return false;
    }

    @Override
    public boolean deleteByPk(String pk) {
        return false;
    }

    @Override
    public ArrayList<String> getAlprgrammeIds() {
        return null;
    }

    @Override
    public ArrayList<String> getAlpatientIds() {
        return null;
    }

    @Override
    public ArrayList<String> getAltherapistIds() {
        return null;
    }
}
