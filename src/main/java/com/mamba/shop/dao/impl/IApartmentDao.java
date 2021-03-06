package com.mamba.shop.dao.impl;

import com.mamba.shop.dao.ApartmentDao;
import com.mamba.shop.entity.Apartment;
import com.mamba.shop.entity.Period;
import com.mamba.shop.entity.custom_entity.SearchCustomModel;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public class IApartmentDao implements ApartmentDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public IApartmentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<Apartment> getAllApartmentListWithDependency() {
        System.out.println("## getAllApartment...");
        Session session = sessionFactory.getCurrentSession();
        return (List<Apartment>) session.getNamedQuery("Apartment.findAllWithDependency").list();
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<Apartment> getAllFreeApartmentsBySearchCustomModelWithDependency(SearchCustomModel customModel) {
        Session session = sessionFactory.getCurrentSession();
        return (List<Apartment>) session
                .getNamedQuery("Apartment.findFreeApartmentsBySearchCustomModelWithDependency")
        .setParameter("p_bedroom", customModel.getBedroom())
        .setParameter("p_people", customModel.getPeople())
        .setParameter("p_children", customModel.getChild())
        .setParameter("p_price", customModel.getPrice())
        .setParameter("p_district", customModel.getDistrict())
        .setParameter("p_enable", 1).list();
    }

    @Override
    public Apartment findByIdWithDependency(String id) {
        System.out.println("## findById id=" + id);
        Session session = sessionFactory.getCurrentSession();
        return (Apartment) session.getNamedQuery("Apartment.findByIdWithDependency")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public Apartment findById(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Apartment.class, id);
    }

    @Override
    public void setDisableApartment(boolean status) {

    }

    @Override
    public void updateApartment(Apartment apartment) {
        sessionFactory.getCurrentSession().saveOrUpdate(apartment);

    }

    @Override
    public void addApartment(Apartment apartment) {
        sessionFactory.getCurrentSession().save(apartment);
        System.out.println("## Add apartment! id =" + apartment.getId());
    }

    @Override
    public void deleteApartment(Apartment apartment) {
        sessionFactory.getCurrentSession().delete(apartment);
        System.out.println("## Delete apartment! id ="+ apartment.getId());
    }

    @Override
    public void addPeriodInApartment(Period period, String id) {
        Apartment apartment = (Apartment) sessionFactory.getCurrentSession()
                .getNamedQuery("Apartment.findByIdWithDependency")
                .setParameter("id", id)
                .setLockMode(LockModeType.WRITE)
                .uniqueResult();
        apartment.getPeriods().add(period);
        sessionFactory.getCurrentSession().saveOrUpdate(apartment);
    }
}
