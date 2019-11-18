package com.cskaoyan.mall.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 时间工具类
 */

public class DateUtils {

    /**
     * 获取当前时间
     * @return 返回一个2019-04-16 09:08:30格式的当前时间 类型为String
     */
    public static String currentDateToString(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String format = simpleDateFormat.format(date);
        return format;
    }
}
