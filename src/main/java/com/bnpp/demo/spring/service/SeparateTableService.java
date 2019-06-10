package com.bnpp.demo.spring.service;

import com.bnpp.demo.spring.model.demo.*;

import java.util.List;

public interface SeparateTableService {

   public <T> T getObjectById(Long id, Class<T> c);

}
