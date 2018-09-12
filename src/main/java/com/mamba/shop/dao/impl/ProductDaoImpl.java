package com.mamba.shop.dao.impl;

import com.mamba.shop.dao.ProductDao;
import com.mamba.shop.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class ProductDaoImpl implements ProductDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getAllProduct() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Product> productList = session.createQuery("from Product").list();
        transaction.commit();
        session.close();
        System.out.println("## Все номера были успешно полученцы");
        return productList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getProductForParam(int countPeople, int countChild, double priceMin, double priceMax) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryStr = "from Product where " +
                "count_people=:people and count_child=:child " +
                "and price>=:minp and price<=:maxp";
        Query query = session.createQuery(queryStr);
        query.setParameter("people",countPeople);
        query.setParameter("child",countChild);
        query.setParameter("minp",priceMin);
        query.setParameter("maxp",priceMax);
        List<Product> list = query.list();
        transaction.commit();
        session.close();
        System.out.println("## Выборка была успешна");
        return list;
    }

    @Override
    public void addProduct(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(product);
        transaction.commit();
        session.close();
        System.out.println("## Номер " + product.getId() + "был добавлен");
    }

    @Override
    public void deleteProduct(Product product) {
        sessionFactory.openSession().delete(product);
        System.out.println("## Номер " + product.getId() + "был добавлен");
    }

    @Override
    public void updateProduct(Product product) {

    }
}
