package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.jsonbean.StatGoods;
import com.cskaoyan.mall.bean.jsonbean.StatOrder;
import com.cskaoyan.mall.bean.jsonbean.StatUser;
import com.cskaoyan.mall.mapper.OrderGoodsMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    /**
     * 用户统计
     * @return
     */
    @Override
    public List<StatUser> selectStatUser() {
        List<StatUser> statUserList = userMapper.selectCountsUser();
        return statUserList;
    }

    /**
     * 订单统计
     * @return
     */
    @Override
    public List<StatOrder> selectStatOrder() {
        List<StatOrder>  statOrderList = orderMapper.selectCountsOrder();
        return statOrderList;
    }

    /**
     * 商品统计
     * @return
     */
    @Override
    public List<StatGoods> selectStatGoods() {
        List<StatGoods> statGoodsList = orderGoodsMapper.selectStatGoods();
        return statGoodsList;
    }
}
