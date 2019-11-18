package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.jsonbean.PageSplit;

import java.util.Map;

public interface FootprintService {
    Map<String, Object> findAll(PageSplit pageSplit);

    Map<String, Object> findFootprintByCondition(PageSplit pageSplit);
}