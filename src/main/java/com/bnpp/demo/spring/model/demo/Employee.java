package com.bnpp.demo.spring.model.demo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EMPLOYEES")
public class Employee extends BaseModel implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "employee_id",updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

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

}