package com.tdpark.sky.shield.utils;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdpark.sky.shield.constants.Constants;
import com.tdpark.sky.shield.dto.BaseResponseDto;
import com.tdpark.sky.shield.enums.ErrorEnum;

public class ResponseUtils {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ResponseUtils.class);

    public static void buildResponse(HttpServletResponse response, ErrorEnum errorEnum) {
        buildResponse(response, errorEnum.getCode(), errorEnum.getDesc());
    }
    public static void buildResponse(HttpServletResponse response, String code,String msg) {
        ServletOutputStream os = null;
        try {
            response.addHeader(Constants.CONTENT_TYPE_KEY,Constants.CONTENT_TYPE_VALUE);
            os = response.getOutputStream();
            BaseResponseDto<Void> baseResponseDto = new BaseResponseDto<Void>(code,msg);
            os.write(new ObjectMapper().writeValueAsString(baseResponseDto).getBytes(Constants.CHARSET));
            os.flush();
        } catch (IOException e) {
            LOGGER.error("", e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    LOGGER.error("", e);
                }
            }
        }
    }
}
