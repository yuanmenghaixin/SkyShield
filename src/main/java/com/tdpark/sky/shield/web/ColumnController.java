package com.tdpark.sky.shield.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdpark.sky.shield.annotation.Auth;
import com.tdpark.sky.shield.dao.TableColumnDao;
import com.tdpark.sky.shield.dao.TableInfoDao;
import com.tdpark.sky.shield.dao.TableRowDao;
import com.tdpark.sky.shield.domain.TableColumn;
import com.tdpark.sky.shield.domain.TableInfo;
import com.tdpark.sky.shield.dto.BaseResponseDto;
import com.tdpark.sky.shield.dto.ColumnRequestDto;
import com.tdpark.sky.shield.dto.ColumnResponseDto;
import com.tdpark.sky.shield.dto.EditColumnRequestDto;
import com.tdpark.sky.shield.dto.RemoveColumnRequestDto;
import com.tdpark.sky.shield.dto.TableDetailResponseDto;
import com.tdpark.sky.shield.enums.ErrorEnum;
import com.tdpark.sky.shield.utils.HttpCookiesUtils;
import com.tdpark.sky.shield.utils.HttpRequestUtils;
import com.tdpark.sky.shield.utils.ParamUtils;

@RestController  
@EnableAutoConfiguration 
@RequestMapping("column")
public class ColumnController {
    
    @Resource
    private TableInfoDao tableInfoDao;
    @Resource
    private TableColumnDao tableColumnDao;
    @Resource
    private TableRowDao tableRowDao;
    
    @ResponseBody
    @RequestMapping("edit")
    @Auth
    public Object edit(String params,HttpServletRequest request,HttpServletResponse response) throws IllegalArgumentException, IllegalAccessException, NoSuchAlgorithmException, JsonParseException, JsonMappingException, IOException{
        EditColumnRequestDto dto = new ObjectMapper().readValue(params, EditColumnRequestDto.class);
        ParamUtils.checkParams(dto);
        String ssToken = HttpRequestUtils.ssToken(request);
        HttpCookiesUtils.resetAuthCookies(request, response);
        TableInfo tableInfo = tableInfoDao.selectByTableNo(ssToken, dto.getTableNo());
        if(tableInfo == null){
            return new BaseResponseDto<Void>(ErrorEnum.ILL_REQUEST);
        }
        List<TableColumn> columns = tableColumnDao.selectByTableNo(ssToken, dto.getTableNo());
        Map<String, String> exist = new HashMap<String, String>();
        for(TableColumn tc : columns){
            exist.put(tc.getColumnKey(), tc.getColumnName());
        }
        List<TableColumn> modify = new ArrayList<TableColumn>();
        List<TableColumn> insert = new ArrayList<TableColumn>();
        List<ColumnRequestDto> list = dto.getColumns();
        for(ColumnRequestDto d : list){
            ParamUtils.checkParams(d);
            if(!exist.containsKey(d.getColumnKey())){
                TableColumn c = new TableColumn();
                c.setSsToken(ssToken);
                c.setTableNo(dto.getTableNo());
                c.setColumnKey(d.getColumnKey());
                c.setColumnName(d.getColumnName());
                c.setSecrets(d.getSecrets());
                insert.add(c);
            }else if(!exist.get(d.getColumnKey()).equals(d.getColumnName())){
                TableColumn c = new TableColumn();
                c.setSsToken(ssToken);
                c.setTableNo(dto.getTableNo());
                c.setColumnKey(d.getColumnKey());
                c.setColumnName(d.getColumnName());
                c.setSecrets(d.getSecrets());
                modify.add(c);
            }
        }
        for(TableColumn tc : insert){
            tableColumnDao.insert(tc);
        }
        for(TableColumn tc : modify){
            tableColumnDao.updateName(tc);
        }
        return new BaseResponseDto<ColumnResponseDto>();
    }
    @ResponseBody
    @RequestMapping("remove")
    @Auth
    public Object remove(RemoveColumnRequestDto dto,HttpServletRequest request,HttpServletResponse response) throws IllegalArgumentException, IllegalAccessException, NoSuchAlgorithmException{
        ParamUtils.checkParams(dto);
        String ssToken = HttpRequestUtils.ssToken(request);
        HttpCookiesUtils.resetAuthCookies(request, response);
        tableColumnDao.deleteByColumnKey(ssToken, dto.getTableNo(), dto.getColumnKey());
        tableRowDao.deleteByColumnKey(ssToken, dto.getTableNo(), dto.getColumnKey());
        return new BaseResponseDto<ColumnResponseDto>();
    }
    @ResponseBody
    @RequestMapping("list")
    @Auth
    public Object list(String tableNo,HttpServletRequest request,HttpServletResponse response) throws IllegalArgumentException, IllegalAccessException, NoSuchAlgorithmException{
        String ssToken = HttpRequestUtils.ssToken(request);
        HttpCookiesUtils.resetAuthCookies(request, response);
        TableInfo tableInfo = tableInfoDao.selectByTableNo(ssToken, tableNo);
        TableDetailResponseDto tableDetailResponseDto = null;
        if(tableInfo != null){
            tableDetailResponseDto = new TableDetailResponseDto();
            tableDetailResponseDto.setTableNo(tableInfo.getTableNo());
            tableDetailResponseDto.setTableName(tableInfo.getTableName());
        }else{
            return new BaseResponseDto<Void>();
        }
        List<TableColumn> list = tableColumnDao.selectByTableNo(ssToken,tableNo);
        if(list != null && !list.isEmpty()){
            List<ColumnResponseDto> result = new ArrayList<ColumnResponseDto>();
            for(TableColumn tableColumn : list){
                ColumnResponseDto columnResponseDto = new ColumnResponseDto();
                columnResponseDto.setColumnKey(tableColumn.getColumnKey());
                columnResponseDto.setColumnName(tableColumn.getColumnName());
                columnResponseDto.setSecrets(tableColumn.getSecrets());
                result.add(columnResponseDto);
            }
            tableDetailResponseDto.setColumns(result);
        }
        return new BaseResponseDto<TableDetailResponseDto>(tableDetailResponseDto);
    }
}
