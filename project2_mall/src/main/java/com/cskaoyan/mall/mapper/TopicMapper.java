package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.generator.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TopicMapper {

    List<Topic> getTopics(@Param("title") String title,
                          @Param("subtitle")String subtitle,
                          @Param("sort")String sort,
                          @Param("order")String order);

    int updateTopic(@Param("topic") Topic topic);

    Topic getTopicById(Integer id);

    void deleteTopic(int id);
}
