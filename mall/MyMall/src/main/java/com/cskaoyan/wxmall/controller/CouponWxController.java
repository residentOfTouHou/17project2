package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.generator.popularizeModule.Coupon;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.wxmall.service.CouponWxService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/wx/coupon")
public class CouponWxController {

    @Autowired
    CouponWxService couponService;

    @RequestMapping("list")
    public BaseReqVo quryCouponList(Integer page,Integer size){
        List<Coupon> coupons = couponService.queryCoupons(page,size);
        PageInfo<Coupon> adPageInfo = new PageInfo<>(coupons);
        long total = adPageInfo.getTotal();
        Map<String,Object> map =new HashMap<>();
        map.put("count",total);
        map.put("data",coupons);
        return BaseReqVo.ok(map);
    }
}
