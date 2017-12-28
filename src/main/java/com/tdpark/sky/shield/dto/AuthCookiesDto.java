package com.tdpark.sky.shield.dto;

public class AuthCookiesDto {

    /** 时间戳 **/
    private String timestamp;
    /** 签名 **/
    private String sign;
    /** 外部token **/
    private String token;
    /** 内部token **/
    private String ssToken;
    /** 随机码 **/
    private String randomCode;
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getSsToken() {
        return ssToken;
    }
    public void setSsToken(String ssToken) {
        this.ssToken = ssToken;
    }
    public String getRandomCode() {
        return randomCode;
    }
    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }
}
