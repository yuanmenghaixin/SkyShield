package com.tdpark.sky.shield.dto;

import com.tdpark.sky.shield.annotation.NotNull;

public class LoginRequestDto {

    @NotNull(desc = "用户名不能为空")
    private String userName;
    @NotNull(desc = "密码不能为空")
    private String passWord;
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
}
