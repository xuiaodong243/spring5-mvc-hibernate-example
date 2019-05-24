package com.bnpp.demo.spring.service;

import java.util.List;

import com.bnpp.demo.spring.model.Product1;
import com.bnpp.demo.spring.model.Product1History;

public interface ProductService {

   Product1 getById(Long id);
   void save(Product1 product);
   void update(Product1 product);
   void delete(Product1 product);
   void saveAll(List<Product1> list);
   List<Product1> list(int pageNumber);
   List<Product1History> getHistory(Long id);
}
