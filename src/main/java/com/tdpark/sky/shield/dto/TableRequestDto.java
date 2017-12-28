package com.tdpark.sky.shield.dto;

import com.tdpark.sky.shield.annotation.NotNull;

public class TableRequestDto {

    @NotNull(desc="表格名称不能为空")
    private String tableName;
    @NotNull(desc="表格描述不能为空")
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
