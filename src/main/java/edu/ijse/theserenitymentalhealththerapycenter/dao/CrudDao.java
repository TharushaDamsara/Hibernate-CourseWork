package edu.ijse.theserenitymentalhealththerapycenter.dao;

import edu.ijse.theserenitymentalhealththerapycenter.dto.PatientDto;

import java.util.ArrayList;

public interface CrudDao<T> extends SuperDao {
    boolean save(T t);
    boolean update(T t);
    ArrayList<T> getAll();
    boolean deletebypk(String pk);
}
