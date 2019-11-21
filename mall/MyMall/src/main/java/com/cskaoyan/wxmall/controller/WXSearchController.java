package com.cskaoyan.wxmall.controller;


import com.cskaoyan.mall.bean.generator.Keyword;
import com.cskaoyan.mall.bean.generator.SearchHistory;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.HistoryService;
import com.cskaoyan.wxmall.bean.HistoryJson;
import com.cskaoyan.wxmall.bean.IndexData;
import com.cskaoyan.wxmall.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wx/search/")
public class WXSearchController {
    @Autowired
    private KeywordService keywordService;

    @Autowired
    private HistoryService historyService;

    @RequestMapping("/index")
    public IndexData index() {
        IndexData dataBean = new IndexData();

    //查找默认关键字
        List<Keyword> keyWordDefault = keywordService.findKeyWordDefault(true);
    //查找热门关键字
        Keyword keyword = null;
        if(!keyWordDefault.isEmpty()) {
          keyword = keyWordDefault.get(0);
        }
        IndexData.DataBean data = new IndexData.DataBean();
        data.setDefaultKeyword(keyword);
        List<Keyword> keyWordHot = keywordService.findKeyWordHot(true);
        data.setHotKeywordList(keyWordHot);
        List<SearchHistory> allHistory = historyService.findAllHistory();
        data.setHistoryKeywordList(allHistory);

        dataBean.setData(data);
        dataBean.setErrmsg("成功");
        dataBean.setErrno(0);
        return dataBean;
    }

    @RequestMapping("/clearhistory")
    public BaseReqVo clearHistory() {
        BaseReqVo baseReqVo = new BaseReqVo();
        historyService.clearAll();


        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @RequestMapping("/helper")
    public HistoryJson helper(String keyword) {
        HistoryJson historyJson = new HistoryJson();

        List<String> keywords = new ArrayList<>();
        List<SearchHistory> allHistory = historyService.findHistoryByKeyWord(keyword);
        for (SearchHistory searchHistory : allHistory) {
            String keyword1 = searchHistory.getKeyword();
            keywords.add(keyword1);
        }
        historyJson.setData(keywords);
        historyJson.setErrmsg("成功");
        historyJson.setErrno(0);
        return historyJson;
    }
}
