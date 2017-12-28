package com.tdpark.sky.shield.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogInterceptor implements HandlerInterceptor{
    private Logger logger = LoggerFactory.getLogger(LogInterceptor.class);  
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
            Object arg2) throws Exception {
        //logger.info("请求日志:[URI:{} , Param:{}]",request.getRequestURI(),new ObjectMapper().writeValueAsString(request.getParameterMap()));
        logger.info("请求日志:[URI:{} , Param:暂不打印]",request.getRequestURI());
        return true;
    }

}
