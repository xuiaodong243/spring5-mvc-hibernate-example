package com.bnpp.demo.spring.dao;

import com.bnpp.demo.spring.model.demo.*;

import java.util.List;

public interface DemoDao {

    Product getProductById(Long id);
    void saveProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Product product);
    List<Product> listProducts(int pageNumber);
    List<ProductHistory> getProductHistory(Long id);

    List<Category> listCategories();
    List<Customer> listCustomers();
    List<Supplier> listSuppliers();
    List<Employee> listEmployees();

    Order getOrderById(Long id);
    void saveOrder(Order order);
    void updateOrder(Order order);
    void delete(Order order);
    List<Order> listOrder(int pageNumber);

    void saveAuditLog(AuditLog auditlog);
    List<AuditLog> listAuditLog(Long rowId,String tableName);

}
