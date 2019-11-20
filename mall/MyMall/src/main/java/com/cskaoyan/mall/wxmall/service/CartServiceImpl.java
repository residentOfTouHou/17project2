package com.cskaoyan.mall.wxmall.service;

import com.cskaoyan.mall.bean.generator.*;
import com.cskaoyan.mall.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 杨盛
 * @date 2019/11/20 11:22
 */

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper cartMapper;

    @Override
    public long queryTotalNumber() {
        CartExample example = new CartExample();
        example.createCriteria().andDeletedEqualTo(false);
        List<Cart> carts = cartMapper.selectByExample(example);
        long total = 0;
        for (Cart cart : carts) {
            total += cart.getNumber();
        }
        return total;
    }

    @Override
    public int insertCart(User principal, GoodsAlter goods, GoodsProduct goodsProduct, int number) {
        Cart cart = new Cart();
        int insert = cartMapper.insertCart(principal, goods, goodsProduct, cart, number);
        return insert;
    }
}
