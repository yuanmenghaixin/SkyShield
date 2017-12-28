package com.tdpark.sky.shield.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdpark.sky.shield.annotation.Auth;
import com.tdpark.sky.shield.dao.UserDao;
import com.tdpark.sky.shield.domain.User;

@RestController  
@EnableAutoConfiguration 
@RequestMapping("usertest")
public class UserTestController {
    private Logger logger = LoggerFactory.getLogger(UserTestController.class);  
    @Resource
    private UserDao userDao;
    
    @RequestMapping("/{id}")  
    public Object queryUser(@PathVariable Long id) throws Exception {
        User user = userDao.selectById(id);
        if(user == null){
            throw new Exception("数据为空");
        }
        try {
            logger.info("queryUser,id:{},result:{}",id, new ObjectMapper().writeValueAsString(user));
        } catch (JsonProcessingException e) {
            logger.error("queryUser Exception",e);;
        }
        return user;
    }
    
    @RequestMapping("/modify") 
    @Auth
    public Object modify(Long id,String name) throws Exception {
        int modify = userDao.updateName(name, id);
        logger.info("更新命中数:{}",modify);
        return queryUser(id);
    }
    
    @RequestMapping("/add") 
    public Object add() throws Exception {
        User user = new User();
        user.setName("jys");
        user.setAge(29);
        int insert = userDao.insert(user);
        logger.info("插入数据返回值:{}",insert);
        return user;
    }
}
