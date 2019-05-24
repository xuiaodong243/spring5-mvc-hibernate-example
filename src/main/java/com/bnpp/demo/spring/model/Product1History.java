package com.bnpp.demo.spring.model;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Embeddable
@Table(name = "TBL_PRODUCTS_HISTORY_0")
public class Product1History extends BaseProduct implements Serializable {

    @Id
    @Column(name = "H_ID")
    private Long hId;

    @Column(name = "PRO_ID", unique = false, nullable = false)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long gethId() {
        return hId;
    }

    public void sethId(Long hId) {
        this.hId = hId;
    }
}
