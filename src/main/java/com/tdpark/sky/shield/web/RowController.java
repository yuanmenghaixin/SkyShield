package com.tdpark.sky.shield.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdpark.sky.shield.annotation.Auth;
import com.tdpark.sky.shield.dao.TableColumnDao;
import com.tdpark.sky.shield.dao.TableInfoDao;
import com.tdpark.sky.shield.dao.TableRowDao;
import com.tdpark.sky.shield.domain.TableColumn;
import com.tdpark.sky.shield.domain.TableInfo;
import com.tdpark.sky.shield.domain.TableRow;
import com.tdpark.sky.shield.dto.AddRowRequestDto;
import com.tdpark.sky.shield.dto.BaseResponseDto;
import com.tdpark.sky.shield.dto.ColumnResponseDto;
import com.tdpark.sky.shield.dto.RemoveRowRequestDto;
import com.tdpark.sky.shield.dto.RowEditResponseDto;
import com.tdpark.sky.shield.dto.RowRequestDto;
import com.tdpark.sky.shield.dto.RowResponseDto;
import com.tdpark.sky.shield.dto.ShowRowRequestDto;
import com.tdpark.sky.shield.utils.HttpCookiesUtils;
import com.tdpark.sky.shield.utils.HttpRequestUtils;
import com.tdpark.sky.shield.utils.ParamUtils;

@Controller
@RequestMapping("row")
public class RowController {
    
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
        AddRowRequestDto dto = new ObjectMapper().readValue(params, AddRowRequestDto.class);
        ParamUtils.checkBlank(dto);
        String ssToken = HttpRequestUtils.ssToken(request);
        HttpCookiesUtils.resetAuthCookies(request, response);
        long rowNo = System.currentTimeMillis();
        String tableNo = dto.getTableNo();
        List<RowRequestDto> row = dto.getRow();
        for(RowRequestDto r:row){
            ParamUtils.checkBlank(r);
        }
        for(RowRequestDto r:row){
            if(r.getId() == null || r.getId().longValue() <= 0){
                TableRow tableRow = new TableRow();
                tableRow.setColumnKey(r.getColumnKey());
                tableRow.setColumnValue(r.getColumnValue());
                tableRow.setRowNo(r.getRowNo() <= 0 ? rowNo:r.getRowNo());
                tableRow.setSsToken(ssToken);
                tableRow.setTableNo(tableNo);
                tableRowDao.insert(tableRow);
            }else{
                TableRow tableRow = new TableRow();
                tableRow.setId(r.getId());
                tableRow.setColumnKey(r.getColumnKey());
                tableRow.setColumnValue(r.getColumnValue());
                tableRow.setSsToken(ssToken);
                tableRow.setTableNo(tableNo);
                tableRowDao.update(tableRow);
            }
            
        }
        return new BaseResponseDto<ColumnResponseDto>();
    }
    
    @ResponseBody
    @RequestMapping("remove")
    @Auth
    public Object remove(RemoveRowRequestDto dto,HttpServletRequest request,HttpServletResponse response) throws IllegalArgumentException, IllegalAccessException, NoSuchAlgorithmException{
        ParamUtils.checkBlank(dto);
        String ssToken = HttpRequestUtils.ssToken(request);
        HttpCookiesUtils.resetAuthCookies(request, response);
        String tableNo = dto.getTableNo();
        tableRowDao.deleteByRowNo(ssToken, tableNo, dto.getRowNo());
        return new BaseResponseDto<ColumnResponseDto>();
    }
    
    @ResponseBody
    @RequestMapping("show")
    @Auth
    public Object show(ShowRowRequestDto dto,HttpServletRequest request,HttpServletResponse response) throws IllegalArgumentException, IllegalAccessException, NoSuchAlgorithmException{
        ParamUtils.checkBlank(dto);
        String ssToken = HttpRequestUtils.ssToken(request);
        HttpCookiesUtils.resetAuthCookies(request, response);
        String tableNo = dto.getTableNo();
        TableInfo tableInfo = tableInfoDao.selectByTableNo(ssToken, tableNo );
        RowEditResponseDto rowEditResponseDto = null;
        if(tableInfo != null){
            rowEditResponseDto = new RowEditResponseDto();
            rowEditResponseDto.setTableNo(tableInfo.getTableNo());
            rowEditResponseDto.setTableName(tableInfo.getTableName());
        }else{
            return new BaseResponseDto<Void>();
        }
        List<TableColumn> list = tableColumnDao.selectByTableNo(ssToken,tableNo);
        List<TableRow> row = tableRowDao.selectByRowNo(ssToken, tableNo, dto.getRowNo());
        Map<String, TableRow> rowMap = new TreeMap<String, TableRow>();
        for(TableRow r : row){
            rowMap.put(r.getColumnKey(), r);
        }
        List<RowResponseDto> rowList = new ArrayList<RowResponseDto>();
        for(TableColumn c : list){
            RowResponseDto rowResponseDto = new RowResponseDto();
            rowResponseDto.setColumnKey(c.getColumnKey());
            rowResponseDto.setColumnName(c.getColumnName());
            rowResponseDto.setSecrets(c.getSecrets());
            if(rowMap.containsKey(c.getColumnKey())){
                TableRow tableRow = rowMap.get(c.getColumnKey());
                rowResponseDto.setId(tableRow.getId());
                rowResponseDto.setColumnValue(tableRow.getColumnValue());
            }
            rowList.add(rowResponseDto);
        }
        rowEditResponseDto.setRow(rowList);
        return new BaseResponseDto<RowEditResponseDto>(rowEditResponseDto);
    }
}
