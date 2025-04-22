package edu.ijse.theserenitymentalhealththerapycenter.dao.custom.impl;


import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.TheraphySessionDao;
import edu.ijse.theserenitymentalhealththerapycenter.entity.TheraphySession;

import java.util.ArrayList;
import java.util.List;

public class TheraphySessonDaoImpl implements TheraphySessionDao {
    @Override
    public boolean save(TheraphySession theraphySession) {
        return false;
    }

    @Override
    public boolean update(TheraphySession theraphySession) {
        return false;
    }

    @Override
    public List<TheraphySession> getAll() {
        return null;
    }

    @Override
    public boolean deletebypk(String pk) {
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
