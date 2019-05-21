package com.bnpp.demo.spring.service;

import java.util.List;

import com.bnpp.demo.spring.model.Product;
import com.bnpp.demo.spring.model.ProductHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bnpp.demo.spring.dao.ProductDao;

@Service
public class ProductServiceImpl implements ProductService {

   @Autowired
   private ProductDao productDao;

   @Transactional
   public Product getById(Long id) {
      return productDao.getById(id);
   }

   @Transactional
   public void save(Product product) {
      productDao.save(product);
   }

   @Transactional
   public void update(Product product) {
      productDao.update(product);
   }

   @Transactional
   public void delete(Product product) {
      productDao.delete(product);
   }

   @Transactional(readOnly = true)
   public List<Product> list(int pageNumber) {
      return productDao.list(pageNumber);
   }

   @Transactional
   public void saveAll(List<Product> list){
      productDao.saveAll(list);
   };

   @Transactional(readOnly = true)
   public List<ProductHistory> getHistory(Long id){
      return productDao.getHistory(id);
   }

}
