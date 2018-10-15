package com.mamba.shop.dao.impl;

import com.mamba.shop.dao.UserDetailsDao;
import com.mamba.shop.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class IUserDetailsDao implements UserDetailsDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public IUserDetailsDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserByUsername(String username) {
        return sessionFactory.getCurrentSession().get(User.class, username);
    }
}
