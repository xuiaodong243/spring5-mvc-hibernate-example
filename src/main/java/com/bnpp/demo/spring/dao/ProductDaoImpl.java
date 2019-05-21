package com.bnpp.demo.spring.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.bnpp.demo.spring.interceptor.AutoTableNameInterceptor;
import com.bnpp.demo.spring.model.Product;
import com.bnpp.demo.spring.model.ProductHistory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements ProductDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public Product getById(Long id){
      return sessionFactory.getCurrentSession().get(Product.class, id);
   }

   @Override
   public void save(Product product) {
      sessionFactory.getCurrentSession().save(product);
   }

   @Override
   public void update(Product product) {
      sessionFactory.getCurrentSession().update(product);
   }
   @Override
   public void delete(Product product) {
      sessionFactory.getCurrentSession().delete(product);
   }

   @Override
   public void saveAll(List<Product> list) {

      Session session = sessionFactory.openSession();
      Transaction tx = session.beginTransaction();
      for ( int i=0; i<list.size(); i++ ) {
         session.save(list.get(i));
      }
      tx.commit();
      session.close();
   }

   @Override
   public List<Product> list(int pageNumber) {
       int pageSize = 10;
      @SuppressWarnings("unchecked")
      TypedQuery<Product> query = sessionFactory.getCurrentSession().createQuery("from Product f ORDER BY f.id desc");
      query.setFirstResult((pageNumber - 1) * pageSize);
      query.setMaxResults(pageSize);
      return query.getResultList();
   }

   @Override
   public List<ProductHistory> getHistory(Long id) {
      char lastChar = String.valueOf(id).charAt(String.valueOf(id).length() - 1);
      AutoTableNameInterceptor interceptor = new AutoTableNameInterceptor("TBL_PRODUCTS_HISTORY_0",String.valueOf(lastChar));
      Session session = sessionFactory.withOptions().interceptor(interceptor).openSession();

      CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
      CriteriaQuery<ProductHistory> criteriaQuery = criteriaBuilder.createQuery(ProductHistory.class);
      Root<ProductHistory> root = criteriaQuery.from(ProductHistory.class);
      criteriaQuery.select(root);
      criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

      @SuppressWarnings("unchecked")
      TypedQuery<ProductHistory> query = session.createQuery(criteriaQuery);
      List<ProductHistory> pList = query.getResultList();

      pList.stream().forEach(p -> {
         System.out.println("Test : "+p.getUpdateDt() + " " + p.getUpdateBy());
      });

      return pList;

   }

}
