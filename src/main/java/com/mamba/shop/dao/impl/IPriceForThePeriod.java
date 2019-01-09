package com.mamba.shop.dao.impl;

import com.mamba.shop.dao.PriceForPeriodDao;
import com.mamba.shop.entity.PriceForThePeriod;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IPriceForThePeriod implements PriceForPeriodDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public IPriceForThePeriod(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PriceForThePeriod> getPriceForThePeriodByIdApartment(String idApartment) {
        System.out.println("### get All PriceForThePeriod by idApartment = " + idApartment);
        Session session = sessionFactory.getCurrentSession();
        return session.getNamedQuery("PriceForThePeriod.getAllPriceForThePeriod")
                .setParameter("id", idApartment).list();
    }

    @Override
    public void addPriceForPeriod(PriceForThePeriod priceForThePeriod) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(priceForThePeriod);
        System.out.println("### Add new PriceForPeriod promocia!" );
    }

    @Override
    public void deletePriceForPeriod(int id) {

    }
}
