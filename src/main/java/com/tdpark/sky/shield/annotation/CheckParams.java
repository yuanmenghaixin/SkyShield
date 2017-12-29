package com.tdpark.sky.shield.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface CheckParams {
    String name();
    boolean notNull() default false;
    int minLength() default 0;
    int maxLength() default Integer.MAX_VALUE;
    long min() default Long.MIN_VALUE;
    long max() default Long.MAX_VALUE;
    
}
