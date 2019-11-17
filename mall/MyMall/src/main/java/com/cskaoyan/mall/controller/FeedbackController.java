package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.generator.Feedback;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.bean.jsonbean.FeedbackData;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;
import com.cskaoyan.mall.service.FeedbackService;
import com.cskaoyan.mall.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/feedback/")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @RequestMapping("/list")
    public BaseReqVo list(PageSplit pageSplit){
        FeedbackData data = new FeedbackData();
        BaseReqVo baseReqVo = new BaseReqVo();
        Integer id = pageSplit.getId();
        String username = pageSplit.getUsername();
        if (id==null && StringUtil.isBlank(username)){
            Map<String, Object> allFeedback = feedbackService.findAll(pageSplit);
            Long total = (Long) allFeedback.get("total");
            List<Feedback> items = (List<Feedback>) allFeedback.get("feedbacks");

            data.setTotal(total);
            data.setItems(items);
        }else {
            Map<String, Object> feedbackByCondition = feedbackService.findFeedbackByCondition(pageSplit);
            Long total = (Long) feedbackByCondition.get("total");
            List<Feedback> items = (List<Feedback>) feedbackByCondition.get("feedbacks");

            data.setTotal(total);
            data.setItems(items);
        }


        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(data);
        return baseReqVo;
    }
}
