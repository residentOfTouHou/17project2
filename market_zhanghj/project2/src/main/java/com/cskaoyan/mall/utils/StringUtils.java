package com.cskaoyan.mall.utils;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/15
 * @time 23:27
 */
public class StringUtils {
    public static boolean isEmpty(String s){
        if(s==null||s.trim()==""){
            return true;
        }
        return false;
    }
}
