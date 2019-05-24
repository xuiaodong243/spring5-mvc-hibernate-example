package com.bnpp.demo.spring.model.demo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "audit_log")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "al_generator")
    @SequenceGenerator(name="al_generator", sequenceName = "audit_log_seq", initialValue = 100, allocationSize = 1)
    @Column(name = "audit_id",updatable = false, nullable = false)
    private Long auditId;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "column_name")
    private String columnName;

    @Column(name = "row_id")
    private Long rowId;

    @Column(name = "change_by")
    private String changeBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastupd_dt")
    private Date lastupdDt;

    @Column(name = "old_value")
    private String oldValue;

    @Column(name = "new_value")
    private String newValue;


    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public String getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(String changeBy) {
        this.changeBy = changeBy;
    }

    public Date getLastupdDt() {
        return lastupdDt;
    }

    public void setLastupdDt(Date lastupdDt) {
        this.lastupdDt = lastupdDt;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}
