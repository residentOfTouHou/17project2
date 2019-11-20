package com.cskaoyan.wxmall.service.impl;

import com.cskaoyan.mall.bean.generator.Address;
import com.cskaoyan.mall.bean.generator.Cart;
import com.cskaoyan.mall.bean.generator.Order;
import com.cskaoyan.mall.bean.generator.popularizeModule.Coupon;
import com.cskaoyan.mall.mapper.AddressMapper;
import com.cskaoyan.mall.mapper.CartMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.mapper.popularizeModuleMapper.CouponMapper;
import com.cskaoyan.wxmall.bean.SubmitOrderBean;
import com.cskaoyan.wxmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/19
 * @time 23:01
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    CouponMapper couponMapper;

    @Autowired
    OrderMapper orderMapper;

    /**
     * 提交订单
     *
     * 团购规则未写
     * @param orderBean
     * @return
     */
    @Override
    public Integer submitOrder(SubmitOrderBean orderBean) {
        Integer cartId = orderBean.getCartId();
        Integer addressId = orderBean.getAddressId();
        Integer couponId = orderBean.getCouponId();
        Cart cart = cartMapper.selectByPrimaryKey(cartId);
        Address address = addressMapper.selectByPrimaryKey(addressId);
        Coupon coupon = couponMapper.queryCouponById(couponId);
        Order record = new Order();

        record.setUserId(cart.getUserId());
        record.setMessage(orderBean.getMessage());


        orderMapper.insertSelective(record);
        Integer id = orderMapper.getLastInsertId();
        return id;
    }
}
