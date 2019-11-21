package com.cskaoyan.wxmall.controller;


import com.cskaoyan.mall.bean.generator.popularizeModule.Topic;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.popularizeModuleService.TopicService;
import com.cskaoyan.wxmall.bean.WXTopicData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/topic/")
public class WXTopicController {
    @Autowired
    private TopicService topicService;

    @RequestMapping("/detail")
    public BaseReqVo detail(Integer id) {
        WXTopicData data = new WXTopicData();
        BaseReqVo baseReqVo = new BaseReqVo();

        Topic topic = topicService.getTopicById(id);

        data.setTopic(topic);
        String[] goods = null;
        if (topic != null) {
            goods = topic.getGoods();
        }

        data.setGoods(goods);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(data);
        return baseReqVo;
    }

    @RequestMapping("/related")
    public WXTopicData related(Integer id) {
        WXTopicData data = new WXTopicData();


        List<Topic> allTopics = topicService.getAllTopics();
       data.setData(allTopics);
        data.setErrno(0);
        data.setErrmsg("成功");

        return data;
    }
}
