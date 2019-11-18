package com.cskaoyan.mall.utils;

import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/18
 * @time 20:47
 */
public class MoneyUtils {
    /**
     * 两位小数金额校验
     * @param object
     * @return
     */
    public static boolean judgeTwoBigDecimal(Object object) {
        boolean flag = false;
        try {
            if (object!=null) {
                String s = object.toString();
                Pattern compile = Pattern.compile("^[+]?(([1-9]{1}\\d{0,7})|(0{1}))(\\.\\d{0,2})?$");
                if(compile.matcher(s).matches()) {
                    flag = true;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
