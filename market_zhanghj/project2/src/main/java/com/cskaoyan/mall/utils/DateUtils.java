package com.cskaoyan.mall.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/16
 * @time 15:52
 */
public class DateUtils {
    public static String currentDateToString(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String format = simpleDateFormat.format(date);
        return format;
    }
}
