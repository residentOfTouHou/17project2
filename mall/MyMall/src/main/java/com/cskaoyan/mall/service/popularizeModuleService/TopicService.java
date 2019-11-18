package com.cskaoyan.mall.service.popularizeModuleService;



import com.cskaoyan.mall.bean.generator.popularizeModule.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> getTopics(Integer page,
                          Integer limit,
                          String title,
                          String subtitle,
                          String sort,
                          String order);

    int updateTopic(Topic topic);

    Topic getTopicById(Integer id);

    void deleteTopic(Topic topic);
}
