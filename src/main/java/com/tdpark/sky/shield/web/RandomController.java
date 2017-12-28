package com.tdpark.sky.shield.web;

import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tdpark.eutils.MD5Utils;
import com.tdpark.sky.shield.constants.Constants;
import com.tdpark.sky.shield.utils.ImgUtils;
import com.tdpark.sky.shield.utils.ImgUtils.ImgCodeDomain;

@Controller
@RequestMapping("random")
public class RandomController {
    
    public static final String RANDON_IMG_CODE = "RANDON_IMG_CODE";
    
    @RequestMapping("/refresh")
    public void imgCodeRefresh(HttpServletRequest request,HttpServletResponse response) throws NoSuchAlgorithmException{
        ImgCodeDomain imgCodeDomain = ImgUtils.randImgCode();
        Long now = System.currentTimeMillis();
        Cookie reandomTimestampCookie = new Cookie(Constants.RANDOM_TIMESTAMP_KEY, String.valueOf(now));
        reandomTimestampCookie.setPath(Constants.COOKIE_PATH);
        response.addCookie(reandomTimestampCookie);
        Cookie reandomSignCookie = new Cookie(Constants.RANDOM_SIGN_KEY, MD5Utils.toMD5(imgCodeDomain.getImgCode() + Constants.MD5_KEY + now));
        reandomSignCookie.setPath(Constants.COOKIE_PATH);
        response.addCookie(reandomSignCookie);
        response.setHeader("Content-Type", "image/jpeg");
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(imgCodeDomain.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
