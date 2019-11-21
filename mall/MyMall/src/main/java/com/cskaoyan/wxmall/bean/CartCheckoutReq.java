package com.cskaoyan.wxmall.bean;

import lombok.Data;

/**
 * @author 杨盛
 * @date 2019/11/21 15:25
 */

@Data
public class CartCheckoutReq {

    int cartId;

    int addressId;

    int couponId;

    int grouponRulesId;
}
