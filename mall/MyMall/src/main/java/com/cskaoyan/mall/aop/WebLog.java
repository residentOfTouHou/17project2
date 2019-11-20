/**
 *
 */
package com.cskaoyan.mall.aop;


import com.cskaoyan.mall.bean.generator.Log;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Aspect
@Component
public class WebLog {

    @Autowired
    LogService logService;

    @Pointcut("execution(public * com.cskaoyan.mall.controller.*.*(..))")
    public void Pointcut() {
    }

    //@Around：环绕通知
    @Around("Pointcut()")
    public Object Around(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes=
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        Log log = new Log();
        Date date = new Date();
        log.setAdmin((String) request.getSession().getAttribute("admin"));
        log.setIp(request.getRemoteAddr());
        log.setDeleted(false);
        //根据url判断操作
        String uri = request.getRequestURI().toString();

        //需要url配合shiro查询授权
        //0-一般操作  1-安全操作  2-订单操作  3-其他操作
        if(uri.endsWith("delete")){
            log.setType(0);
        }else if(uri.endsWith("login") || uri.endsWith("update") || uri.endsWith("create")){
            log.setType(1);
        }else if(uri.contains("order")){
            log.setType(2);
        }else {
            log.setType(3);
        }
        //根据url判断操作
        if(uri.endsWith("login")){
            log.setAction("登录");
        }else if(uri.endsWith("delete")) {
            log.setAction("删除");
        }else if(uri.endsWith("update")) {
            log.setAction("更新");
        }else if(uri.endsWith("create")){
            log.setAction("增加");
        }else {
            log.setAction("其他");
        }
        Object object = pjp.proceed();
        //0-失败  1-成功

        BaseReqVo baseReqVo = (BaseReqVo) object;
        if(baseReqVo.getErrno() == 0)
            log.setStatus(true);
        else
            log.setStatus(false);
        //未知
        log.setResult("");
        //comment未知
        log.setComment("");
        log.setAddTime(date);
        log.setUpdateTime(date);
        logService.insertLog(log);
        return object;
    }
}
