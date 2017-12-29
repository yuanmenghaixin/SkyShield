package com.tdpark.sky.shield.dto;

import com.tdpark.sky.shield.annotation.CheckParams;

public class LoginRequestDto {

    @CheckParams(name="用户名",notNull=true)
    private String userName;
    @CheckParams(name="密码",notNull=true)
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
