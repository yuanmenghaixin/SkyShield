package com.tdpark.sky.shield.test;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tdpark.sky.shield.dto.LoginRequestDto;
import com.tdpark.sky.shield.web.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserControllerTest {

    @Autowired
    private UserController userController;
    
    @Test
    public void login(){
        try {
            LoginRequestDto dto = new LoginRequestDto();
            Object login = userController.login(dto , null, null);
            System.out.println(JSONObject.valueToString(login));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
