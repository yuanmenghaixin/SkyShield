package com.tdpark.sky.shield.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tdpark.sky.shield.domain.User;

public interface UserDao {

    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({ @Result(property = "name", column = "name"),
        @Result(property = "age", column = "age") ,
        @Result(property = "lastLoginTime", column = "last_login_time") })
    User selectById(Long id);

    @Update("update user set name = #{name} where id = #{id}")
    int updateName(@Param("name")String name,@Param("id")Long id);
    
    @Insert("insert into user (name,age,last_login_time) value (#{name},#{age},NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") 
    int insert(User user);
}
