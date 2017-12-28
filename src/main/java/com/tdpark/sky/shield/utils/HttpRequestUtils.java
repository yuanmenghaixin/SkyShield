package com.tdpark.sky.shield.utils;

import javax.servlet.http.HttpServletRequest;

import com.tdpark.sky.shield.constants.Constants;

public class HttpRequestUtils {

    public static String ssToken(HttpServletRequest request){
        Object obj = request.getAttribute(Constants.SS_TOKEN_KEY);
        return obj == null ? null : obj.toString();
    }
}
