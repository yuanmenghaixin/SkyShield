package com.tdpark.sky.shield.dto;

import com.tdpark.sky.shield.annotation.CheckParams;

public class TableRequestDto {

    @CheckParams(name="表格名称",notNull=true,minLength=1,maxLength=32)
    private String tableName;
    @CheckParams(name="表格描述",notNull=true,minLength=1,maxLength=128)
    private String tableDesc;
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getTableDesc() {
        return tableDesc;
    }
    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }
    
}
