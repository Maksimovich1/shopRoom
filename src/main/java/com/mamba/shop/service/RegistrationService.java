package com.mamba.shop.service;


import com.mamba.shop.entity.User;
import javassist.NotFoundException;

import java.util.List;

public interface RegistrationService {

    void regUser(String username, String pass, String email,
                    String phone, String service_cod)
            throws IllegalAccessException, IllegalArgumentException;
    List<User> getAllUser();
    void disableOrEnableUser(String username, boolean enabled) throws NotFoundException;
}
