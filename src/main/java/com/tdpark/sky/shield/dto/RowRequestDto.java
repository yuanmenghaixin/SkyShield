package com.tdpark.sky.shield.dto;

import com.tdpark.sky.shield.annotation.CheckParams;

public class RowRequestDto {
    
    @CheckParams(name="数据ID",notNull=true)
    private Long id;
    @CheckParams(name="表格行编号",notNull=true)
    private Long rowNo;
    @CheckParams(name="表格列编号",notNull=true,minLength=32,maxLength=32)
    private String columnKey;
    @CheckParams(name="数据",notNull=true)
    private String columnValue;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getRowNo() {
        return rowNo;
    }
    public void setRowNo(Long rowNo) {
        this.rowNo = rowNo;
    }
    public String getColumnKey() {
        return columnKey;
    }
    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }
    public String getColumnValue() {
        return columnValue;
    }
    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }
}
