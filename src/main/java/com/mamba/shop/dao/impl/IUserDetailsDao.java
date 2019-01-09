package com.mamba.shop.dao.impl;

import com.mamba.shop.dao.UserDetailsDao;
import com.mamba.shop.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class IUserDetailsDao implements UserDetailsDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public IUserDetailsDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findUserByUsername(String username) {
        System.out.println("###___get user by username" + username);
        return sessionFactory.getCurrentSession().get(User.class, username);
    }
}
