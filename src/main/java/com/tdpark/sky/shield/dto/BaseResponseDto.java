package com.tdpark.sky.shield.dto;

import com.tdpark.sky.shield.enums.ErrorEnum;

public class BaseResponseDto<T>{
    private String code = "00000";
    private String msg;
    private T data;

    public BaseResponseDto() {
    }
    public BaseResponseDto(T data) {
        this.data = data;
    }
    public BaseResponseDto(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public BaseResponseDto(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public BaseResponseDto(ErrorEnum errorEnum) {
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
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
