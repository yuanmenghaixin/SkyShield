package com.tdpark.sky.shield.dto;

public class TableResponseDto {

    private Long id;
    private String tableNo;
    private String tableName;
    private String tableDesc;
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
