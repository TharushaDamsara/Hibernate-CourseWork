package edu.ijse.theserenitymentalhealththerapycenter.dao.custom;

import edu.ijse.theserenitymentalhealththerapycenter.dao.CrudDao;
import edu.ijse.theserenitymentalhealththerapycenter.entity.TheraphySession;

import java.util.ArrayList;

public interface TheraphySessionDao extends CrudDao<TheraphySession> {
    ArrayList<String> getAlprgrammeIds();

    ArrayList<String> getAlpatientIds();

    ArrayList<String> getAltherapistIds();
}
