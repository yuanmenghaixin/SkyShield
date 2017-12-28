package com.tdpark.sky.shield.dto;

import com.tdpark.sky.shield.annotation.NotNull;

public class RemoveRowRequestDto {
    @NotNull(desc = "表格编号不能为空")
    private String tableNo;
    @NotNull(desc = "表格行编号不能为空")
    private long rowNo;
    public String getTableNo() {
        return tableNo;
    }
    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }
    public long getRowNo() {
        return rowNo;
    }
    public void setRowNo(long rowNo) {
        this.rowNo = rowNo;
    }
}
