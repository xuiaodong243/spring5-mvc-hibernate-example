package com.bnpp.demo.spring.model.demo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CATEGORIES")
public class Category extends BaseModel implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "category_id",updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String desc;

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
    
}