package com.cskaoyan.mall.controller;


import com.cskaoyan.mall.bean.generator.Coupon;
import com.cskaoyan.mall.bean.generator.CouponUser;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.CouponService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    @RequestMapping("list")
    public BaseReqVo queryCouponList(Integer page, Integer limit, String sort, String order, String name,
                                     Integer type, Integer status){
        List<Coupon> couponList = couponService.queryCouponList(page,limit,sort,order,name,type,status);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setData(couponList);
        PageInfo<Coupon> adPageInfo = new PageInfo<>(couponList);
        long total = adPageInfo.getTotal();
        Map<String,Object> couponsMap = new HashMap<>();
        couponsMap.put("items",couponList);
        couponsMap.put("total",total);
        baseReqVo.setErrno(0);
        baseReqVo.setData(couponsMap);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("read")
    public BaseReqVo read(int id){
        Coupon coupon = couponService.queryCouponById(id);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setData(coupon);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("listuser")
    public BaseReqVo listUser(Integer page,Integer limit,Integer couponId,Integer userId,Integer status,String sort,String order){
        List<CouponUser> couponUsers = couponService.queryCouponUsers(page,limit,couponId,userId,status,sort,order);
        PageInfo<CouponUser> adPageInfo = new PageInfo<>(couponUsers);
        long total = adPageInfo.getTotal();
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        Map<String,Object> couponUsersMap = new HashMap<>();
        couponUsersMap.put("items",couponUsers);
        couponUsersMap.put("total",total);
        baseReqVo.setData(couponUsersMap);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("update")
    public BaseReqVo update(@RequestBody Coupon coupon){
        Coupon updatedcoupon = couponService.updateCouponInfo(coupon);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setData(updatedcoupon);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("delete")
    public BaseReqVo deleteCouopon(@RequestBody Coupon coupon){
        couponService.deleteCoupon(coupon);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
