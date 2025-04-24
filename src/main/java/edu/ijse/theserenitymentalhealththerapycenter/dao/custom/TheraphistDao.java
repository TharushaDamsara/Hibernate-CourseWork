package edu.ijse.theserenitymentalhealththerapycenter.dao.custom;

import edu.ijse.theserenitymentalhealththerapycenter.dao.CrudDao;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Theraphist;
import edu.ijse.theserenitymentalhealththerapycenter.entity.TheraphyPorgramme;

import java.util.ArrayList;

public interface TheraphistDao  extends CrudDao<Theraphist> {
    Theraphist getDetails(String selectedItem);
    ArrayList<Theraphist> getAltherapistIds();


}
