package edu.ijse.theserenitymentalhealththerapycenter.bo.custom;

import edu.ijse.theserenitymentalhealththerapycenter.bo.SuperBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.UserDto;

import java.util.ArrayList;
import java.util.Optional;

public interface UserBo extends SuperBo {
    public Optional<String> getLastPK();
    UserDto getUserDetails(String email);
    ArrayList<UserDto> loadTable();
    boolean saveUser(UserDto user);
    boolean isUniqueEmail(String email);
    boolean update(UserDto dto);
    boolean delete(int id);
    boolean isUniqueEmailForUpdate(String email, int id);
}
