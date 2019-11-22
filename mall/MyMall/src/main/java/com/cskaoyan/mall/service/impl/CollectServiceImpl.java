package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.Collect;
import com.cskaoyan.mall.bean.generator.CollectExample;
import com.cskaoyan.mall.bean.generator.Goods;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;
import com.cskaoyan.mall.mapper.CollectMapper;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.service.CollectService;
import com.cskaoyan.wxmall.utils.UserIdUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
   private GoodsMapper goodsMapper;
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
        Integer limit = pageSplit.getLimit();
        if(size!=null){
            limit = size;
        }
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
       List<Object> objectList = new ArrayList<>();
        for (Collect collect : collects) {
            Map<String,Object> objects = new HashMap<>();
            Integer valueId1 = collect.getValueId();
            objects.put("valueId",valueId);
            objects.put("id",collect.getId());
            objects.put("type",collect.getType());

            Goods goods = goodsMapper.queryGoodsByGoodsSn(valueId1.toString());
            String brief = goods.getBrief();
            objects.put("brief",brief);
            String picUrl = goods.getPicUrl();
            objects.put("picUrl",picUrl);
            String name = goods.getName();
            objects.put("name",name);
           BigDecimal retailPrice = goods.getRetailPrice();
            objects.put("retailPrice",retailPrice);
            objectList.add(objects);
        }

        PageInfo<Collect> collectPageInfo = new PageInfo<>(collects);
        long total = collectPageInfo.getTotal();
//        获取总页数
        Long totalPages = (total + size -1) / size;
        Map<String,Object> map = new HashMap<>();
        map.put("total", total);
        map.put("collects", objectList);
        map.put("totalPages", totalPages);
        return map;
    }

    @Override
    public int addCollection(Byte type, Integer valueId) {
        System.out.println(type);
        System.out.println(valueId);
        CollectExample collectExample = new CollectExample();
        Collect collect = new Collect();
        Integer userId = UserIdUtils.getCurrentUserId();
        collect.setUserId(userId);
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
        if(type != null){
            criteria.andTypeEqualTo(type);
        }
        if(valueId != null){
            criteria.andValueIdEqualTo(valueId);
        }



        int i = collectMapper.deleteByExample(collectExample);
        return i;
    }
}
