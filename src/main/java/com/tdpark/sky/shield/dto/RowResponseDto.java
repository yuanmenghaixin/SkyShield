package com.tdpark.sky.shield.dto;

public class RowResponseDto {

    private Long id;
    private String columnKey;
    private String columnName;
    private String columnValue;
    private String secrets;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    public String getColumnValue() {
        return columnValue;
    }
    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }
    public String getSecrets() {
        return secrets;
    }
    public void setSecrets(String secrets) {
        this.secrets = secrets;
    }
    
}
