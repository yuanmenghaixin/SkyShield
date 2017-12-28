package com.tdpark.sky.shield.dao;

import com.tdpark.sky.shield.domain.UserAuth;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserAuthDao {

    @Insert("insert into `user_auth` (`ss_token`,`user_name`,`pass_word`,`gmt_created`,`gmt_modified`) value (#{ssToken},#{userName},#{passWord},now(),now())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(UserAuth userAuth);
    
    @Select("select * from `user_auth` where ss_token = #{ssToken}")
    @Results({ 
        @Result(property = "id", column = "id"),
        @Result(property = "ssToken", column = "ss_token"),
        @Result(property = "userName", column = "user_name"),
        @Result(property = "passWord", column = "pass_word"),
        @Result(property = "gmtCreated", column = "gmt_created"),
        @Result(property = "gmtModified", column = "gmt_modified")
    })
    UserAuth selectBySsToken(@Param("ssToken") String ssToken);
}
