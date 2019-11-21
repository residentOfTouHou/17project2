package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.Collect;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;

import java.util.List;
import java.util.Map;

public interface CollectService {
    Map<String, Object> findAll(PageSplit pageSplit);

    Map<String, Object> findCollectByCondition(PageSplit pageSplit);

    Map<String, Object> findCollectByCon(PageSplit pageSplit);

    int  addCollection(Byte type, Integer valueId);

    List<Collect> findCollectByCondition(Byte type, Integer valueId);

    int deleteCollection(Byte type, Integer valueId);
}
