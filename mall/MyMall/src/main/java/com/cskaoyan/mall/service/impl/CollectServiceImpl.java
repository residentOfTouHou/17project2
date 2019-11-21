package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.Collect;
import com.cskaoyan.mall.bean.generator.CollectExample;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;
import com.cskaoyan.mall.mapper.CollectMapper;
import com.cskaoyan.mall.service.CollectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;
    @Override
    public Map<String, Object> findAll(PageSplit pageSplit) {
        //分页操作
        PageHelper.startPage(pageSplit.getPage(),pageSplit.getLimit());



        CollectExample collectExample = new CollectExample();

//排序方式
        collectExample.setOrderByClause(pageSplit.getSort() + " " + pageSplit.getOrder());


        List<Collect> collects = collectMapper.selectByExample(collectExample);
        PageInfo<Collect> collectPageInfo = new PageInfo<>(collects);
        long total = collectPageInfo.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("total", total);
        map.put("collects", collects);
        return map;

    }

    @Override
    public Map<String, Object> findCollectByCondition(PageSplit pageSplit) {
        Integer page = pageSplit.getPage();
        Integer size = pageSplit.getSize();
        Integer limit = size;
        PageHelper.startPage(page, limit);
        Integer valueId = pageSplit.getValueId();
        Integer userId = pageSplit.getUserId();
        Byte type = pageSplit.getType();
        CollectExample collectExample = new CollectExample();
//        升序降序
        collectExample.setOrderByClause(pageSplit.getSort() + " " + pageSplit.getOrder());
        CollectExample.Criteria criteria = collectExample.createCriteria();
        if(valueId!=null){
            criteria.andValueIdEqualTo(valueId);
        }
        if (userId!=null){
            criteria.andUserIdEqualTo(userId);
        }

        if (type!=null){
            criteria.andTypeEqualTo(type);
        }
        List<Collect> collects = collectMapper.selectByExample(collectExample);

        PageInfo<Collect> collectPageInfo = new PageInfo<>(collects);
        long total = collectPageInfo.getTotal();

        Map<String,Object> map = new HashMap<>();
        map.put("total", total);
        map.put("collects", collects);

        return map;
    }



    @Override
    public Map<String, Object> findCollectByCon(PageSplit pageSplit) {
        Integer page = pageSplit.getPage();
        Integer size = pageSplit.getSize();
        PageHelper.startPage(page, size);
        Integer valueId = pageSplit.getValueId();
        Integer userId = pageSplit.getUserId();
        Byte type = pageSplit.getType();
        CollectExample collectExample = new CollectExample();
//        升序降序
//        collectExample.setOrderByClause(pageSplit.getSort() + " " + pageSplit.getOrder());
        CollectExample.Criteria criteria = collectExample.createCriteria();
        if(valueId!=null){
            criteria.andValueIdEqualTo(valueId);
        }
        if (userId!=null){
            criteria.andUserIdEqualTo(userId);
        }

        if (type!=null){
            criteria.andTypeEqualTo(type);
        }
        List<Collect> collects = collectMapper.selectByExample(collectExample);

        PageInfo<Collect> collectPageInfo = new PageInfo<>(collects);
        long total = collectPageInfo.getTotal();
//        获取总页数
        Long totalPages = (total + size -1) / size;
        Map<String,Object> map = new HashMap<>();
        map.put("total", total);
        map.put("collects", collects);
        map.put("totalPages", totalPages);
        return map;
    }

    @Override
    public int addCollection(Byte type, Integer valueId) {
        CollectExample collectExample = new CollectExample();
        Collect collect = new Collect();
//        有问题
        collect.setUserId(1);
        collect.setValueId(valueId);
        collect.setType(type);
        collect.setAddTime(new Date());
        collect.setUpdateTime(new Date() );
        collect.setDeleted(true);

        int i = collectMapper.insertSelective(collect);

        return i;
    }

    @Override
    public List<Collect> findCollectByCondition(Byte type, Integer valueId) {
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        if(valueId!=null){
            criteria.andValueIdEqualTo(valueId);
        }
        if (type!=null){
            criteria.andTypeEqualTo(type);
        }
        List<Collect> collects = collectMapper.selectByExample(collectExample);
        return collects;
    }

    @Override
    public int deleteCollection(Byte type, Integer valueId) {
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andTypeEqualTo(type).andValueIdEqualTo(valueId);

        int i = collectMapper.deleteByExample(collectExample);
        return i;
    }
}
