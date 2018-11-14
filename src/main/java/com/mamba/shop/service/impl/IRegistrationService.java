package com.mamba.shop.service.impl;

import com.mamba.shop.dao.UserRegistrationDao;
import com.mamba.shop.entity.Authorities;
import com.mamba.shop.entity.User;
import com.mamba.shop.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class IRegistrationService implements RegistrationService {

    private final UserRegistrationDao registrationDao;

    @Autowired
    public IRegistrationService(UserRegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }

    @Override
    public boolean regUser(String username, String password) {

        try{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodePass = encoder.encode(password);
            User user = new User();
            user.setUsername(username);
            user.setPassword(encodePass);
            user.setEnabled(true); //по умолчанию аккаунт не подтвержден
            registrationDao.addUser(user);
            Authorities authorities = new Authorities();
            authorities.setUser(user);
            authorities.setAuthority("ROLE_USER");
            registrationDao.addAuth(authorities);
            System.out.println("###___ reg success for user" + username);
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
