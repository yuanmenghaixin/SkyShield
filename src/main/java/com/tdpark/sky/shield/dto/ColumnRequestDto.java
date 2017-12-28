package com.tdpark.sky.shield.dto;

import com.tdpark.sky.shield.annotation.NotNull;

public class ColumnRequestDto {

    @NotNull(desc="列编号不能为空")
    private String columnKey;
    @NotNull(desc="列名称不能为空")
    private String columnName;
    @NotNull(desc="是否加密不能为空")
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
