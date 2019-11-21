package com.cskaoyan.mall.bean.generator.popularizeModule;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CouponUser {
    private Integer id;

    private Integer userId;

    private Integer couponId;

    private Short status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date usedTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private Integer orderId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Boolean deleted;


}