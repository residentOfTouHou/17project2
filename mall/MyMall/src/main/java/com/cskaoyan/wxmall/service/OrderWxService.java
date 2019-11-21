package com.cskaoyan.wxmall.service;

import com.cskaoyan.mall.bean.generator.Comment;
import com.cskaoyan.mall.bean.generator.OrderGoods;
import com.cskaoyan.wxmall.bean.SubmitOrderBean;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/19
 * @time 23:03
 */
public interface OrderWxService {

    Integer submitOrder(SubmitOrderBean orderBean);

    Map<String,Object> listOrder(Integer showType, Integer page, Integer size);

    Map<String, Object> detailOrder(Integer id);

    void cancelOrder(Integer id);

    int getOrderNumber();

    void refundOrder(Integer id);

    void deleteOrder(Integer id);

    void confirmOrder(Integer orderId);

    void prepayOrder(Integer orderId);

    OrderGoods goodsOrder(Integer orderId, Integer goodsId);

    void commentOrder(Integer orderGoodsId, Comment comment);

}
