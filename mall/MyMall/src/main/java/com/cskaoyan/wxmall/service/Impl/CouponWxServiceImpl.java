package com.cskaoyan.wxmall.service.Impl;

import com.cskaoyan.mall.bean.generator.popularizeModule.Coupon;
import com.cskaoyan.mall.mapper.popularizeModuleMapper.CouponMapper;
import com.cskaoyan.wxmall.service.CouponWxService;
import com.github.pagehelper.PageHelper;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponWxServiceImpl implements CouponWxService {

    @Autowired
    CouponMapper couponMapper;


    @Override
    public List<Coupon> queryCoupons(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return couponMapper.queryCoupons();
    }

    @Override
    public Coupon queryCouponById(Integer couponId) {
        return couponMapper.queryCouponById(couponId);
    }

    @Override
    public void insertCouponUser(int userId, Coupon lessenTotalCoupon) {
        couponMapper.insertCouponUser(userId,lessenTotalCoupon);
    }

    @Override
    public int checkUserCouponExits(int userId, Integer couponId) {
        int flag = couponMapper.checkUserCouponExits(userId,couponId);
        return flag;
    }

    @Override
    public List<Integer> getMyCouponIds(int userId, int status) {
        return couponMapper.getMyCouponIds(userId,status);
    }

    @Override
    public List<Coupon> getMyCouponList(List<Integer> myCouponIds,int page,int size) {
        PageHelper.startPage(page,size);
        return couponMapper.getMycouponList(myCouponIds);
    }

    @Override
    public Coupon queryCouponIdByCode(Integer code) {
        return couponMapper.queryCouponByCode(code);
    }

    @Override
    public void updateCoupon(Coupon lessenTotalCoupon) {
        couponMapper.updateCoupon(lessenTotalCoupon);
    }

    @Override
    public List<Coupon> queryCouponByUserId(int userId) {
        return couponMapper.queryCouponByUserId(userId);
    }
}
