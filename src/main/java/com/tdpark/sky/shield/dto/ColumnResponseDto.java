package com.tdpark.sky.shield.dto;


public class ColumnResponseDto {

    private Long id;
    private String tableNo;
    private String columnKey;
    private String columnName;
    private String secrets;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
