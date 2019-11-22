package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.generator.Order;
import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.bean.generator.popularizeModule.Groupon;
import com.cskaoyan.mall.bean.generator.popularizeModule.GrouponRules;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.wxmall.bean.*;
import com.cskaoyan.wxmall.service.GrouponWxService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/groupon")
public class GrouponWxController {

    @Autowired
    GrouponWxService grouponWxService;


    @RequestMapping("list")
    public BaseReqVo getGrouponList(Integer page,Integer size){
        List<GrouponWxBean> grouponWxBeanList = grouponWxService.queryWxGrouponsList(page,size);
        Map<String,Object> map = new HashMap<>();
        map.put("data",grouponWxBeanList);
        return BaseReqVo.ok(map);
    }

    @RequestMapping("my")
    public BaseReqVo getMyGroupon(int showType){
        MyGroupon myGroupon = new MyGroupon();
        User user = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        int userId =user.getId();
            myGroupon.setOrderStatusText("已取消");
            myGroupon.setCreator(user.getNickname());
            Groupon groupon = grouponWxService.queryGrouponByUserId(userId);
            myGroupon.setGroupon(groupon);
            int orderId = groupon.getOrderId();
            myGroupon.setOrderId(orderId);
            Order order = grouponWxService.queryOrderByOrderId(orderId);
            String orderSn = order.getOrderSn();
            BigDecimal actualPrice = order.getActualPrice();
            int joinerCount = 1;
            myGroupon.setOrderSn(orderSn);
            myGroupon.setActualPrice(actualPrice);
            myGroupon.setJoinerCount(joinerCount);
            List<Integer> goodsIds = grouponWxService.queryOrderGoodsByOrderId(orderId);
            List<GrouponGoodsWxBean> goodsList = grouponWxService.queryGoodsListByGoodIds(goodsIds);
            myGroupon.setGoods(goodsList);
            int grouponRulesId = groupon.getGrouponId();
            GrouponRules grouponRules = grouponWxService.queryGrouponRuleById(grouponRulesId);
            myGroupon.setId(1);
            myGroupon.setRules(grouponRules);
            HandleOption handleOption = new HandleOption(false,true,false,false,false,false,false);
            myGroupon.setHandleOption(handleOption);
            myGroupon.setCount(1);
        Map<String,Object> map = new HashMap<>();
        List<Object> myGroupons = new ArrayList<>();
        myGroupons.add(myGroupon);
        map.put("data",myGroupons);
        return BaseReqVo.ok(map);
    }

    @RequestMapping("detail")
    public BaseReqVo getGrouponDetail(int grouponId){
        Groupon groupon = grouponWxService.queryGrouponById(grouponId);
        User user = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        int userId = user.getId();
        OrderInfo orderInfo = grouponWxService.queryOrderByUserId(userId);
        int orderId = orderInfo.getId();
        List<Integer> goodsIds = grouponWxService.queryOrderGoodsByOrderId(orderId);
        List<GrouponGoodsWxBean> goodsList = grouponWxService.queryGoodsListByGoodIds(goodsIds);
        int grouponRulesId = groupon.getGrouponId();
        GrouponRules grouponRules = grouponWxService.queryGrouponRuleById(grouponRulesId);
        HandleOption handleOption = new HandleOption(false,true,false,false,false,false,false);
        orderInfo.setHandleOption(handleOption);
        List<User> users = new ArrayList<>();
        users.add(user);
        Map<String,Object> map = new HashMap<>();
        map.put("creator",user);
        map.put("joiners",users);
        map.put("orderInfo",orderInfo);
        map.put("orderGoods",goodsList);
        map.put("rules",grouponRules);
        map.put("linkGrouponId",1);
        return BaseReqVo.ok(map);


    }

}
