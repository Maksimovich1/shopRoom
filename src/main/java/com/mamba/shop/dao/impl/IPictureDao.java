package com.mamba.shop.dao.impl;

import com.mamba.shop.dao.PictureDao;
import com.mamba.shop.entity.Picture;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class IPictureDao implements PictureDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public IPictureDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addPicture(Picture picture) {
        sessionFactory.getCurrentSession().persist(picture);
    }

    @Override
    public void deletePicture(Picture picture) {
        sessionFactory.getCurrentSession().delete(picture);

    }
    @Override
    public List<Picture> getAllPictureByApartment(String idApartment) {
        return null;
    }

    @Override
    public Picture getPictureById(int id) {
        System.out.println("#### get pic id =" + id);
       return sessionFactory.getCurrentSession().get(Picture.class, id);
    }
}
