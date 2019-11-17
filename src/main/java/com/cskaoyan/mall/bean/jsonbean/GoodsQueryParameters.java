package com.cskaoyan.mall.bean.jsonbean;

import lombok.Data;

/**
 * @author 杨盛
 * @date 2019/11/15 19:29
 */

@Data
public class GoodsQueryParameters {

    Integer page;

    Integer limit;

    String sort;

    String order;

    Integer goodsSn;

    String name;
}
