package com.cskaoyan.mall.wxmall.service;

import com.cskaoyan.mall.bean.generator.GoodsAlter;
import com.cskaoyan.mall.bean.generator.GoodsProduct;
import com.cskaoyan.mall.bean.generator.User;

/**
 * @author 杨盛
 * @date 2019/11/20 11:22
 */
public interface CartService {

    long queryTotalNumber();

    int insertCart(User principal, GoodsAlter goods, GoodsProduct goodsProduct, int number);
}
