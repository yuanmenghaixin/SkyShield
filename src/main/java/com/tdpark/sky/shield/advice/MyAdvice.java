package com.tdpark.sky.shield.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdpark.sky.shield.dto.BaseResponseDto;
import com.tdpark.sky.shield.enums.ErrorEnum;
import com.tdpark.sky.shield.exception.SSException;

@ControllerAdvice
public class MyAdvice {
    private Logger logger = LoggerFactory.getLogger(MyAdvice.class); 
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object sysExceptionHandler(Exception ex) {
        logger.error("系统异常:",ex);
        BaseResponseDto<Void> baseResponseDto = new BaseResponseDto<Void>(ErrorEnum.SYS_EXCEPTION);
        return baseResponseDto;
    }
    
    @ResponseBody
    @ExceptionHandler(value = SSException.class)
    public Object ssExceptionHandler(SSException ex) {
        logger.error("业务异常:",ex);
        BaseResponseDto<Void> baseResponseDto = new BaseResponseDto<Void>(ex.getCode(),ex.getMsg());
        return baseResponseDto;
    }
}
