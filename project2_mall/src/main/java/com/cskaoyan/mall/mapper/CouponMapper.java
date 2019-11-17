package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.generator.Coupon;
import com.cskaoyan.mall.bean.generator.CouponUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CouponMapper {

    List<Coupon> queryCouponList(@Param("sort") String sort,
                                 @Param("order")String order,
                                 @Param("name")String name,
                                 @Param("type")Integer type,
                                 @Param("status")Integer status);

    Coupon queryCouponById(int id);

    List<CouponUser> queryCouponUsers(@Param("couponId") Integer couponId,
                                      @Param("sort") String sort,
                                      @Param("order") String order,
                                      @Param("userId")Integer userId,
                                      @Param("status")Integer status);

    void updateCoupon(@Param("coupon") Coupon coupon);

    void deleteCoupon( int id);

    int createCoupon(@Param("coupon") Coupon coupon);
}
