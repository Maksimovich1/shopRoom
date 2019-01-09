package com.mamba.shop.dao.impl;

import com.mamba.shop.dao.UserRegistrationDao;
import com.mamba.shop.entity.Authorities;
import com.mamba.shop.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.NestedServletException;

import java.sql.SQLException;
import java.util.List;

@Repository
public class IUserRegistrationDao implements UserRegistrationDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public IUserRegistrationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getUserByUsernameWithDependency(String username) {
        System.out.println("### get user by username with dependency");
        Session session = sessionFactory.getCurrentSession();
        return (User) session.getNamedQuery("User.findByUserNameWithDependency")
                .setParameter("username", username)
                .uniqueResult();
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

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        String sql = "from User";
        return (List<User>) session.createQuery(sql).list();
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

    @SuppressWarnings("unchecked")
    @Override
    public boolean duplicateEmail(String email) {
        String sql = "from User where email = :email1";
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session.createQuery(sql).setParameter("email1", email).list();
        if (users != null){
            if (users.size() != 0)
                return true;
        }
        return false;
    }
}
