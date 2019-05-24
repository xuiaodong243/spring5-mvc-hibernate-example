package com.bnpp.demo.spring.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.bnpp.demo.spring.interceptor.AutoTableNameInterceptor;
import com.bnpp.demo.spring.model.Product1;
import com.bnpp.demo.spring.model.Product1History;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements ProductDao {

   @Autowired
   @Qualifier("sessionFactory")
   private SessionFactory sessionFactory;

   @Override
   public Product1 getById(Long id){
      return sessionFactory.getCurrentSession().get(Product1.class, id);
   }

   @Override
   public void save(Product1 product) {
      sessionFactory.getCurrentSession().save(product);
   }

   @Override
   public void update(Product1 product) {
      sessionFactory.getCurrentSession().update(product);
   }
   @Override
   public void delete(Product1 product) {
      sessionFactory.getCurrentSession().delete(product);
   }

   @Override
   public void saveAll(List<Product1> list) {

      Session session = sessionFactory.openSession();
      Transaction tx = session.beginTransaction();
      for ( int i=0; i<list.size(); i++ ) {
         session.save(list.get(i));
      }
      tx.commit();
      session.close();
   }

   @Override
   public List<Product1> list(int pageNumber) {
       int pageSize = 10;
      @SuppressWarnings("unchecked")
      TypedQuery<Product1> query = sessionFactory.getCurrentSession().createQuery("from Product1 f ORDER BY f.id desc");
      query.setFirstResult((pageNumber - 1) * pageSize);
      query.setMaxResults(pageSize);
      return query.getResultList();
   }

   @Override
   public List<Product1History> getHistory(Long id) {
      char lastChar = String.valueOf(id).charAt(String.valueOf(id).length() - 1);
      AutoTableNameInterceptor interceptor = new AutoTableNameInterceptor("TBL_PRODUCTS_HISTORY_0",String.valueOf(lastChar));
      Session session = sessionFactory.withOptions().interceptor(interceptor).openSession();

      CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
      CriteriaQuery<Product1History> criteriaQuery = criteriaBuilder.createQuery(Product1History.class);
      Root<Product1History> root = criteriaQuery.from(Product1History.class);
      criteriaQuery.select(root);
      criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

      @SuppressWarnings("unchecked")
      TypedQuery<Product1History> query = session.createQuery(criteriaQuery);
      List<Product1History> pList = query.getResultList();

      pList.stream().forEach(p -> {
         System.out.println("Test : "+p.getUpdateDt() + " " + p.getUpdateBy());
      });

      return pList;

   }

}
