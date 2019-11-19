package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.generator.popularizeModule.Coupon;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.wxmall.service.CouponWxService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/wx/coupon")
public class CouponController {

    @Autowired
    CouponWxService couponService;

    @RequestMapping("list")
    public BaseReqVo quryCouponList(int page,int limit){
        List<Coupon> coupons = couponService.queryCoupons(page,limit);
        PageInfo<Coupon> adPageInfo = new PageInfo<>(coupons);
        long total = adPageInfo.getTotal();
        Map<String,Object> map =new HashMap<>();
        map.put("count",total);
        map.put("data",coupons);
        return BaseReqVo.ok(map);
    }
}
