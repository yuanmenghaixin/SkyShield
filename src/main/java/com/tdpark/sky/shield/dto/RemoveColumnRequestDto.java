package com.tdpark.sky.shield.dto;

import com.tdpark.sky.shield.annotation.CheckParams;

public class RemoveColumnRequestDto {

    @CheckParams(name="表格编号",notNull=true,minLength=32,maxLength=32)
    private String tableNo;
    @CheckParams(name="表格列编号",notNull=true,minLength=32,maxLength=32)
    private String columnKey;
    public String getTableNo() {
        return tableNo;
    }
    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }
    public String getColumnKey() {
        return columnKey;
    }
    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }
}
