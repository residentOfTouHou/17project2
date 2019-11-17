package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.SearchHistory;
import com.cskaoyan.mall.bean.generator.SearchHistoryExample;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;
import com.cskaoyan.mall.mapper.SearchHistoryMapper;
import com.cskaoyan.mall.service.HistoryService;
import com.cskaoyan.mall.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private SearchHistoryMapper historyMapper;
    @Override
    public Map<String, Object> findAll(PageSplit pageSplit) {
        //分页操作
        PageHelper.startPage(pageSplit.getPage(),pageSplit.getLimit());


        SearchHistoryExample searchHistoryExample = new SearchHistoryExample();

//排序方式
        searchHistoryExample.setOrderByClause(pageSplit.getSort() + " " + pageSplit.getOrder());


        List<SearchHistory> searchHistories = historyMapper.selectByExample(searchHistoryExample);
        PageInfo<SearchHistory> searchHistoryPageInfo = new PageInfo<>(searchHistories);
        long total = searchHistoryPageInfo.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("total", total);
        map.put("searchHistories", searchHistories);
        return map;

    }

    @Override
    public Map<String, Object> findHistoryByCondition(PageSplit pageSplit) {

        PageHelper.startPage(pageSplit.getPage(),pageSplit.getLimit());
        String keyword = pageSplit.getKeyword();
        Integer userId = pageSplit.getUserId();
        SearchHistoryExample searchHistoryExample = new SearchHistoryExample();

        SearchHistoryExample.Criteria criteria = searchHistoryExample.createCriteria();
        if(!StringUtil.isBlank(keyword)){
            criteria.andKeywordLike("%" +keyword + "%");
        }
        if (userId!=null){
            criteria.andUserIdEqualTo(userId);
        }
        List<SearchHistory> searchHistories = historyMapper.selectByExample(searchHistoryExample);
//               升序降序
        searchHistoryExample.setOrderByClause(pageSplit.getSort() + " " + pageSplit.getOrder());

        PageInfo<SearchHistory> searchHistoryPageInfo = new PageInfo<>(searchHistories);
        long total = searchHistoryPageInfo.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("total", total);
        map.put("searchHistories", searchHistories);
        return map;
    }
}
