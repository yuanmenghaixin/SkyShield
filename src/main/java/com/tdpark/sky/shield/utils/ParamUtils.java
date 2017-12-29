package com.tdpark.sky.shield.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.tdpark.eutils.StringUtils;
import com.tdpark.sky.shield.annotation.CheckParams;
import com.tdpark.sky.shield.enums.ErrorEnum;
import com.tdpark.sky.shield.exception.SSException;

public class ParamUtils {

    public static void checkParams(Object obj) throws IllegalArgumentException,
            IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            CheckParams checkParams = field.getAnnotation(CheckParams.class);
            if (checkParams == null) {
                continue;
            }
            field.setAccessible(true);
            Object object = field.get(obj);
            if (checkParams.notNull()) {
                if (object == null) {
                    throw new SSException(ErrorEnum.FIELD_BE_NULL.getCode(),
                            String.format(ErrorEnum.FIELD_BE_NULL.getDesc(),
                                    checkParams.name()));
                }
                if (object instanceof String
                        && StringUtils.isBlank(object.toString())) {
                    throw new SSException(ErrorEnum.FIELD_BE_NULL.getCode(),
                            String.format(ErrorEnum.FIELD_BE_NULL.getDesc(),
                                    checkParams.name()));
                }
            }
            if (object instanceof String) {
                int value = object.toString().length();
                if (value < checkParams.minLength()) {
                    throw new SSException(ErrorEnum.LENGTH_MIN_ILL.getCode(),
                            String.format(ErrorEnum.LENGTH_MIN_ILL.getDesc(),
                                    checkParams.name(),checkParams.minLength()));
                }
                if (value > checkParams.maxLength()) {
                    throw new SSException(ErrorEnum.LENGTH_MAX_ILL.getCode(),
                            String.format(ErrorEnum.LENGTH_MAX_ILL.getDesc(),
                                    checkParams.name(),checkParams.maxLength()));
                }
            }
            if (object instanceof Integer || object instanceof Long
                    || object instanceof Double || object instanceof BigDecimal
                    || object instanceof Float) {
                BigDecimal value = null;
                if(object instanceof BigDecimal){
                    value = (BigDecimal)object;
                }else{
                    value = new BigDecimal(object.toString());
                }
                if(value.compareTo(new BigDecimal(String.valueOf(checkParams.min()))) < 0){
                    throw new SSException(ErrorEnum.VALUE_MIN_ILL.getCode(),
                            String.format(ErrorEnum.VALUE_MIN_ILL.getDesc(),
                                    checkParams.name(),checkParams.min()));
                }
                if(value.compareTo(new BigDecimal(String.valueOf(checkParams.max()))) > 0){
                    throw new SSException(ErrorEnum.VALUE_MAX_ILL.getCode(),
                            String.format(ErrorEnum.VALUE_MAX_ILL.getDesc(),
                                    checkParams.name(),checkParams.max()));
                }
            }
        }
    }


    public static class A{
        @CheckParams(name="姓名",notNull=true,minLength=0,maxLength=20)
        private String name;
        @CheckParams(name="年龄",notNull=true,min=0,max=120)
        private int age;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
    }
    public static void main(String[] args) throws IllegalArgumentException,
            IllegalAccessException {
        A a = new A();
        a.setName("jiangyongsheng");
        a.setAge(121);
        ParamUtils.checkParams(a);
    }
}
