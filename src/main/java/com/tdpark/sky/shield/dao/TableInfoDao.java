package com.tdpark.sky.shield.dao;
import com.tdpark.sky.shield.domain.TableInfo;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
public interface TableInfoDao{
    @Insert("insert into `table_info` (`ss_token`,`table_no`,`table_name`,`table_desc`,`gmt_created`,`gmt_modified`) value (#{ssToken},#{tableNo},#{tableName},#{tableDesc},now(),now())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(TableInfo tableInfo);
    
    @Select("select * from `table_info` where ss_token = #{ssToken}")
    @Results({ 
        @Result(property = "id", column = "id"),
        @Result(property = "ssToken", column = "ss_token"),
        @Result(property = "tableNo", column = "table_no"),
        @Result(property = "tableName", column = "table_name"),
        @Result(property = "tableDesc", column = "table_desc"),
        @Result(property = "gmtCreated", column = "gmt_created"),
        @Result(property = "gmtModified", column = "gmt_modified")
    })
    List<TableInfo> selectBySsToken(@Param("ssToken")String ssToken);
    
    @Select("select * from `table_info` where ss_token = #{ssToken} and table_no = #{tableNo}")
    @Results({ 
        @Result(property = "id", column = "id"),
        @Result(property = "ssToken", column = "ss_token"),
        @Result(property = "tableNo", column = "table_no"),
        @Result(property = "tableName", column = "table_name"),
        @Result(property = "tableDesc", column = "table_desc"),
        @Result(property = "gmtCreated", column = "gmt_created"),
        @Result(property = "gmtModified", column = "gmt_modified")
    })
    TableInfo selectByTableNo(@Param("ssToken")String ssToken,@Param("tableNo")String tableNo);
    
    @Delete("delete from `table_info` where ss_token = #{ssToken} and table_no = #{tableNo}")
    int delete(@Param("ssToken")String ssToken,@Param("tableNo")String tableNo);
}