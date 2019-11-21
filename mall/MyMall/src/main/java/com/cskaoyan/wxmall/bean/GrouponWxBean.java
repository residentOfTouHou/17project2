package com.cskaoyan.wxmall.bean;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class GrouponWxBean {
    BigDecimal groupon_price;
    GoodsWxBean goods;
    BigDecimal groupon_member;
}
