package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.SearchHistory;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;

import java.util.List;
import java.util.Map;

public interface HistoryService {

    Map<String, Object> findAll(PageSplit pageSplit);

    List<SearchHistory> findAllHistory();

    Map<String, Object> findHistoryByCondition(PageSplit pageSplit);

    void clearAll();
}
