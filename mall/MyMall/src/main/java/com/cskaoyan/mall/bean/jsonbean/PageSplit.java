package com.cskaoyan.mall.bean.jsonbean;

import lombok.Data;

@Data
public class PageSplit {
    private Integer page;

    private Integer limit;

    private String sort;

    private String order;
}
