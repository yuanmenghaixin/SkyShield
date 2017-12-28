package com.tdpark.sky.shield.utils;

import com.tdpark.sky.shield.enums.ErrorEnum;
import com.tdpark.sky.shield.exception.SSException;

public class SSExceptionUtils {

    public static void check(boolean c,String code,String msg){
        if(!c){
            throw new SSException(code, msg);
        }
    }
    public static void check(boolean c,ErrorEnum errorEnum){
        check(c, errorEnum.getCode(), errorEnum.getDesc());
    }
}
