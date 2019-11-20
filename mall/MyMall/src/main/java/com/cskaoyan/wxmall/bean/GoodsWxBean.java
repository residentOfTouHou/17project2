package com.cskaoyan.wxmall.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsWxBean {
    Integer id;
    String name;
    String brief;
    String picUrl;
    BigDecimal counterPrice;
    BigDecimal retailPrice;
}
