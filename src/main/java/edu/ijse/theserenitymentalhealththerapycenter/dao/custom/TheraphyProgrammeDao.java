package edu.ijse.theserenitymentalhealththerapycenter.dao.custom;

import edu.ijse.theserenitymentalhealththerapycenter.dao.CrudDao;
import edu.ijse.theserenitymentalhealththerapycenter.entity.TheraphyPorgramme;

import java.util.ArrayList;

public interface TheraphyProgrammeDao extends CrudDao<TheraphyPorgramme> {
    TheraphyPorgramme getDetails(String selectedItem);
    ArrayList<TheraphyPorgramme> getAlprgrammeIds();
}
