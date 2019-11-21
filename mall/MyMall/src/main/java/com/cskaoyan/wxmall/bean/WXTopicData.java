package com.cskaoyan.wxmall.bean;

import com.cskaoyan.mall.bean.generator.Goods;
import com.cskaoyan.mall.bean.generator.popularizeModule.Topic;
import lombok.Data;

import java.util.List;
@Data
public class WXTopicData {
    private Topic topic;
    private String[] goods;
    private List<Topic> data;
    private int errno;
    private String errmsg;
}
