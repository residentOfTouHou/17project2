package com.cskaoyan.wxmall.bean;

import lombok.Data;

/**
 * @author 杨盛
 * @date 2019/11/20 16:33
 */

@Data
public class CartTotal {

    int goodsTotal;

    int checkedGoodsCount;

    double goodsAmount;

    double checkedGoodsAmount;
}
