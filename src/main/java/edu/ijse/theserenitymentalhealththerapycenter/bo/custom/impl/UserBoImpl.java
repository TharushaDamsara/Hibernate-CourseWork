package edu.ijse.theserenitymentalhealththerapycenter.bo.custom.impl;

import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.UserBo;
import edu.ijse.theserenitymentalhealththerapycenter.dao.DaoFactory;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.UserDao;
import edu.ijse.theserenitymentalhealththerapycenter.dto.UserDto;
import edu.ijse.theserenitymentalhealththerapycenter.entity.User;
import edu.ijse.theserenitymentalhealththerapycenter.util.PasswordEncript.PasswordUtils;

import java.util.ArrayList;
import java.util.Optional;

public class UserBoImpl implements UserBo {
    @Override
    public UserDto cheackPassword(String userName) {
        User user = userDao.cheackPassword(userName);
        return toUserDTO(user);
    }

    @Override
    public boolean cheackUser(String userName) {
        return userDao.cheackUser(userName);
    }

    UserDao userDao = (UserDao) DaoFactory.getInstance().getSuperDao(DaoFactory.daoType.User);
    @Override
    public Optional<String> getLastPK() {
        return userDao.getLastPK();
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
        String password = PasswordUtils.hashPassword(user.getPassword());
        user.setPassword(password);
        User user1 = toUser(user);
        return userDao.save(user1);
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
    public static UserDto toUserDTO(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }
    public static User toUser(UserDto userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getRole()
        );
    }

}
