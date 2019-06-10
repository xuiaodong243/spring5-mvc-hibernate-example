package com.bnpp.demo.spring.service;

import com.bnpp.demo.spring.dao.SeparateTableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false, value="transactionManager3")
public class SeparateTableServiceImpl implements SeparateTableService{

    @Autowired
    private SeparateTableDao separateTableDao;

    @Override
    public <T> T getObjectById(Long id, Class<T> c) {
        return separateTableDao.getById(id,c);
    }
}
