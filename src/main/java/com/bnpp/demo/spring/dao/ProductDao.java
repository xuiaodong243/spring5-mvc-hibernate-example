package com.bnpp.demo.spring.dao;

import java.util.List;

import com.bnpp.demo.spring.model.Product1;
import com.bnpp.demo.spring.model.Product1History;

public interface ProductDao {
   Product1 getById(Long id);
   void save(Product1 Product);
   void update(Product1 Product);
   void delete(Product1 Product);
   void saveAll(List<Product1> list);
   List<Product1> list(int pageNumber);
   List<Product1History> getHistory(Long id);

}
