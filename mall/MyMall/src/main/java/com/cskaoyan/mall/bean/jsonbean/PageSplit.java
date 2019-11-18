package com.cskaoyan.mall.bean.jsonbean;

import lombok.Data;

@Data
public class PageSplit {
    private Integer page;

    private Integer limit;

    private String sort;

    private String order;

    private String username;

    private String mobile;

    private Integer userId;

    private String name;

    private Integer valueId;

    private Integer goodsId;

    private String keyword;

    private Integer id;
}
