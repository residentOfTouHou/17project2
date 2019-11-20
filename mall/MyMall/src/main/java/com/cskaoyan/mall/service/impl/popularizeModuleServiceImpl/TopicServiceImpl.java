package com.cskaoyan.mall.service.impl.popularizeModuleServiceImpl;


import com.cskaoyan.mall.bean.generator.popularizeModule.Topic;
import com.cskaoyan.mall.mapper.popularizeModuleMapper.TopicMapper;
import com.cskaoyan.mall.service.popularizeModuleService.TopicService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicMapper topicMapper;

    @Override
    public List<Topic> getTopics(Integer page,
                                 Integer limit,
                                 String title,
                                 String subtitle,
                                 String sort,
                                 String order) {
        PageHelper.startPage(page,limit);

        if(title != null){
            title = "%" + title + "%";
        }
        if(subtitle != null){
            subtitle = "%" + subtitle + "%";
        }

        List<Topic> topics = topicMapper.getTopics(title,subtitle,sort,order);
        return topics;
    }

    @Override
    public int updateTopic(Topic topic) {
        topicMapper.updateTopic(topic);
        return 0;
    }

    @Override
    public Topic getTopicById(Integer id) {
        Topic updatedTopic = topicMapper.getTopicById(id);
        return updatedTopic;
    }

    @Override
    public void deleteTopic(Topic topic) {
        int id = topic.getId();
        topicMapper.deleteTopic(id);
    }

    @Override
    public Topic createTopic(Topic topic) {
        int flag = topicMapper.createTopic(topic);
        int topicId = topic.getId();
        if(flag == 1){
            Topic insertedTopic = topicMapper.getTopicById(topicId);
            return insertedTopic;
        }
        return null;
    }
}
