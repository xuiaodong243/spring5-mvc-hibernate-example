package com.bnpp.demo.spring.service;

import com.bnpp.demo.spring.model.demo.*;

import java.util.List;

public interface DemoService {

   Product getProductById(Long id);
   void saveProduct(Product product);
   void updateProduct(Product product);
   void deleteProduct(Product product);
   List<Product> listProducts(int pageNumber);
   List<ProductHistory> getProductHistory(Long id);
   void saveAll(List<Product> list);

   List<Category> listCategories();
   List<Customer> listCustomers();
   List<Supplier> listSuppliers();
   List<Employee> listEmployees();

   Order getOrderById(Long id);
   void saveOrder(Order order);
   void updateOrder(Order order);
   void delete(Order order);
   List<Order> listOrder(int pageNumber);

   List<AuditLog> listAuditLog(Long rowId,String tableName);

}
