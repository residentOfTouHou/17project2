package com.cskaoyan.wxmall.bean;

import lombok.Data;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/19
 * @time 22:15
 */
@Data
public class SubmitOrderBean {
    private Integer cartId;

    private Integer addressId;

    private Integer couponId;

    private String message;

    private Integer grouponRulesId;

    private Integer grouponLinkId;
}
