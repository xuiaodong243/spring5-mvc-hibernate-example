package com.bnpp.demo.spring.model.demo;

import com.bnpp.demo.spring.model.BaseProduct;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCTS")
public class Product extends BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pro_generator")
    @SequenceGenerator(name="pro_generator", sequenceName = "demo_product_seq", initialValue = 100, allocationSize = 1)
    @Column(name = "product_id",updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String desc;

    @Column(name = "unit_price")
    private long unitPrice;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "category_id")
    private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}