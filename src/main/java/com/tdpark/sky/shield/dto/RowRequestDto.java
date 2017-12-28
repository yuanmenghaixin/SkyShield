package com.tdpark.sky.shield.dto;

import com.tdpark.sky.shield.annotation.NotNull;

public class RowRequestDto {
    
    @NotNull(desc="数据ID不能为空")
    private Long id;
    @NotNull(desc="数据行号不能为空")
    private Long rowNo;
    @NotNull(desc="列编号不能为空")
    private String columnKey;
    @NotNull(desc="数据不能为空")
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
