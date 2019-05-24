package com.bnpp.demo.spring.model.demo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@MappedSuperclass
public class BaseModel {

   @Column(name = "CREATE_BY",updatable= false)
   private String createBy;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "CREATE_DT", updatable= false)
   private Date createDt;

   @Column(name = "LASTUPD_BY")
   private String lastupdBy;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "LASTUPD_DT")
   private Date lastupdDt;

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

   public String getLastupdBy() {
      return lastupdBy;
   }

   public void setLastupdBy(String lastupdBy) {
      this.lastupdBy = lastupdBy;
   }

   public Date getLastupdDt() {
      return lastupdDt;
   }

   public void setLastupdDt(Date lastupdDt) {
      this.lastupdDt = lastupdDt;
   }
}
