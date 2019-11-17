package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.generator.SearchHistory;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.bean.jsonbean.HistoryData;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;
import com.cskaoyan.mall.service.HistoryService;
import com.cskaoyan.mall.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/history/")
public class HistoryController {
    @Autowired
    private HistoryService historyService;
    @RequestMapping("/list")
    public BaseReqVo list(PageSplit pageSplit){
        HistoryData data = new HistoryData();
        BaseReqVo baseReqVo = new BaseReqVo();
        Integer userId = pageSplit.getUserId();
        String keyword = pageSplit.getKeyword();
        if (userId==null && StringUtils.isBlank(keyword)){
            Map<String, Object> allHistory= historyService.findAll(pageSplit);
            Long total = (Long) allHistory.get("total");
            List<SearchHistory> items = (List<SearchHistory>) allHistory.get("searchHistories");

            data.setTotal(total);
            data.setItems(items);
        }else {
            Map<String, Object> historyByCondition = historyService.findHistoryByCondition(pageSplit);
            Long total = (Long) historyByCondition.get("total");
            List<SearchHistory> items = (List<SearchHistory>) historyByCondition.get("searchHistories");

            data.setTotal(total);
            data.setItems(items);
        }


        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(data);
        return baseReqVo;
    }
}
