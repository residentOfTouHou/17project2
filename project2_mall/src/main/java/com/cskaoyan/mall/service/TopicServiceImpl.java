package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.Topic;
import com.cskaoyan.mall.mapper.TopicMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService{

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
}
