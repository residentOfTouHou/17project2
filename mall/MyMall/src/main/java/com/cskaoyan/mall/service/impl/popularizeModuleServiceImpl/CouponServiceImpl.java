package com.cskaoyan.mall.service.impl.popularizeModuleServiceImpl;


import com.cskaoyan.mall.bean.generator.popularizeModule.Coupon;
import com.cskaoyan.mall.bean.generator.popularizeModule.CouponUser;
import com.cskaoyan.mall.mapper.popularizeModuleMapper.CouponMapper;
import com.cskaoyan.mall.service.popularizeModuleService.CouponService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponMapper couponMapper;

    @Override
    public List<Coupon> queryCouponList(Integer page,
                                        Integer limit,
                                        String sort,
                                        String order,
                                        String name,
                                        Integer type,
                                        Integer status) {
        PageHelper.startPage(page,limit);

        if(name != null){
            name = "%"+name+"%";
        }
        List<Coupon> couponList = couponMapper.queryCouponList(sort,order,name,
                type,status);
        return couponList;
    }

    @Override
    public Coupon queryCouponById(int id) {
        Coupon coupon = couponMapper.queryCouponById(id);
        return coupon;
    }

    @Override
    public List<CouponUser> queryCouponUsers(Integer page,
                                             Integer limit,
                                             Integer couponId,
                                             Integer userId,
                                             Integer status,
                                             String sort,
                                             String order) {
        PageHelper.startPage(page,limit);
        List<CouponUser> couponUsers = couponMapper.queryCouponUsers(couponId,sort,order,userId,status);
        return couponUsers;
    }

    @Override
    public Coupon updateCouponInfo(Coupon coupon) {
        couponMapper.updateCoupon(coupon);
        int id = coupon.getId();
        Coupon updatedCoupon = couponMapper.queryCouponById(id);
        return updatedCoupon;
    }

    @Override
    public void deleteCoupon(Coupon coupon) {
        int id = coupon.getId();
        couponMapper.deleteCoupon(id);
    }

    @Override
    public Coupon createCoupon(Coupon coupon) {
        int flag = couponMapper.createCoupon(coupon);
        int id = coupon.getId();
        if(flag == 1){
            return couponMapper.queryCouponById(id);
        }
        return  null;
    }

    public List<Coupon> queryCoupons(){
        return couponMapper.queryCoupons();
    }

}
