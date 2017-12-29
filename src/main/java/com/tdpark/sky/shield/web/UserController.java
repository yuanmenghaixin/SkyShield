package com.tdpark.sky.shield.web;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tdpark.eutils.MD5Utils;
import com.tdpark.eutils.RSASha1Utils;
import com.tdpark.sky.shield.annotation.Auth;
import com.tdpark.sky.shield.constants.Constants;
import com.tdpark.sky.shield.dao.UserAuthDao;
import com.tdpark.sky.shield.dao.UserInfoDao;
import com.tdpark.sky.shield.domain.UserAuth;
import com.tdpark.sky.shield.domain.UserInfo;
import com.tdpark.sky.shield.dto.BaseResponseDto;
import com.tdpark.sky.shield.dto.LoginRequestDto;
import com.tdpark.sky.shield.dto.RegisterRequestDto;
import com.tdpark.sky.shield.dto.RsaKeys;
import com.tdpark.sky.shield.dto.UserResponseDto;
import com.tdpark.sky.shield.enums.ErrorEnum;
import com.tdpark.sky.shield.utils.HttpCookiesUtils;
import com.tdpark.sky.shield.utils.HttpRequestUtils;
import com.tdpark.sky.shield.utils.ParamUtils;
import com.tdpark.sky.shield.utils.SSExceptionUtils;

@RestController  
@EnableAutoConfiguration
@RequestMapping("user")
public class UserController {
    
    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private UserAuthDao userAuthDao;
    @ResponseBody
    @RequestMapping("rsa_keys")
    public Object getRsaKeys() throws Exception{
        BaseResponseDto<RsaKeys> dto = new BaseResponseDto<RsaKeys>();
        String[] keys = RSASha1Utils.getKeys();
        RsaKeys rsaKeys = new RsaKeys(keys[0], keys[1]);
        dto.setData(rsaKeys);
        return dto;
    }
    
    @ResponseBody
    @RequestMapping("register")
    public Object register(RegisterRequestDto dto,HttpServletRequest request,HttpServletResponse response) throws IllegalArgumentException, IllegalAccessException, NoSuchAlgorithmException{
        ParamUtils.checkParams(dto);
        long randomTimestamp = 0L;
        long now = System.currentTimeMillis();
        try {
            randomTimestamp = Long.parseLong(HttpCookiesUtils.getValue(request, Constants.RANDOM_TIMESTAMP_KEY));
        } catch (Exception e) {
        }
        SSExceptionUtils.check(now - randomTimestamp <= Constants.RANDOM_EXPIRATION, ErrorEnum.RANMOD_CODE_EXPIRATION);
        String randomSign = HttpCookiesUtils.getValue(request, Constants.RANDOM_SIGN_KEY);
        SSExceptionUtils.check(MD5Utils.toMD5(dto.getRandomCode().toUpperCase()+Constants.MD5_KEY+randomTimestamp).equals(randomSign), ErrorEnum.RANMOD_CODE_ERROR);
        SSExceptionUtils.check(dto.getPassWord().equals(dto.getComfWord()), ErrorEnum.COM_PW_ERROR);
        String ssToken = MD5Utils.toMD5(dto.getUserName() + Constants.MD5_KEY);
        UserAuth userAuth = userAuthDao.selectBySsToken(ssToken);
        SSExceptionUtils.check(userAuth == null, ErrorEnum.USER_EXIST);
        UserInfo userInfo = userInfoDao.selectBySsToken(ssToken);
        SSExceptionUtils.check(userInfo == null, ErrorEnum.USER_EXIST);
        userAuth = new UserAuth();
        userAuth.setSsToken(ssToken);
        userAuth.setUserName(dto.getUserName());
        userAuth.setPassWord(MD5Utils.toMD5(dto.getPassWord() + Constants.MD5_KEY));
        userAuthDao.insert(userAuth);
        userInfo = new UserInfo();
        userInfo.setPublicKey(dto.getPublicKey());
        userInfo.setPrivateKey(dto.getPrivateKey());
        userInfo.setSsToken(ssToken);
        userInfoDao.insert(userInfo);
        request.setAttribute(Constants.SS_TOKEN_KEY, ssToken);
        HttpCookiesUtils.resetAuthCookies(request, response);
        return new BaseResponseDto<Void>();
    }
    
    @ResponseBody
    @RequestMapping("login")
    public Object login(LoginRequestDto dto,HttpServletRequest request,HttpServletResponse response) throws Exception{
        ParamUtils.checkParams(dto);
        String ssToken = MD5Utils.toMD5(dto.getUserName() + Constants.MD5_KEY);
        UserAuth userAuth = userAuthDao.selectBySsToken(ssToken);
        SSExceptionUtils.check(userAuth != null, ErrorEnum.USER_NOT_EXIST);
        SSExceptionUtils.check(MD5Utils.toMD5(dto.getPassWord() + Constants.MD5_KEY).equals(userAuth.getPassWord()), ErrorEnum.PW_ERROR);
        request.setAttribute(Constants.SS_TOKEN_KEY, userAuth.getSsToken());
        HttpCookiesUtils.resetAuthCookies(request, response);
        return new BaseResponseDto<Void>();
    }
    
    @ResponseBody
    @RequestMapping("my_keys")
    @Auth
    public Object myKeys(HttpServletRequest request,HttpServletResponse response) throws NoSuchAlgorithmException{
        String ssToken = HttpRequestUtils.ssToken(request);
        UserInfo userInfo = userInfoDao.selectBySsToken(ssToken);
        HttpCookiesUtils.resetAuthCookies(request, response);
        BaseResponseDto<UserResponseDto> baseResponseDto = new BaseResponseDto<UserResponseDto>();
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setPublicKey(userInfo.getPublicKey());
        userResponseDto.setPrivateKey(userInfo.getPrivateKey());
        baseResponseDto.setData(userResponseDto);
        return baseResponseDto;
    }

    @ResponseBody
    @RequestMapping("check")
    @Auth
    public Object check(HttpServletRequest request,HttpServletResponse response) throws NoSuchAlgorithmException{
        return new BaseResponseDto<Void>();
    }
    

    @ResponseBody
    @RequestMapping("exit")
    public Object exit(HttpServletRequest request,HttpServletResponse response) throws NoSuchAlgorithmException{
        Cookie timestampCookie = new Cookie(Constants.TIMESTAMP_KEY, null);
        timestampCookie.setPath(Constants.COOKIE_PATH);
        response.addCookie(timestampCookie);
        Cookie randomCodeCookie = new Cookie(Constants.RANDOM_CODE_KEY, null);
        randomCodeCookie.setPath(Constants.COOKIE_PATH);
        response.addCookie(randomCodeCookie);
        Cookie tokenCookie = new Cookie(Constants.TOKEN_KEY, null);
        tokenCookie.setPath(Constants.COOKIE_PATH);
        response.addCookie(tokenCookie);
        Cookie signCookie = new Cookie(Constants.SIGN_KEY, null);
        signCookie.setPath(Constants.COOKIE_PATH);
        response.addCookie(signCookie);
        return new BaseResponseDto<Void>();
    }
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(MD5Utils.toMD5("jys" + Constants.MD5_KEY));
        System.out.println(MD5Utils.toMD5("woyaodenglu" + Constants.MD5_KEY));
    }
}
