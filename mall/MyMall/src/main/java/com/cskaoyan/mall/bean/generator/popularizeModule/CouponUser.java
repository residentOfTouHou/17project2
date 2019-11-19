package com.cskaoyan.mall.bean.generator.popularizeModule;

import lombok.Data;

import java.util.Date;

@Data
public class CouponUser {
    private Integer id;

    private Integer userId;

    private Integer couponId;

    private Short status;

    private Date usedTime;

    private Date startTime;

    private Date endTime;

    private Integer orderId;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;


}