package com.tdpark.sky.shield.interceptor;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tdpark.eutils.MD5Utils;
import com.tdpark.eutils.StringUtils;
import com.tdpark.sky.shield.annotation.Auth;
import com.tdpark.sky.shield.constants.Constants;
import com.tdpark.sky.shield.dto.AuthCookiesDto;
import com.tdpark.sky.shield.enums.ErrorEnum;
import com.tdpark.sky.shield.utils.HttpCookiesUtils;
import com.tdpark.sky.shield.utils.ResponseUtils;

public class AuthInterceptor implements HandlerInterceptor{
    private Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);  
    @Override
    public void afterCompletion(HttpServletRequest arg0,
            HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
            Object arg2, ModelAndView arg3) throws Exception {
        
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            if(!checkAuth(request, response, handlerMethod)){
                return false;
            }
        }
        return true;
    }
    private boolean checkAuth(HttpServletRequest request,
            HttpServletResponse response,HandlerMethod handlerMethod){
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
        if(auth == null || !auth.value()){
            return true;
        }
        AuthCookiesDto authCookiesDto = HttpCookiesUtils.buildAuthCookiesDto(request);
        String sign = authCookiesDto.getSign();
        if(StringUtils.isBlank(sign)){
            ResponseUtils.buildResponse(response, ErrorEnum.NO_AUTH);
            return false;
        }
        String token = authCookiesDto.getToken();
        if(StringUtils.isBlank(token)){
            ResponseUtils.buildResponse(response, ErrorEnum.NO_AUTH);
            return false;
        }
        String randomCode = authCookiesDto.getRandomCode();
        if(StringUtils.isBlank(randomCode)){
            ResponseUtils.buildResponse(response, ErrorEnum.NO_AUTH);
            return false;
        }
        String timestamp = authCookiesDto.getTimestamp();
        if(StringUtils.isBlank(timestamp)){
            ResponseUtils.buildResponse(response, ErrorEnum.NO_AUTH);
            return false;
        }
        try {
            long parseLong = Long.parseLong(timestamp);
            if(System.currentTimeMillis() - parseLong > Constants.EXPIRATION){
                ResponseUtils.buildResponse(response, ErrorEnum.EXPIRATION_TOKEN);
                return false;
            }
        } catch (Exception e) {
            logger.info("时间戳格式有误,timestamp:{}",timestamp);
            ResponseUtils.buildResponse(response, ErrorEnum.NO_AUTH);
            return false;
        }
        try {
            String md5 = MD5Utils.toMD5(String.format("%s%s%s",timestamp,authCookiesDto.getToken(),Constants.MD5_KEY));
            if(!md5.equals(sign)){
                ResponseUtils.buildResponse(response, ErrorEnum.ILL_REQUEST);
                return false;
            }
        } catch (NoSuchAlgorithmException e) {
            logger.info("MD5加密异常,timestamp:{},ssToken:{}",timestamp,token);
            ResponseUtils.buildResponse(response, ErrorEnum.NO_AUTH);
            return false;
        }
        return true;
    }
}
