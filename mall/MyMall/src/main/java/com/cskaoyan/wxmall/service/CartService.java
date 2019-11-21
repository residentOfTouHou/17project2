package com.cskaoyan.wxmall.service;

import com.cskaoyan.mall.bean.generator.GoodsAlter;
import com.cskaoyan.mall.bean.generator.GoodsProduct;
import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.wxmall.bean.CartCheckedReq;
import com.cskaoyan.wxmall.bean.CartCheckoutReq;

import java.util.List;
import java.util.Map;

/**
 * @author 杨盛
 * @date 2019/11/20 11:22
 */
public interface CartService {

    long queryTotalNumber(User principal);

    int insertCart(User principal, GoodsAlter goods, GoodsProduct goodsProduct, int number);

    Map<String, Object> queryCarts();

    int updateCheckeds(CartCheckedReq checkedReq);

    int updateCart(int id, int number);

    int deleteById(List<Integer> productIds);

    int fastAddCart(User principal, GoodsAlter goods, GoodsProduct goodsProduct, int number);

    Map<String, Object> checkoutOrder(CartCheckoutReq cartCheckoutReq);
}
