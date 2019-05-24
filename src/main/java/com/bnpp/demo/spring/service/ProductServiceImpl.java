package com.bnpp.demo.spring.service;

import java.util.List;

import com.bnpp.demo.spring.model.Product1;
import com.bnpp.demo.spring.model.Product1History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bnpp.demo.spring.dao.ProductDao;

@Service
@Transactional(readOnly = false, value="transactionManager")
public class ProductServiceImpl implements ProductService {

   @Autowired
   private ProductDao productDao;

   public Product1 getById(Long id) {
      return productDao.getById(id);
   }

   public void save(Product1 product) {
      productDao.save(product);
   }

   public void update(Product1 product) {
      productDao.update(product);
   }

   public void delete(Product1 product) {
      productDao.delete(product);
   }

   public List<Product1> list(int pageNumber) {
      return productDao.list(pageNumber);
   }

   public void saveAll(List<Product1> list){
      productDao.saveAll(list);
   };

   public List<Product1History> getHistory(Long id){
      return productDao.getHistory(id);
   }

}
