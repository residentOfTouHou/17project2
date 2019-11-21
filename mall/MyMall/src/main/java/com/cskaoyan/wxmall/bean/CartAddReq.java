package com.cskaoyan.wxmall.bean;

import lombok.Data;

/**
 * @author 杨盛
 * @date 2019/11/20 11:28
 */

@Data
public class CartAddReq {

    int goodsId;

    int number;

    int productId;

}
