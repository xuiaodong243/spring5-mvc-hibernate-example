package com.bnpp.demo.spring.dao;

import com.bnpp.demo.spring.model.Product1History;
import com.bnpp.demo.spring.model.demo.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DemoDaoImpl implements DemoDao {

   @Autowired
   @Qualifier("sessionFactory2")
   private SessionFactory sessionFactory;


   @Override
   public Product getProductById(Long id) {
      Session session = sessionFactory.openSession();
      Product p= session.get(Product.class, id);
      session.close();
      return p;

   }

   @Override
   public void saveProduct(Product product) {
      sessionFactory.getCurrentSession().save(product);
   }

   @Override
   public void updateProduct(Product product) {
      sessionFactory.getCurrentSession().update(product);
   }

   @Override
   public void deleteProduct(Product product) {
      sessionFactory.getCurrentSession().delete(product);
   }

   @Override
   public List<Product> listProducts(int pageNumber) {
      int pageSize = 10;
      @SuppressWarnings("unchecked")
      TypedQuery<Product> query = sessionFactory.getCurrentSession().createQuery("from Product f ORDER BY f.id desc");
      query.setFirstResult((pageNumber - 1) * pageSize);
      query.setMaxResults(pageSize);
      return query.getResultList();
   }

   @Override
   public List<ProductHistory> getProductHistory(Long id) {

      //TypedQuery<ProductHistory> query = sessionFactory.getCurrentSession().createQuery("from ProductHistory f ORDER BY f.hId desc");

      CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
      CriteriaQuery<ProductHistory> criteriaQuery = criteriaBuilder.createQuery(ProductHistory.class);
      Root<ProductHistory> root = criteriaQuery.from(ProductHistory.class);
      criteriaQuery.select(root);
      criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

      @SuppressWarnings("unchecked")
      TypedQuery<ProductHistory> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
      List<ProductHistory> pList = query.getResultList();
      System.out.println("history size: "+pList.size());
      return pList;
   }

   @Override
   public List<Category> listCategories() {
      TypedQuery<Category> query = sessionFactory.getCurrentSession().createQuery("from Category");
      return query.getResultList();
   }

   @Override
   public List<Customer> listCustomers() {
      TypedQuery<Customer> query = sessionFactory.getCurrentSession().createQuery("from Customer");
      return query.getResultList();
   }

   @Override
   public List<Supplier> listSuppliers() {
      TypedQuery<Supplier> query = sessionFactory.getCurrentSession().createQuery("from Supplier");
      return query.getResultList();
   }

   @Override
   public List<Employee> listEmployees() {
      TypedQuery<Employee> query = sessionFactory.getCurrentSession().createQuery("from Employee");
      return query.getResultList();
   }

   @Override
   public Order getOrderById(Long id) {
      return sessionFactory.getCurrentSession().get(Order.class, id);
   }

   @Override
   public void saveOrder(Order order) {
      sessionFactory.getCurrentSession().save(order);
   }

   @Override
   public void updateOrder(Order order) {
      sessionFactory.getCurrentSession().update(order);
   }

   @Override
   public void delete(Order order) {
      sessionFactory.getCurrentSession().delete(order);
   }

   @Override
   public List<Order> listOrder(int pageNumber) {
      int pageSize = 10;
      @SuppressWarnings("unchecked")
      TypedQuery<Order> query = sessionFactory.getCurrentSession().createQuery("from Order f ORDER BY f.id desc");
      query.setFirstResult((pageNumber - 1) * pageSize);
      query.setMaxResults(pageSize);
      return query.getResultList();
   }

   @Override
   public void saveAuditLog(AuditLog auditlog) {
      sessionFactory.getCurrentSession().save(auditlog);
   }

   @Override
   public List<AuditLog> listAuditLog(Long rowId,String tableName) {
      CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
      CriteriaQuery<AuditLog> criteriaQuery = criteriaBuilder.createQuery(AuditLog.class);
      Root<AuditLog> root = criteriaQuery.from(AuditLog.class);
      criteriaQuery.select(root);
      criteriaQuery.where(criteriaBuilder.equal(root.get("rowId"), rowId),criteriaBuilder.equal(root.get("tableName"), tableName));
      criteriaQuery.orderBy(criteriaBuilder.desc(root.get("auditId")));
      @SuppressWarnings("unchecked")
      TypedQuery<AuditLog> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
      List<AuditLog> pList = query.getResultList();
      System.out.println("audit log size: "+pList.size());
      return pList;
   }
}
