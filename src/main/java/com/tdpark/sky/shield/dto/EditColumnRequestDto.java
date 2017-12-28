package com.tdpark.sky.shield.dto;

import java.util.List;

import com.tdpark.sky.shield.annotation.NotNull;

public class EditColumnRequestDto {

    @NotNull(desc="表格编号不能为空")
    private String tableNo;
    @NotNull(desc="表格列信息不能为空")
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
