package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.jsonbean.PageSplit;

import java.util.Map;

public interface CollectService {
    Map<String, Object> findAll(PageSplit pageSplit);

    Map<String, Object> findCollectByCondition(PageSplit pageSplit);
}
