package edu.ijse.theserenitymentalhealththerapycenter.dao.custom.impl;


import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.UserDao;
import edu.ijse.theserenitymentalhealththerapycenter.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public boolean deletebypk(String pk) {
        return false;
    }

    @Override
    public Optional<String> getLastPK() {
        return Optional.empty();
    }
}
