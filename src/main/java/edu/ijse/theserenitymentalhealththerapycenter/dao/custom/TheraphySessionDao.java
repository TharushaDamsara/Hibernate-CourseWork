package edu.ijse.theserenitymentalhealththerapycenter.dao.custom;

import edu.ijse.theserenitymentalhealththerapycenter.dao.CrudDao;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Patient;
import edu.ijse.theserenitymentalhealththerapycenter.entity.TheraphySession;

import java.util.ArrayList;

public interface TheraphySessionDao extends CrudDao<TheraphySession> {
    TheraphySession getDetails(String selectedItem);

    ArrayList<TheraphySession> gettherapyids();


    Double getPayment(String selectedItem);
}
