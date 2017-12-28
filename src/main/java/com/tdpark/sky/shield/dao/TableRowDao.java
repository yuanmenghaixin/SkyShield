package com.tdpark.sky.shield.dao;
import com.tdpark.sky.shield.domain.TableRow;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
public interface TableRowDao{
    @Insert("insert into `table_row` (`ss_token`,`table_no`,`row_no`,`column_key`,`column_value`,`gmt_created`,`gmt_modified`) value (#{ssToken},#{tableNo},#{rowNo},#{columnKey},#{columnValue},now(),now())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(TableRow tableRow);
    
    @Update("update `table_row` set `gmt_modified` = now(),`column_value` = #{columnValue} where ss_token = #{ssToken} and table_no = #{tableNo} and column_key = #{columnKey} and id = #{id}")
    int update(TableRow tableRow);
    
    @Select("select * from `table_row` where ss_token = #{ssToken} and table_no = #{tableNo}")
    @Results({ 
        @Result(property = "id", column = "id"),
        @Result(property = "ssToken", column = "ss_token"),
        @Result(property = "tableNo", column = "table_no"),
        @Result(property = "rowNo", column = "row_no"),
        @Result(property = "columnKey", column = "column_key"),
        @Result(property = "columnValue", column = "column_value"),
        @Result(property = "gmtCreated", column = "gmt_created"),
        @Result(property = "gmtModified", column = "gmt_modified")
    })
    List<TableRow> selectByTableNo(@Param("ssToken")String ssToken,@Param("tableNo")String tableNo);
    
    @Select("select * from `table_row` where ss_token = #{ssToken} and table_no = #{tableNo} and row_no = #{rowNo}")
    @Results({ 
        @Result(property = "id", column = "id"),
        @Result(property = "ssToken", column = "ss_token"),
        @Result(property = "tableNo", column = "table_no"),
        @Result(property = "rowNo", column = "row_no"),
        @Result(property = "columnKey", column = "column_key"),
        @Result(property = "columnValue", column = "column_value"),
        @Result(property = "gmtCreated", column = "gmt_created"),
        @Result(property = "gmtModified", column = "gmt_modified")
    })
    List<TableRow> selectByRowNo(@Param("ssToken")String ssToken,@Param("tableNo")String tableNo,@Param("rowNo")long rowNo);
    
    @Delete("delete from `table_row` where ss_token = #{ssToken} and table_no = #{tableNo}")
    int delete(@Param("ssToken")String ssToken,@Param("tableNo")String tableNo);
    
    @Delete("delete from `table_row` where ss_token = #{ssToken} and table_no = #{tableNo} and column_key = #{columnKey}")
    int deleteByColumnKey(@Param("ssToken")String ssToken,@Param("tableNo")String tableNo,@Param("columnKey")String columnKey);
    
    @Delete("delete from `table_row` where ss_token = #{ssToken} and table_no = #{tableNo} and row_no = #{rowNo}")
    int deleteByRowNo(@Param("ssToken")String ssToken,@Param("tableNo")String tableNo,@Param("rowNo")Long rowNo);
}