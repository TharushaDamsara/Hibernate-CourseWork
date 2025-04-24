package edu.ijse.theserenitymentalhealththerapycenter.bo.custom.impl;

import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.UserBo;
import edu.ijse.theserenitymentalhealththerapycenter.dto.UserDto;

import java.util.ArrayList;
import java.util.Optional;

public class UserBoImpl implements UserBo {

    @Override
    public Optional<String> getLastPK() {
        return Optional.empty();
    }

    @Override
    public UserDto getUserDetails(String email) {
        return null;
    }

    @Override
    public ArrayList<UserDto> loadTable() {
        return null;
    }

    @Override
    public boolean saveUser(UserDto user) {
        return false;
    }

    @Override
    public boolean isUniqueEmail(String email) {
        return false;
    }

    @Override
    public boolean update(UserDto dto) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean isUniqueEmailForUpdate(String email, int id) {
        return false;
    }
}
