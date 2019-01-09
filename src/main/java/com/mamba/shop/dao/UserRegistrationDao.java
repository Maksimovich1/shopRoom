package com.mamba.shop.dao;

import com.mamba.shop.entity.Authorities;
import com.mamba.shop.entity.User;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLException;
import java.util.List;

public interface UserRegistrationDao {

    void addUser(User userAdd);
    void deleteUser(User userDelete);
    void updateUser(User userUpdate);
    List<User> getAllUsers();
    User getUserByUsernameWithDependency(String username);

    /*auth*/
    void addAuth(Authorities authorities);
    void updateAuth(Authorities authorities);
    void deleteAuth(Authorities authorities);

    boolean duplicateEmail(String email);
}
