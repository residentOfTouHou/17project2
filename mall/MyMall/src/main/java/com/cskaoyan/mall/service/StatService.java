package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.jsonbean.StatGoods;
import com.cskaoyan.mall.bean.jsonbean.StatOrder;
import com.cskaoyan.mall.bean.jsonbean.StatUser;

import java.util.List;

public interface StatService {

    List<StatUser> selectStatUser();

    List<StatOrder> selectStatOrder();

    List<StatGoods> selectStatGoods();
}
