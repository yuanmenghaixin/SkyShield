package com.tdpark.sky.shield.utils;

import java.lang.reflect.Field;

import com.tdpark.eutils.StringUtils;
import com.tdpark.sky.shield.annotation.NotNull;
import com.tdpark.sky.shield.dto.RegisterRequestDto;
import com.tdpark.sky.shield.enums.ErrorEnum;
import com.tdpark.sky.shield.exception.SSException;

public class ParamUtils {

    public static void checkBlank(Object obj) throws IllegalArgumentException, IllegalAccessException{
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field : fields){
            NotNull notNull = field.getAnnotation(NotNull.class);
            if(notNull == null){
                continue;
            }
            field.setAccessible(true);
            Object object = field.get(obj);
            if(object == null){
                throw new SSException(ErrorEnum.FIELD_BE_NULL.getCode(),notNull.desc());
            }
            if(object instanceof String && StringUtils.isBlank(object.toString())){
                throw new SSException(ErrorEnum.FIELD_BE_NULL.getCode(),notNull.desc());
            }
        }
    }
    
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        RegisterRequestDto registerRequestDto = new RegisterRequestDto();
        registerRequestDto.setPublicKey("asd");
        registerRequestDto.setPublicKey("asd");
        checkBlank(registerRequestDto);
    }
}
