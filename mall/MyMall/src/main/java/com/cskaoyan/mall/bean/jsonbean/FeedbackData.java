package com.cskaoyan.mall.bean.jsonbean;

import com.cskaoyan.mall.bean.generator.Feedback;
import lombok.Data;

import java.util.List;

@Data
public class FeedbackData {
    private Long total;

    private List<Feedback> items;
}
