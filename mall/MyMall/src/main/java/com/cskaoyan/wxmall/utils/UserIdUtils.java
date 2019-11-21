package com.cskaoyan.wxmall.utils;

import com.cskaoyan.mall.bean.generator.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * userID工具类
 */
public class UserIdUtils {

    /**
     * 获取当前用户的userId
     * 返回0代表当前用户未登录或认证已过期
     */
    public static Integer getCurrentUserId(){
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        User primaryPrincipal = null;
        Integer userId = 0;
        if(principals != null){
            primaryPrincipal  = (User) principals.getPrimaryPrincipal();
            userId = primaryPrincipal.getId();
        }
        return userId;
    }
}
