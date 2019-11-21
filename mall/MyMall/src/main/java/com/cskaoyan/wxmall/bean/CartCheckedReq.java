package com.cskaoyan.wxmall.bean;

import lombok.Data;

import java.util.List;

/**
 * @author 杨盛
 * @date 2019/11/20 20:04
 */
@Data
public class CartCheckedReq {

    List<Integer> productIds;

    boolean isChecked;

}
