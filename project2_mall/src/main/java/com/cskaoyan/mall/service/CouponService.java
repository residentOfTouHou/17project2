package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.Coupon;
import com.cskaoyan.mall.bean.generator.CouponUser;

import java.util.List;

public interface CouponService {
    List<Coupon> queryCouponList(Integer page, Integer limit, String sort, String order, String name,
                                 Integer type, Integer status);

    Coupon queryCouponById(int id);

    List<CouponUser> queryCouponUsers(Integer page,Integer limit,Integer couponId,Integer userId,Integer status,String sort,String order);

    Coupon updateCouponInfo(Coupon coupon);

    void deleteCoupon(Coupon coupon);

    Coupon createCoupon(Coupon coupon);
}
