package com.tdpark.sky.shield.dto;

import java.util.List;

import com.tdpark.sky.shield.annotation.CheckParams;

public class EditColumnRequestDto {

    @CheckParams(name="表格编号",notNull=true,minLength=32,maxLength=32)
    private String tableNo;
    @CheckParams(name="表格列信息",notNull=true)
    private List<ColumnRequestDto> columns;
    public String getTableNo() {
        return tableNo;
    }
    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }
    public List<ColumnRequestDto> getColumns() {
        return columns;
    }
    public void setColumns(List<ColumnRequestDto> columns) {
        this.columns = columns;
    }
    
    
}
