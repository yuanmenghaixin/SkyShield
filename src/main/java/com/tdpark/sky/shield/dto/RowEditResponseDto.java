package com.tdpark.sky.shield.dto;

import java.util.List;

public class RowEditResponseDto extends TableResponseDto{

    private List<RowResponseDto> row;

    public List<RowResponseDto> getRow() {
        return row;
    }

    public void setRow(List<RowResponseDto> row) {
        this.row = row;
    }
    
}
