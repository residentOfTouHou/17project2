package com.cskaoyan.wxmall.service.Impl;

import com.cskaoyan.mall.bean.generator.popularizeModule.Coupon;
import com.cskaoyan.mall.mapper.popularizeModuleMapper.CouponMapper;
import com.cskaoyan.wxmall.service.CouponWxService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponWxServiceImpl implements CouponWxService {

    @Autowired
    CouponMapper couponMapper;


    @Override
    public List<Coupon> queryCoupons(int page, int limit) {
        PageHelper.startPage(page,limit);
        return couponMapper.queryCoupons();
    }
}
