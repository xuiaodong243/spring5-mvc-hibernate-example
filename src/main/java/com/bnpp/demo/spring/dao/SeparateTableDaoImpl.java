package com.bnpp.demo.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeparateTableDaoImpl implements SeparateTableDao {


    @Autowired
    @Qualifier("sessionFactory3")
    private SessionFactory sessionFactory;

    @Override
    public <T> T getById(Long id, Class<T> c) {
        Session session = sessionFactory.openSession();
        T t = session.get(c, id);
        session.close();
        return t;
    }
}
