package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.bean.generator.popularizeModule.Coupon;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.wxmall.service.CouponWxService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/wx/coupon")
public class CouponWxController {

    @Autowired
    CouponWxService couponWxService;

    @RequestMapping("list")
    public BaseReqVo quryCouponList(Integer page,Integer size){
        List<Coupon> coupons = couponWxService.queryCoupons(page,size);
        PageInfo<Coupon> adPageInfo = new PageInfo<>(coupons);
        long total = adPageInfo.getTotal();
        Map<String,Object> map =new HashMap<>();
        map.put("count",total);
        map.put("data",coupons);
        return BaseReqVo.ok(map);
    }

    @RequestMapping("receive")
    public BaseReqVo receiveCoupon(@RequestBody Map<String,Object> map){
        Integer couponId = (Integer) map.get("couponId");
        User user = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        int userId = user.getId();
        int flag = couponWxService.checkUserCouponExits(userId,couponId);
        if(flag == 1){
            return new BaseReqVo("您已领取优惠券",740);
        }
        Coupon coupon = couponWxService.queryCouponById(couponId);
        int total = coupon.getTotal();
        if(total != 0){
            total -= 1;
            coupon.setTotal(total);
            couponWxService.updateCoupon(coupon);
        }
        couponWxService.insertCouponUser(userId,coupon);
        return BaseReqVo.ok();
    }

    @RequestMapping("exchange")
    public BaseReqVo exchangeCoupon(@RequestBody Map<String,Object> map){
        Integer code = (Integer) map.get("code");
        User user = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Coupon coupon = couponWxService.queryCouponIdByCode(code);
        int couponId =coupon.getId();
        int userId = user.getId();
        int flag = couponWxService.checkUserCouponExits(userId,couponId);
        if(flag == 1){
            return new BaseReqVo("您已兑换优惠券",740);
        }
        int total = coupon.getTotal();
        if(total != 0){
            total -= 1;
            coupon.setTotal(total);
            couponWxService.updateCoupon(coupon);
        }
        couponWxService.insertCouponUser(userId,coupon);
        return BaseReqVo.ok();
    }

    @RequestMapping("mylist")
    public BaseReqVo getMyCouponList(int status,int page,int size){
        User user = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        int userId = user.getId();
        Map<String,Object> map = new HashMap<>();
        List<Integer> myCouponIds = couponWxService.getMyCouponIds(userId,status);
        List<Coupon> myCouponList = couponWxService.getMyCouponList(myCouponIds,page,size);
        map.put("count",myCouponList.size());
        map.put("data",myCouponList);
        return BaseReqVo.ok(map);
    }

    @RequestMapping("selectlist")
    public BaseReqVo selectlist(int cartId,int grouponRulesId){
        User user = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        int userId = user.getId();
        List<Coupon> coupons = couponWxService.queryCouponByUserId(userId);
        return BaseReqVo.ok(coupons);
    }
}
