package com.cskaoyan.mall.controller.popularizeModuleController;


import com.cskaoyan.mall.bean.generator.popularizeModule.Topic;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;

import com.cskaoyan.mall.service.popularizeModuleService.TopicService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/topic")
public class TopicController {

    @Autowired
    TopicService topicService;

    @RequestMapping("list")
    public BaseReqVo getTopicList(Integer page,
                                  Integer limit,
                                  String title,
                                  String subtitle,
                                  String sort,
                                  String order){
        List<Topic> topics = topicService.getTopics(page,limit,title,subtitle,sort,order);
        Map<String,Object> topicMap = new HashMap<>();
        PageInfo<Topic> topicPageInfo = new PageInfo<>(topics);
        long total = topicPageInfo.getTotal();
        topicMap.put("total",total);
        topicMap.put("items",topics);
        return new BaseReqVo(topicMap,"成功",0);
    }

    @RequestMapping("create")
    public BaseReqVo createTopic(@RequestBody Topic topic){
        Topic insertedTopic = topicService.createTopic(topic);
        return new BaseReqVo(insertedTopic,"成功",0);
    }

    @RequestMapping("update")
    public BaseReqVo updateTopic(@RequestBody Topic topic){
        int flag = topicService.updateTopic(topic);
        Topic updatedTopic = null;
        if(flag == 1){
            updatedTopic = topicService.getTopicById(topic.getId());
        }
        return new BaseReqVo(updatedTopic,"成功",0);
    }

    @RequestMapping("delete")
    public BaseReqVo deleteTopic(@RequestBody Topic topic){
        topicService.deleteTopic(topic);
        return new BaseReqVo("成功",0);
    }



}
