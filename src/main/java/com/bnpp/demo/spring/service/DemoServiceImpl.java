package com.bnpp.demo.spring.service;

import com.bnpp.demo.spring.dao.DemoDao;
import com.bnpp.demo.spring.model.demo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = false, value="transactionManager2")
public class DemoServiceImpl implements DemoService {

   @Autowired
   private DemoDao demoDao;

   @Override
   public Product getProductById(Long id) {
      return demoDao.getProductById(id);
   }

   @Override
   public void saveProduct(Product product) {
      demoDao.saveProduct(product);
   }

   @Override
   public void updateProduct(Product product) {
      Product oldProduct = demoDao.getProductById(product.getId());

      String excludeColumns = "lastupdDt,createDt,lastupdBy,createDt,createBy";

      ReflectionUtils.doWithFields(product.getClass(), field -> {
         System.out.println("Field name: " + field.getName());
         field.setAccessible(true);
         System.out.println("Field value: "+ field.get(oldProduct));
         if(excludeColumns.indexOf(field.getName()) <= -1 && !field.get(oldProduct).equals(field.get(product))){
            AuditLog auditLog = new AuditLog();
            auditLog.setTableName("Product");
            auditLog.setColumnName(field.getName());
            auditLog.setRowId(product.getId());
            auditLog.setLastupdDt(new Date());
            auditLog.setChangeBy(product.getLastupdBy());
            auditLog.setOldValue(field.get(oldProduct)==null? "":field.get(oldProduct).toString());
            auditLog.setNewValue(field.get(product)==null? "":field.get(product).toString());
            demoDao.saveAuditLog(auditLog);
         }

      });

      demoDao.updateProduct(product);
   }

   @Override
   public void deleteProduct(Product product) {
      demoDao.deleteProduct(product);
   }

   @Override
   public List<Product> listProducts(int pageNumber) {
      return demoDao.listProducts(pageNumber);
   }

   @Override
   public List<ProductHistory> getProductHistory(Long id) {
      return demoDao.getProductHistory(id);
   }

   @Override
   public void saveAll(List<Product> list) {
      demoDao.saveAll(list);
   }

   @Override
   public List<Category> listCategories() {
      return demoDao.listCategories();
   }

   @Override
   public List<Customer> listCustomers() {
      return demoDao.listCustomers();
   }

   @Override
   public List<Supplier> listSuppliers() {
      return demoDao.listSuppliers();
   }

   @Override
   public List<Employee> listEmployees() {
      return demoDao.listEmployees();
   }

   @Override
   public Order getOrderById(Long id) {
      return demoDao.getOrderById(id);
   }

   @Override
   public void saveOrder(Order order) {
      demoDao.saveOrder(order);
   }

   @Override
   public void updateOrder(Order order) {
      demoDao.updateOrder(order);
   }

   @Override
   public void delete(Order order) {
      demoDao.delete(order);
   }

   @Override
   public List<Order> listOrder(int pageNumber) {
      return demoDao.listOrder(pageNumber);
   }

   @Override
   public List<AuditLog> listAuditLog(Long rowId,String tableName) {
      return demoDao.listAuditLog(rowId,tableName);
   }
}
