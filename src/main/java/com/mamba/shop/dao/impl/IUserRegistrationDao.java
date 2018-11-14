package com.mamba.shop.dao.impl;

import com.mamba.shop.dao.UserRegistrationDao;
import com.mamba.shop.entity.Authorities;
import com.mamba.shop.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IUserRegistrationDao implements UserRegistrationDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public IUserRegistrationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User userAdd) {
        sessionFactory.getCurrentSession().persist(userAdd);
        System.out.println("###__ user add for name = " + userAdd.getUsername());
    }

    @Override
    public void deleteUser(User userDelete) {
        sessionFactory.getCurrentSession().delete(userDelete);
        System.out.println("###__user delete name = " + userDelete.getUsername());
    }

    @Override
    public void updateUser(User userUpdate) {
        sessionFactory.getCurrentSession().saveOrUpdate(userUpdate);
        System.out.println("###__ user update for name = " + userUpdate.getUsername());
    }

    @Override
    public void addAuth(Authorities authorities) {
        sessionFactory.getCurrentSession().persist(authorities);
        System.out.println("###__ new auth user = "
                + authorities.getUser().getUsername()
                + " role = " + authorities.getAuthority());
    }

    @Override
    public void updateAuth(Authorities authorities) {
        sessionFactory.getCurrentSession().saveOrUpdate(authorities);
        System.out.println("###___ update auth for user = "
                + authorities.getUser().getUsername());
    }

    @Override
    public void deleteAuth(Authorities authorities) {
        sessionFactory.getCurrentSession().delete(authorities);
        System.out.println("###___ delete auth for user = "
                + authorities.getUser().getUsername());
    }
}
