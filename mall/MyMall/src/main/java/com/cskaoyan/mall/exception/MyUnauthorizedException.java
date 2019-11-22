/**
 *
 */
package com.cskaoyan.mall.exception;

import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.google.gson.Gson;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyUnauthorizedException{
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public String handleException(Exception e, HttpServletRequest request){
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(506);
        baseReqVo.setErrmsg("未授权的操作");
        Gson gson = new Gson();
        return gson.toJson(baseReqVo);
    }


    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public String authenticateHandleException(Exception e, HttpServletRequest request){
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(501);
        baseReqVo.setErrmsg("请登录");
        Gson gson = new Gson();
        return gson.toJson(baseReqVo);
    }
}
