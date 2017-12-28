package com.tdpark.sky.shield.dto;

import java.io.Serializable;

/**
 * 
 * @Description: 用户鉴权需要的基础参数
 * @author Evan Jiang
 * @date 2017年12月6日 上午11:27:20 
 *
 */
public class AuthRequestDto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** 时间戳 **/
    private long timestamp;
    /** 签名 **/
    private String sign;
    /** 用户token **/
    private String ssToken;
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getSsToken() {
        return ssToken;
    }
    public void setSsToken(String ssToken) {
        this.ssToken = ssToken;
    }

}
