package com.cskaoyan.wxmall.service;

import com.cskaoyan.mall.bean.generator.GoodsAlter;
import com.cskaoyan.mall.bean.generator.Order;
import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.bean.generator.popularizeModule.Groupon;
import com.cskaoyan.mall.bean.generator.popularizeModule.GrouponRules;
import com.cskaoyan.wxmall.bean.GrouponGoodsWxBean;
import com.cskaoyan.wxmall.bean.GrouponWxBean;
import com.cskaoyan.wxmall.bean.OrderInfo;

import java.util.List;

public interface GrouponWxService {

    List<GrouponWxBean> queryWxGrouponsList(Integer page, Integer size);

    Groupon queryGrouponByUserId(int userId);

    Order queryOrderByOrderId(int orderId);

    List<Integer> queryOrderGoodsByOrderId(int orderId);

    List<GrouponGoodsWxBean> queryGoodsListByGoodIds(List<Integer> goodsIds);

    GrouponRules queryGrouponRuleById(int grouponRulesId);

    Groupon queryGrouponById(int grouponId);

    OrderInfo queryOrderByUserId(int userId);
}
