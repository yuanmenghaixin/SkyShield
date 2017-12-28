package com.tdpark.sky.shield.domain;
import java.util.Date;
public class TableRow{
    /** 主键 **/
    private Long id;
    /** 用户令牌,与业务关联 **/
    private String ssToken;
    /** 表格编号UUID **/
    private String tableNo;
    /** 行号 **/
    private Long rowNo;
    /** 列属性名字母开头+UUID **/
    private String columnKey;
    /** 单元格值 **/
    private String columnValue;
    /** 创建时间 **/
    private Date gmtCreated;
    /** 修改时间 **/
    private Date gmtModified;
    /** 主键 **/
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    /** 用户令牌,与业务关联 **/
    public String getSsToken(){
        return this.ssToken;
    }
    public void setSsToken(String ssToken){
        this.ssToken = ssToken;
    }
    /** 表格编号UUID **/
    public String getTableNo(){
        return this.tableNo;
    }
    public void setTableNo(String tableNo){
        this.tableNo = tableNo;
    }
    /** 行号 **/
    public Long getRowNo(){
        return this.rowNo;
    }
    public void setRowNo(Long rowNo){
        this.rowNo = rowNo;
    }
    /** 列属性名字母开头+UUID **/
    public String getColumnKey(){
        return this.columnKey;
    }
    public void setColumnKey(String columnKey){
        this.columnKey = columnKey;
    }
    /** 单元格值 **/
    public String getColumnValue(){
        return this.columnValue;
    }
    public void setColumnValue(String columnValue){
        this.columnValue = columnValue;
    }
    /** 创建时间 **/
    public Date getGmtCreated(){
        return this.gmtCreated;
    }
    public void setGmtCreated(Date gmtCreated){
        this.gmtCreated = gmtCreated;
    }
    /** 修改时间 **/
    public Date getGmtModified(){
        return this.gmtModified;
    }
    public void setGmtModified(Date gmtModified){
        this.gmtModified = gmtModified;
    }
}