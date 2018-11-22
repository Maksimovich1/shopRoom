package com.mamba.shop.service.impl;

import com.mamba.shop.dao.UserDetailsDao;
import com.mamba.shop.dao.UserRegistrationDao;
import com.mamba.shop.entity.Authorities;
import com.mamba.shop.entity.User;
import com.mamba.shop.service.RegistrationService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class IRegistrationService implements RegistrationService {

    @Value(value = "${app.service_code}")
    private String serviceCode;

    private final UserRegistrationDao registrationDao;
    private final UserDetailsDao userDetailsDao;

    @Autowired
    public IRegistrationService(UserRegistrationDao registrationDao,
                                UserDetailsDao userDetailsDao) {
        this.registrationDao = registrationDao;
        this.userDetailsDao = userDetailsDao;
    }

    @Override
    public void regUser(String username, String password, String email,
                           String phone, String service_code)
            throws IllegalAccessException, IllegalArgumentException {

        int codeServiceOutside = Integer.parseInt(service_code);
        int codeServiceInside = Integer.parseInt(this.serviceCode);

        if (codeServiceOutside != codeServiceInside) // код верен юзер прошел верификацию
            throw new IllegalAccessException("Не верный код верификации");
        if (userDetailsDao.findUserByUsername(username) != null) {
            System.out.println("DUPLICATE USERNAME_______________");
            throw new IllegalArgumentException("Данный email или имя есть в системе.");
        }
        if (registrationDao.duplicateEmail(email)) {
            System.out.println("DUPLICATE EMAIL___________________");
            throw new IllegalArgumentException("Данный email или имя есть в системе.");
        }
        try{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodePass = encoder.encode(password);
            User user = new User();
            user.setUsername(username);
            user.setPassword(encodePass);
            user.setPhone(phone);
            user.setEmail(email);
            user.setEnabled(false); // юзер подтвержден
            registrationDao.addUser(user);
            Authorities authorities = new Authorities();
            authorities.setUser(user);
            authorities.setAuthority("ROLE_USER");
            registrationDao.addAuth(authorities);
            System.out.println("###___ reg success for user" + username);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUser() {

        return null;
    }

    @Override
    public void disableOrEnableUser(String username, boolean enabled) throws NotFoundException {
        User user = userDetailsDao.findUserByUsername(username);
        if (user != null){
            user.setEnabled(enabled);
        }
        else throw new NotFoundException("Пользователь не найден");
    }
}
