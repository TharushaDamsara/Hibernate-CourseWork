package edu.ijse.theserenitymentalhealththerapycenter.dao.custom.impl;


import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.UserDao;
import edu.ijse.theserenitymentalhealththerapycenter.entity.User;

import java.util.ArrayList;

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
    public ArrayList<User> getAll() {
        return null;
    }

    @Override
    public boolean deletebypk(String pk) {
        return false;
    }
}
