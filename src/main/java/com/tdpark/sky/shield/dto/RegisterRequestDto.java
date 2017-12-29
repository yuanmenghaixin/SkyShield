package com.tdpark.sky.shield.dto;

import com.tdpark.sky.shield.annotation.CheckParams;

public class RegisterRequestDto {

    @CheckParams(name="混淆后的公钥",notNull=true,minLength=1,maxLength=512)
    private String publicKey;
    @CheckParams(name="混淆后的私钥",notNull=true,minLength=1,maxLength=1024)
    private String privateKey;
    @CheckParams(name="用户名",notNull=true,minLength=1,maxLength=32)
    private String userName;
    @CheckParams(name="密码",notNull=true,minLength=1,maxLength=32)
    private String passWord;
    @CheckParams(name="确认密码",notNull=true)
    private String comfWord;
    @CheckParams(name="验证码",notNull=true)
    private String randomCode;
    public String getPublicKey() {
        return publicKey;
    }
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
    public String getPrivateKey() {
        return privateKey;
    }
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
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
    public String getComfWord() {
        return comfWord;
    }
    public void setComfWord(String comfWord) {
        this.comfWord = comfWord;
    }
    public String getRandomCode() {
        return randomCode;
    }
    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }
    
}
