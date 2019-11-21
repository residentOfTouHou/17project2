package com.cskaoyan.wxmall.utils;

import com.cskaoyan.mall.bean.generator.Order;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/20
 * @time 15:09
 */
public class OrderWxUtils {
    /**
     * 获取状态码文本
     * @param order_status
     * @return
     */
    public static String getStatusText(Integer order_status){
        if(order_status==101){
            return "未付款";
        }else if(order_status==201){
            return "已付款";
        }else if(order_status==301){
            return "已发货";
        }else if(order_status==401){
            return "已收货(用户)";
        }
        return null;
    }

    /**
     * 获取状态码
     * @param showType
     * @return
     */
    public static int typeToStatusCode(Integer showType){
        if(showType == 1){
            return 101;
        }else if(showType == 2){
            return 201;
        }else if(showType == 3){
            return 301;
        }else if(showType == 4){
            return 401;
        }
        return 0;
    }

    /**
     * handleOption
     *
     * @param order
     * @return
     */
    public static Map<String,Boolean> handleOption(Order order){
        Short orderStatus = order.getOrderStatus();
        Map<String,Boolean> map = new HashMap<>();
        map.put("cancel",false);
        map.put("delete",false);
        map.put("pay",false);
        map.put("comment",false);
        map.put("confirm",false);
        map.put("refund",false);
        map.put("rebuy",false);
        if(orderStatus==101){ //未付款 可以付款可以取消
            map.put("pay",true);
            map.put("cancel",true);
        }else if(orderStatus==102) { //用户取消 可以删除
            map.put("delete",true);
        }else if(orderStatus==201) { //已付款 可以退款
            map.put("refund",true);
        }else if(orderStatus==202||orderStatus==203) { //申请取消 已取消 可以删除
            map.put("delete",true);
        }else if(orderStatus==301) { //已发货 可以确认收货
            map.put("confirm",true);
        }else if(orderStatus==401) { //已收货 可以评价可以删除
            map.put("delete",true);
            map.put("comment",true);
        }
//        if(order.getDeleted()){
//            map.put("delete",true);
//        }
        return map;
    }
}
