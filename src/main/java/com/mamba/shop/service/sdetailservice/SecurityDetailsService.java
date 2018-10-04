package com.mamba.shop.service.sdetailservice;

import com.mamba.shop.dao.UserDetailsDao;
import com.mamba.shop.entity.Authorities;
import com.mamba.shop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityDetailsService implements UserDetailsService {

    private final UserDetailsDao detailsDao;

    @Autowired
    public SecurityDetailsService(UserDetailsDao detailsDao) {
        this.detailsDao = detailsDao;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = detailsDao.findUserByUsername(username);
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder = null;

        if (user != null){
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
            userBuilder.disabled(user.isEnabled());
            userBuilder.password(user.getPassword());
            String []auth = user.getAuthorities().
                    stream().map(Authorities::getAuthority).toArray(String[]::new);
            userBuilder.authorities(auth);

        }else throw new UsernameNotFoundException("User not found");
        return userBuilder.build();
    }
}
