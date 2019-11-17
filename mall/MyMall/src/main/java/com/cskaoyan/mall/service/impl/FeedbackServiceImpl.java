package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.Feedback;
import com.cskaoyan.mall.bean.generator.FeedbackExample;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;
import com.cskaoyan.mall.mapper.FeedbackMapper;
import com.cskaoyan.mall.service.FeedbackService;
import com.cskaoyan.mall.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;
    @Override
    public Map<String, Object> findAll(PageSplit pageSplit) {
        //分页操作
        PageHelper.startPage(pageSplit.getPage(),pageSplit.getLimit());


        FeedbackExample feedbackExample = new FeedbackExample();

//排序方式
        feedbackExample.setOrderByClause(pageSplit.getSort() + " " + pageSplit.getOrder());


        List<Feedback> feedbacks = feedbackMapper.selectByExample(feedbackExample);
        PageInfo<Feedback> feedbackPageInfo = new PageInfo<>(feedbacks);
        long total = feedbackPageInfo.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("total", total);
        map.put("feedbacks", feedbacks);
        return map;

    }


    @Override
    public Map<String, Object> findFeedbackByCondition(PageSplit pageSplit) {

        PageHelper.startPage(pageSplit.getPage(),pageSplit.getLimit());
        String username = pageSplit.getUsername();
        Integer id = pageSplit.getId();
        FeedbackExample feedbackExample = new FeedbackExample();

        FeedbackExample.Criteria criteria = feedbackExample.createCriteria();
        if(!StringUtil.isBlank(username)){
            criteria.andUsernameLike("%" +username + "%");
        }
        if (id!=null){
            criteria.andIdEqualTo(id);
        }
        List<Feedback> feedbacks = feedbackMapper.selectByExample(feedbackExample);
//               升序降序
        feedbackExample.setOrderByClause(pageSplit.getSort() + " " + pageSplit.getOrder());

        PageInfo<Feedback> feedbackPageInfo = new PageInfo<>(feedbacks);
        long total = feedbackPageInfo.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("total", total);
        map.put("feedbacks", feedbacks);
        return map;
    }
}
