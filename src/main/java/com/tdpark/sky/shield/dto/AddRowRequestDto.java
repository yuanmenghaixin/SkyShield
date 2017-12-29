package com.tdpark.sky.shield.dto;

import java.util.List;

import com.tdpark.sky.shield.annotation.CheckParams;

public class AddRowRequestDto {
    @CheckParams(name="表格编号",notNull=true,minLength=32,maxLength=32)
    private String tableNo;
    @CheckParams(name="表格行数据",notNull=true)
    private List<RowRequestDto> row;
    public String getTableNo() {
        return tableNo;
    }
    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }
    public List<RowRequestDto> getRow() {
        return row;
    }
    public void setRow(List<RowRequestDto> row) {
        this.row = row;
    }

}
