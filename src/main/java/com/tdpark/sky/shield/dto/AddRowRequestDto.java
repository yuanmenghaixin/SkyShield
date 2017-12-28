package com.tdpark.sky.shield.dto;

import java.util.List;

import com.tdpark.sky.shield.annotation.NotNull;

public class AddRowRequestDto {
    @NotNull(desc = "表格编号不能为空")
    private String tableNo;
    @NotNull(desc = "表格行数据不能为空")
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
