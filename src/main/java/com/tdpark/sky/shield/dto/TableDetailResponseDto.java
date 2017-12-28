package com.tdpark.sky.shield.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableDetailResponseDto extends TableResponseDto {

    private List<ColumnResponseDto> columns;
    
    private Map<Long,Map<String, String>> rows;

    public List<ColumnResponseDto> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnResponseDto> columns) {
        this.columns = columns;
    }

    public Map<Long, Map<String, String>> getRows() {
        return rows;
    }

    public void setRows(Map<Long, Map<String, String>> rows) {
        this.rows = rows;
    }

    public void addColumn(String columnKey, String columnName, String secrets) {
        if(columns == null){
            columns = new ArrayList<ColumnResponseDto>();
        }
        ColumnResponseDto dto = new ColumnResponseDto();
        dto.setColumnKey(columnKey);
        dto.setColumnName(columnName);
        dto.setSecrets(secrets);
        columns.add(dto);
    }
    
}
