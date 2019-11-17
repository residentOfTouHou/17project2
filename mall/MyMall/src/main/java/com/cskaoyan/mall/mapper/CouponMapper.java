package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.generator.Coupon;
import com.cskaoyan.mall.bean.generator.CouponExample;
import java.util.List;

import com.cskaoyan.mall.bean.generator.CouponUser;
import org.apache.ibatis.annotations.Param;

public interface CouponMapper {
    long countByExample(CouponExample example);

    int deleteByExample(CouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    List<Coupon> selectByExample(CouponExample example);

    Coupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByExample(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);

//    赵汉聪
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
}