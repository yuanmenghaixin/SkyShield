package com.tdpark.sky.shield.domain;
import java.util.Date;
public class UserAuth{
    /** 主键 **/
    private Long id;
    /** 用户令牌,与业务关联 **/
    private String ssToken;
    /** 用户名 **/
    private String userName;
    /** 密码 **/
    private String passWord;
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
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
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