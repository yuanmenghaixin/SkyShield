package com.tdpark.sky.shield.dto;

import com.tdpark.sky.shield.annotation.CheckParams;

public class ColumnRequestDto {

    @CheckParams(name="列编号",notNull=true,minLength=32,maxLength=32)
    private String columnKey;
    @CheckParams(name="列名称",notNull=true,maxLength=16)
    private String columnName;
    @CheckParams(name="是否加密",notNull=true,maxLength=1)
    private String secrets;
    public String getColumnKey() {
        return columnKey;
    }
    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }
    public String getColumnName() {
        return columnName;
    }
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    public String getSecrets() {
        return secrets;
    }
    public void setSecrets(String secrets) {
        this.secrets = secrets;
    }
    
    
}
