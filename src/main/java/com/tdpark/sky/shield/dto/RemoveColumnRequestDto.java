package com.tdpark.sky.shield.dto;

import com.tdpark.sky.shield.annotation.NotNull;

public class RemoveColumnRequestDto {

    @NotNull(desc="表格编号不能为空")
    private String tableNo;
    @NotNull(desc="表格列编号不能为空")
    private String columnKey;
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
}
