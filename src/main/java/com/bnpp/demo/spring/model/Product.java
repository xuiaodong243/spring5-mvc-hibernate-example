package com.bnpp.demo.spring.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;


@Entity
@Table(name = "TBL_PRODUCTS")
public class Product extends BaseProduct{
   @Id
   @GeneratedValue
   @Column(name = "PRO_ID")
   private Long id;

   @Override
   public Long getId() {
      return id;
   }

   @Override
   public void setId(Long id) {
      this.id = id;
   }
}
