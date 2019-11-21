package com.cskaoyan.wxmall.service.impl;


import com.cskaoyan.mall.bean.generator.Order;
import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.bean.generator.popularizeModule.Groupon;
import com.cskaoyan.mall.bean.generator.popularizeModule.GrouponRules;
import com.cskaoyan.mall.mapper.popularizeModuleMapper.GrouponMapper;
import com.cskaoyan.mall.mapper.popularizeModuleMapper.GrouponRulesMapper;
import com.cskaoyan.wxmall.bean.GrouponGoodsWxBean;
import com.cskaoyan.wxmall.bean.GrouponWxBean;
import com.cskaoyan.wxmall.bean.OrderInfo;
import com.cskaoyan.wxmall.service.GrouponWxService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.acl.Group;
import java.util.List;

@Service
public class GrouponWxServiceImpl implements GrouponWxService {

    @Autowired
    GrouponMapper grouponMapper;

    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    public List<GrouponWxBean> queryWxGrouponsList(Integer page,Integer size){
        PageHelper.startPage(page,size);
        List<GrouponWxBean> grouponWxBeanList = grouponMapper.queryWxGrouponsList();
        for (GrouponWxBean grouponWxBean : grouponWxBeanList) {
            BigDecimal dicount = grouponWxBean.getGroupon_price();
            BigDecimal retailPrice = grouponWxBean.getGoods().getRetailPrice();
            BigDecimal grouponPrice = retailPrice.subtract(dicount);
            grouponWxBean.setGroupon_price(grouponPrice);
        }
        return grouponWxBeanList;
    }

    @Override
    public Groupon queryGrouponByUserId(int userId) {
        return grouponMapper.queryGrouponByUserId(userId);
    }

    @Override
    public Order queryOrderByOrderId(int orderId) {
        return grouponMapper.queryOrderByOrderId(orderId);
    }

    @Override
    public List<Integer> queryOrderGoodsByOrderId(int orderId) {
        return grouponMapper.queryOrderGoodsByOrderId(orderId);
    }

    @Override
    public List<GrouponGoodsWxBean> queryGoodsListByGoodIds(List<Integer> goodsIds) {
        return grouponMapper.queryGoodsListByGoodIds(goodsIds);
    }

    @Override
    public GrouponRules queryGrouponRuleById(int grouponRulesId) {
        return grouponRulesMapper.queryGrouponRulesById(grouponRulesId);
    }

    @Override
    public Groupon queryGrouponById(int grouponId) {
        return grouponMapper.queryGrouponById(grouponId);
    }

    @Override
    public OrderInfo queryOrderByUserId(int userId) {
        return grouponMapper.queryOrderByUserId(userId);
    }

}
