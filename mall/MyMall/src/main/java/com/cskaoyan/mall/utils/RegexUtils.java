package com.cskaoyan.mall.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类 用来校验一些参数
 */
public class RegexUtils {

    /**
     * 校验QQ号方法
     * @return 返回true表示是QQ号
     */
    public static boolean isQQ(String str) {
        String pattern = "[1-9]([0-9]{4,10})";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.matches();
    }

    /**
     * 校验手机号方法
     */
    public static boolean isPhoneNumber(String str) {
        String pattern = "0?(13|14|15|18|17)[0-9]{9}";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.matches();
    }
    /**
     * 校验运费方法 （只能为正整数）
     */
    public static boolean isNumber(String str) {
        String pattern = "[1-9]\\d*";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.matches();
    }
}
