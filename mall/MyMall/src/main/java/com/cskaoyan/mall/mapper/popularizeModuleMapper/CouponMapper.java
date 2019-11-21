package com.cskaoyan.mall.mapper.popularizeModuleMapper;


import com.cskaoyan.mall.bean.generator.popularizeModule.Coupon;
import com.cskaoyan.mall.bean.generator.popularizeModule.CouponUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CouponMapper {

    List<Coupon> queryCouponList(@Param("sort") String sort,
                                 @Param("order") String order,
                                 @Param("name") String name,
                                 @Param("type") Integer type,
                                 @Param("status") Integer status);

    Coupon queryCouponById(int id);

    List<CouponUser> queryCouponUsers(@Param("couponId") Integer couponId,
                                      @Param("sort") String sort,
                                      @Param("order") String order,
                                      @Param("userId") Integer userId,
                                      @Param("status") Integer status);

    void updateCoupon(@Param("coupon") Coupon coupon);

    void deleteCoupon(int id);

    int createCoupon(@Param("coupon") Coupon coupon);

    List<Coupon> queryCoupons();

    void insertCouponUser(@Param("userId") int userId,
                          @Param("lessenTotalCoupon") Coupon lessenTotalCoupon);

    int checkUserCouponExits(int userId,int couponId);

    List<Integer> getMyCouponIds(@Param("userId") int userId,
                                 @Param("status") int status);

    List<Coupon> getMycouponList(@Param("couponIds") List<Integer> myCouponIds);

    Coupon queryCouponByCode(Integer code);

    List<Coupon> queryCouponByUserId(int userId);
}
