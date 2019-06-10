package com.bnpp.demo.spring.dao;

import com.bnpp.demo.spring.model.demo.*;

import java.util.List;

public interface SeparateTableDao {

    <T> T getById(Long id, Class<T> c);

}
