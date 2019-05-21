package com.bnpp.demo.spring.dao;

import java.util.List;

import com.bnpp.demo.spring.model.Product;
import com.bnpp.demo.spring.model.ProductHistory;

public interface ProductDao {
   Product getById(Long id);
   void save(Product Product);
   void update(Product Product);
   void delete(Product Product);
   void saveAll(List<Product> list);
   List<Product> list(int pageNumber);
   List<ProductHistory> getHistory(Long id);

}
