package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.Feedback;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;

import java.util.Map;

public interface FeedbackService {
    Map<String, Object> findAll(PageSplit pageSplit);

    Map<String, Object> findFeedbackByCondition(PageSplit pageSplit);

    int insert(Feedback feedback);
}
