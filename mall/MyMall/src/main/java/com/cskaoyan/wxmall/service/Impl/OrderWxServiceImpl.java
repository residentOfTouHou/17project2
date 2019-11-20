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
import com.cskaoyan.wxmall.service.OrderWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/19
 * @time 23:01
 */
@Service
public class OrderWxServiceImpl implements OrderWxService {

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
     * order_sn不清楚随机生成
     * 运费字段关联由cart最后决定给不给
     * 团购规则未写
     * 积分减免没有找到
     * good_price 取cart中的 price （暂视为单价）
     *
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
        Date date = new Date();
        String format = new SimpleDateFormat("yyyyMMdd").format(date);
        BigDecimal number = new BigDecimal(cart.getNumber());
        BigDecimal result = number.multiply(cart.getPrice()); //总价

        Order record = new Order();
        record.setUserId(cart.getUserId());
        record.setMessage(orderBean.getMessage());
        record.setOrderSn(format+ (UUID.randomUUID()));
        record.setConsignee(address.getName());
        record.setMobile(address.getMobile());
        record.setAddress(address.getAddress());
        if(orderBean.getMessage()!=null){
            record.setMessage(orderBean.getMessage());
        }else {
            record.setMessage("");
        }
        record.setGoodsPrice(result);
        record.setFreightPrice(new BigDecimal(0)); // 缺少
        record.setCouponPrice(coupon.getDiscount());
        record.setIntegralPrice(new BigDecimal(0)); // 缺少
        record.setGrouponPrice(new BigDecimal(0)); // 缺少
        record.setOrderPrice(result); // 缺少
        record.setActualPrice(result); //缺少
        record.setPayId(String.valueOf(cartId)); //缺少微信付款id
        //缺少付款时间
        record.setComments(cart.getNumber());
        record.setAddTime(date);
        record.setUpdateTime(date);

        orderMapper.insertSelective(record);
        Integer id = orderMapper.getLastInsertId();
        return id;
    }
}
