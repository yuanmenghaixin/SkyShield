package com.tdpark.sky.shield;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tdpark.sky.shield.dao")
public class Run {
    public static void main(String[] args) {  
        SpringApplication.run(Run.class, args);  
    }  
}
