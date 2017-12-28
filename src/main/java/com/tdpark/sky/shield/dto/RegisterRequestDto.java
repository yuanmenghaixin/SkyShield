package com.tdpark.sky.shield.dto;

import com.tdpark.sky.shield.annotation.NotNull;

public class RegisterRequestDto {

    @NotNull(desc="混淆后的公钥不能为空")
    private String publicKey;
    @NotNull(desc="混淆后的私钥不能为空")
    private String privateKey;
    @NotNull(desc="用户名不能为空")
    private String userName;
    @NotNull(desc="密码不能为空")
    private String passWord;
    @NotNull(desc="确认密码不能为空")
    private String comfWord;
    @NotNull(desc="验证码不能为空")
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
