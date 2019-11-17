package com.cskaoyan.mall.bean.jsonbean;

import com.cskaoyan.mall.bean.generator.SearchHistory;
import com.cskaoyan.mall.bean.generator.User;
import lombok.Data;

import java.util.List;

@Data
public class HistoryData {
    private Long total;

    private List<SearchHistory> items;
}
