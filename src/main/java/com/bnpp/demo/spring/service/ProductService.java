package com.bnpp.demo.spring.service;

import java.util.List;

import com.bnpp.demo.spring.model.Product;
import com.bnpp.demo.spring.model.ProductHistory;

public interface ProductService {

   Product getById(Long id);
   void save(Product product);
   void update(Product product);
   void delete(Product product);
   void saveAll(List<Product> list);
   List<Product> list(int pageNumber);
   List<ProductHistory> getHistory(Long id);
}
