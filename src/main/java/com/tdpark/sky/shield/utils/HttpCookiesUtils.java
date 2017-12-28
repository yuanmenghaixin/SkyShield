package com.tdpark.sky.shield.utils;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tdpark.eutils.MD5Utils;
import com.tdpark.eutils.StringUtils;
import com.tdpark.eutils.UUIDUtils;
import com.tdpark.sky.shield.constants.Constants;
import com.tdpark.sky.shield.dto.AuthCookiesDto;

public class HttpCookiesUtils {

    public static AuthCookiesDto buildAuthCookiesDto(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        AuthCookiesDto dto = new AuthCookiesDto();
        if(cookies == null){
            return dto;
        }
        for(Cookie cookie : cookies){
            String name = cookie.getName();
            if(name.equals(Constants.SIGN_KEY)){
                dto.setSign(cookie.getValue());
            }else if(name.equals(Constants.TOKEN_KEY)){
                dto.setToken(cookie.getValue());
            }else if(name.equals(Constants.RANDOM_CODE_KEY)){
                dto.setRandomCode(cookie.getValue());
            }else if(name.equals(Constants.TIMESTAMP_KEY)){
                dto.setTimestamp(cookie.getValue());
            }
        }
        if(StringUtils.isNotBlank(dto.getToken()) && StringUtils.isNotBlank(dto.getRandomCode())){
            try {
                dto.setSsToken(ConfoundingUtils.toSSToken(dto.getToken(), dto.getRandomCode()));
                request.setAttribute(Constants.SS_TOKEN_KEY, dto.getSsToken());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return dto;
    }
    
    public static void resetAuthCookies(HttpServletRequest request,HttpServletResponse response) throws NoSuchAlgorithmException{
        String ssToken = HttpRequestUtils.ssToken(request);
        if(StringUtils.isBlank(ssToken)){
            return;
        }
        String timestamp = String.valueOf(System.currentTimeMillis());
        String randomCode = UUIDUtils.uuid();
        String token = ConfoundingUtils.toToken(ssToken, randomCode);
        String sign = MD5Utils.toMD5(String.format("%s%s%s",timestamp,token,Constants.MD5_KEY));
        Cookie timestampCookie = new Cookie(Constants.TIMESTAMP_KEY, timestamp);
        timestampCookie.setPath(Constants.COOKIE_PATH);
        response.addCookie(timestampCookie);
        Cookie randomCodeCookie = new Cookie(Constants.RANDOM_CODE_KEY, randomCode);
        randomCodeCookie.setPath(Constants.COOKIE_PATH);
        response.addCookie(randomCodeCookie);
        Cookie tokenCookie = new Cookie(Constants.TOKEN_KEY, token);
        tokenCookie.setPath(Constants.COOKIE_PATH);
        response.addCookie(tokenCookie);
        Cookie signCookie = new Cookie(Constants.SIGN_KEY, sign);
        signCookie.setPath(Constants.COOKIE_PATH);
        response.addCookie(signCookie);
    }
    
    public static String getValue(HttpServletRequest request,String key){
        Cookie[] cookies = request.getCookies();
        if(cookies == null || cookies.length <= 0){
            return null;
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(key)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
