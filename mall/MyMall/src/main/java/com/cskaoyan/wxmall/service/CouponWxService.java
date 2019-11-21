package com.cskaoyan.wxmall.service;

import com.cskaoyan.mall.bean.generator.popularizeModule.Coupon;

import java.util.List;

public interface CouponWxService {
    List<Coupon> queryCoupons(Integer page,Integer size);

    Coupon queryCouponById(Integer couponId);

    void insertCouponUser(int userId, Coupon lessenTotalCoupon);

    int checkUserCouponExits(int userId, Integer couponId);


    List<Integer> getMyCouponIds(int userId, int status);

    List<Coupon> getMyCouponList(List<Integer> myCouponIds,int page,int size);

    Coupon queryCouponIdByCode(Integer code);

    void updateCoupon(Coupon lessenTotalCoupon);
}
