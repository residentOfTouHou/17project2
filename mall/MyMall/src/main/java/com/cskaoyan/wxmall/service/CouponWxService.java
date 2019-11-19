package com.cskaoyan.wxmall.service;

import com.cskaoyan.mall.bean.generator.popularizeModule.Coupon;

import java.util.List;

public interface CouponWxService {
    List<Coupon> queryCoupons(int page,int limit);
}
