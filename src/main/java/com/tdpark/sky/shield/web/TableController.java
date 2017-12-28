package com.tdpark.sky.shield.web;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tdpark.eutils.UUIDUtils;
import com.tdpark.sky.shield.annotation.Auth;
import com.tdpark.sky.shield.dao.TableColumnDao;
import com.tdpark.sky.shield.dao.TableInfoDao;
import com.tdpark.sky.shield.dao.TableRowDao;
import com.tdpark.sky.shield.domain.TableColumn;
import com.tdpark.sky.shield.domain.TableInfo;
import com.tdpark.sky.shield.domain.TableRow;
import com.tdpark.sky.shield.dto.BaseResponseDto;
import com.tdpark.sky.shield.dto.TableDetailResponseDto;
import com.tdpark.sky.shield.dto.TableRequestDto;
import com.tdpark.sky.shield.dto.TableResponseDto;
import com.tdpark.sky.shield.utils.HttpCookiesUtils;
import com.tdpark.sky.shield.utils.HttpRequestUtils;
import com.tdpark.sky.shield.utils.ParamUtils;

@RestController  
@EnableAutoConfiguration 
@RequestMapping("table")
public class TableController {
    
    @Resource
    private TableInfoDao tableInfoDao;
    @Resource
    private TableColumnDao tableColumnDao;
    @Resource
    private TableRowDao tableRowDao;
    
    @ResponseBody
    @RequestMapping("add")
    @Auth
    public Object add(TableRequestDto dto,HttpServletRequest request,HttpServletResponse response) throws IllegalArgumentException, IllegalAccessException, NoSuchAlgorithmException{
        ParamUtils.checkBlank(dto);
        String ssToken = HttpRequestUtils.ssToken(request);
        TableInfo tableInfo = new TableInfo();
        tableInfo.setSsToken(ssToken);
        tableInfo.setTableDesc(dto.getTableDesc());
        tableInfo.setTableName(dto.getTableName());
        tableInfo.setTableNo(UUIDUtils.uuid());
        tableInfoDao.insert(tableInfo);
        HttpCookiesUtils.resetAuthCookies(request, response);
        TableResponseDto tableResponseDto = new TableResponseDto();
        tableResponseDto.setId(tableInfo.getId());
        tableResponseDto.setTableDesc(tableInfo.getTableDesc());
        tableResponseDto.setTableName(tableInfo.getTableName());
        tableResponseDto.setTableNo(tableInfo.getTableNo());
        return new BaseResponseDto<TableResponseDto>(tableResponseDto);
    }
    
    @ResponseBody
    @RequestMapping("remove")
    @Auth
    public Object remove(String tableNo,HttpServletRequest request,HttpServletResponse response) throws IllegalArgumentException, IllegalAccessException, NoSuchAlgorithmException{
        String ssToken = HttpRequestUtils.ssToken(request);
        HttpCookiesUtils.resetAuthCookies(request, response);
        tableInfoDao.delete(ssToken, tableNo);
        tableColumnDao.delete(ssToken, tableNo);
        tableRowDao.delete(ssToken, tableNo);
        return new BaseResponseDto<Void>();
    }
    
    @ResponseBody
    @RequestMapping("list")
    @Auth
    public Object list(HttpServletRequest request,HttpServletResponse response) throws NoSuchAlgorithmException{
        String ssToken = HttpRequestUtils.ssToken(request);
        HttpCookiesUtils.resetAuthCookies(request, response);
        List<TableInfo> list = tableInfoDao.selectBySsToken(ssToken);
        if(list.isEmpty()){
            return new BaseResponseDto<List<TableResponseDto>>();
        }
        List<TableResponseDto> result = new ArrayList<TableResponseDto>();
        for(TableInfo tableInfo : list){
            TableResponseDto tableResponseDto = new TableResponseDto();
            tableResponseDto.setId(tableInfo.getId());
            tableResponseDto.setTableDesc(tableInfo.getTableDesc());
            tableResponseDto.setTableName(tableInfo.getTableName());
            tableResponseDto.setTableNo(tableInfo.getTableNo());
            result.add(tableResponseDto);
        }
        return new BaseResponseDto<List<TableResponseDto>>(result);
    }
    
    @ResponseBody
    @RequestMapping("show")
    @Auth
    public Object show_table(String tableNo,HttpServletRequest request,HttpServletResponse response) throws NoSuchAlgorithmException{
        String ssToken = HttpRequestUtils.ssToken(request);
        HttpCookiesUtils.resetAuthCookies(request, response);
        TableInfo tableInfo = tableInfoDao.selectByTableNo(ssToken,tableNo);
        if(tableInfo == null){
            return new BaseResponseDto<TableDetailResponseDto>();
        }
        TableDetailResponseDto tableDetailResponseDto = new TableDetailResponseDto();
        tableDetailResponseDto.setId(tableInfo.getId());
        tableDetailResponseDto.setTableDesc(tableInfo.getTableDesc());
        tableDetailResponseDto.setTableName(tableInfo.getTableName());
        tableDetailResponseDto.setTableNo(tableInfo.getTableNo());
        List<TableColumn> columns = tableColumnDao.selectByTableNo(ssToken, tableNo);
        if(columns.isEmpty()){
            return new BaseResponseDto<TableDetailResponseDto>(tableDetailResponseDto);
        }
        for(TableColumn column : columns){
            tableDetailResponseDto.addColumn(column.getColumnKey(), column.getColumnName(), column.getSecrets());
        }
        List<TableRow> rows = tableRowDao.selectByTableNo(ssToken,tableNo);
        if(rows.isEmpty()){
            return new BaseResponseDto<TableDetailResponseDto>(tableDetailResponseDto);
        }
        Map<Long, Map<String, String>> map = new TreeMap<Long, Map<String,String>>();
        for(TableRow row : rows){
            Long rowNo = row.getRowNo();
            Map<String, String> m = null;
            if(map.containsKey(rowNo)){
                m = map.get(rowNo);
            }else{
                m = new HashMap<String, String>();
                map.put(rowNo, m);
            }
            m.put(row.getColumnKey(), row.getColumnValue());
        }
        tableDetailResponseDto.setRows(map);
        return new BaseResponseDto<TableDetailResponseDto>(tableDetailResponseDto);
    }
}
