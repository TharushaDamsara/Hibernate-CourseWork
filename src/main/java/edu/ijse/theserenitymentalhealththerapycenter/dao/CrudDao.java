package edu.ijse.theserenitymentalhealththerapycenter.dao;

import edu.ijse.theserenitymentalhealththerapycenter.dto.PatientDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CrudDao<T> extends SuperDao {
    boolean save(T t);
    boolean update(T t);
    List<T> getAll();
    boolean deletebypk(String pk);
    public Optional<String> getLastPK();
}
