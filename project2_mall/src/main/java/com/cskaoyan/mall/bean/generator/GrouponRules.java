package com.cskaoyan.mall.bean.generator;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class GrouponRules {
    private Integer id;

    private Integer goodsId;

    private String goodsName;

    private String picUrl;

    private BigDecimal discount;

    private Integer discountMember;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;

    private Boolean deleted;
}