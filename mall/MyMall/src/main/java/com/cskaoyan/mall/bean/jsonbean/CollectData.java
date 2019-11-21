package com.cskaoyan.mall.bean.jsonbean;

import com.cskaoyan.mall.bean.generator.Collect;
import lombok.Data;

import java.util.List;

@Data
public class CollectData {
    private Long total;

    private Long totalPages;

    private List<Collect> items;
}
