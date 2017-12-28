package com.tdpark.sky.shield.exception;

import com.tdpark.sky.shield.enums.ErrorEnum;

public class SSException extends RuntimeException{
    /**
     * 
     */
    private static final long serialVersionUID = -1448077775331117069L;
    private String code;
    private String msg;
    
    public SSException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    public SSException(ErrorEnum errorEnum) {
        super(errorEnum.getDesc());
        this.code = errorEnum.getCode();
        this.msg = errorEnum.getDesc();
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
