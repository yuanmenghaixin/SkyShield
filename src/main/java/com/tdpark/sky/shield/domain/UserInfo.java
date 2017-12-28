package com.tdpark.sky.shield.domain;
import java.util.Date;
public class UserInfo{
    /** 主键 **/
    private Long id;
    /** 用户令牌,与业务关联 **/
    private String ssToken;
    /** 手机号码 **/
    private String phoneNo;
    /** 混淆后的公钥 **/
    private String publicKey;
    /** 混淆后的私钥 **/
    private String privateKey;
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
    /** 手机号码 **/
    public String getPhoneNo(){
        return this.phoneNo;
    }
    public void setPhoneNo(String phoneNo){
        this.phoneNo = phoneNo;
    }
    /** 混淆后的公钥 **/
    public String getPublicKey(){
        return this.publicKey;
    }
    public void setPublicKey(String publicKey){
        this.publicKey = publicKey;
    }
    /** 混淆后的私钥 **/
    public String getPrivateKey(){
        return this.privateKey;
    }
    public void setPrivateKey(String privateKey){
        this.privateKey = privateKey;
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