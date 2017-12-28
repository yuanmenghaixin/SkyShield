package com.tdpark.sky.shield.domain;
import java.util.Date;
public class TableInfo{
    /** 主键 **/
    private Long id;
    /** 用户令牌,与业务关联 **/
    private String ssToken;
    /** 表格编号UUID **/
    private String tableNo;
    /** 表格名称 **/
    private String tableName;
    /** 表格描述 **/
    private String tableDesc;
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
    /** 表格名称 **/
    public String getTableName(){
        return this.tableName;
    }
    public void setTableName(String tableName){
        this.tableName = tableName;
    }
    /** 表格描述 **/
    public String getTableDesc(){
        return this.tableDesc;
    }
    public void setTableDesc(String tableDesc){
        this.tableDesc = tableDesc;
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