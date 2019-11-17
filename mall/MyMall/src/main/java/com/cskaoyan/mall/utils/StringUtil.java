package com.cskaoyan.mall.utils;

public class StringUtil {
    //判断字符串非空（2个条件）：
    //1.引用非空-null
    //2.非空字符串-" "
    public static boolean hasLength(String str){
        return str!=null && !"".equals(str.trim());
    }
    //判断字符串为空
    public static boolean isBlank(String str){
        return !hasLength(str);
    }


}