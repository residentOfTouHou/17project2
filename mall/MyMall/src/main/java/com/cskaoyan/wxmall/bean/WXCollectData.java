package com.cskaoyan.wxmall.bean;

import com.cskaoyan.mall.bean.generator.Collect;
import lombok.Data;

import java.util.List;

@Data
public class WXCollectData {
    private Long total;

    private Long totalPages;

    private List<Collect> collectList;
}
