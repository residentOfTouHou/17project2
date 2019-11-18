package com.cskaoyan.mall.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtil {

    /**
     * 获取当前时间
     * @return 返回一个2019-04-16 09:08:30格式的当前时间 类型为String
     */
    public static String getCurrentTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(date);
        return currentTime;
    }

    public static String parseDate2String(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

}
