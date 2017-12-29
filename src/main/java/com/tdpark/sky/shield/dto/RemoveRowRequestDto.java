package com.tdpark.sky.shield.dto;

import com.tdpark.sky.shield.annotation.CheckParams;

public class RemoveRowRequestDto {
    @CheckParams(name="表格编号",notNull=true,minLength=32,maxLength=32)
    private String tableNo;
    @CheckParams(name="表格行编号",notNull=true)
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
