package com.tdpark.sky.shield.dao;

import com.tdpark.sky.shield.domain.UserInfo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserInfoDao {
    @Insert("insert into `user_info` (`ss_token`,`phone_no`,`public_key`,`private_key`,`gmt_created`,`gmt_modified`) value (#{ssToken},#{phoneNo},#{publicKey},#{privateKey},now(),now())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(UserInfo userInfo);

    @Select("select * from `user_info` where ss_token = #{ssToken}")
    @Results({ 
        @Result(property = "id", column = "id"),
        @Result(property = "ssToken", column = "ss_token"),
        @Result(property = "phoneNo", column = "phone_no"),
        @Result(property = "publicKey", column = "public_key"),
        @Result(property = "privateKey", column = "privateKey"),
        @Result(property = "gmtCreated", column = "gmt_created"),
        @Result(property = "gmtModified", column = "gmt_modified")
    })
    UserInfo selectBySsToken(@Param("ssToken") String ssToken);
}
