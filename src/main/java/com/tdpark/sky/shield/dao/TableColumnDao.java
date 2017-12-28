package com.tdpark.sky.shield.dao;
import com.tdpark.sky.shield.domain.TableColumn;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
public interface TableColumnDao{
    @Insert("insert into `table_column` (`ss_token`,`table_no`,`column_key`,`column_name`,`secrets`,`gmt_created`,`gmt_modified`) value (#{ssToken},#{tableNo},#{columnKey},#{columnName},#{secrets},now(),now())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(TableColumn tableColumn);
    
    @Update("update `table_column` set column_name = #{columnName} where ss_token = #{ssToken} and table_no = #{tableNo} and column_key = #{columnKey}")
    int updateName(TableColumn tableColumn);
    
    @Select("select * from `table_column` where ss_token = #{ssToken} and table_no = #{tableNo}")
    @Results({ 
        @Result(property = "id", column = "id"),
        @Result(property = "ssToken", column = "ss_token"),
        @Result(property = "tableNo", column = "table_no"),
        @Result(property = "columnKey", column = "column_key"),
        @Result(property = "columnName", column = "column_name"),
        @Result(property = "secrets", column = "secrets"),
        @Result(property = "gmtCreated", column = "gmt_created"),
        @Result(property = "gmtModified", column = "gmt_modified")
    })
    List<TableColumn> selectByTableNo(@Param("ssToken")String ssToken,@Param("tableNo")String tableNo);
    
    @Delete("delete from `table_column` where ss_token = #{ssToken} and table_no = #{tableNo}")
    int delete(@Param("ssToken")String ssToken,@Param("tableNo")String tableNo);
    
    @Delete("delete from `table_column` where ss_token = #{ssToken} and table_no = #{tableNo} and column_key = #{columnKey}")
    int deleteByColumnKey(@Param("ssToken")String ssToken,@Param("tableNo")String tableNo,@Param("columnKey")String columnKey);
}