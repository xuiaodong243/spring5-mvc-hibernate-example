package com.bnpp.demo.spring.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@MappedSuperclass
public class BaseProduct {

   @Id
   @GeneratedValue
   @Column(name = "PRO_ID")
   private Long id;

   @Column(name = "PRO_NAME")
   @Size(max = 100, min = 3, message = "{prod.name.invalid}")
   @NotEmpty(message="Please Enter prod name")
   private String name;

   @Column(name = "PRO_DESC")
   @NotEmpty(message="Please Enter prod desc")
   private String desc;

   @Column(name = "CREATE_BY",updatable= false)
   private String createBy;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "CREATE_DT", updatable= false)
   private Date createDt;

   @Column(name = "UPDATE_BY")
   private String updateBy;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "UPDATE_DT")
   private Date updateDt;


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

   public String getCreateBy() {
      return createBy;
   }

   public void setCreateBy(String createBy) {
      this.createBy = createBy;
   }

   public Date getCreateDt() {
      return createDt;
   }

   public void setCreateDt(Date createDt) {
      this.createDt = createDt;
   }

   public String getUpdateBy() {
      return updateBy;
   }

   public void setUpdateBy(String updateBy) {
      this.updateBy = updateBy;
   }

   public Date getUpdateDt() {
      return updateDt;
   }

   public void setUpdateDt(Date updateDt) {
      this.updateDt = updateDt;
   }
}
