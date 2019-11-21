package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.Footprint;
import com.cskaoyan.mall.bean.generator.FootprintExample;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;
import com.cskaoyan.mall.mapper.FootprintMapper;
import com.cskaoyan.mall.service.FootprintService;
import com.cskaoyan.wxmall.bean.FootPrintBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class FootprintServiceImpl implements FootprintService {
    @Autowired
    private FootprintMapper footprintMapper;

    @Override
    public Map<String, Object> findAll(PageSplit pageSplit) {
        PageHelper.startPage(pageSplit.getPage(),pageSplit.getLimit());


        FootprintExample footprintExample = new FootprintExample();

//排序方式
        footprintExample.setOrderByClause(pageSplit.getSort() + " " + pageSplit.getOrder());


        List<Footprint> footprints = footprintMapper.selectByExample(footprintExample);
        PageInfo<Footprint> footprintPageInfo = new PageInfo<>(footprints);
        long total = footprintPageInfo.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("total", total);
        map.put("footprints", footprints);
        return map;

    }

    @Override
    public Map<String, Object> findFootprintByCondition(PageSplit pageSplit) {
        PageHelper.startPage(pageSplit.getPage(),pageSplit.getLimit());
        Integer goodsId = pageSplit.getGoodsId();
        Integer userId = pageSplit.getUserId();
        FootprintExample footprintExample = new FootprintExample();
//        升序降序
        footprintExample.setOrderByClause(pageSplit.getSort() + " " + pageSplit.getOrder());
        FootprintExample.Criteria criteria = footprintExample.createCriteria();
        if(goodsId!=null){
            criteria.andGoodsIdEqualTo(goodsId);
        }
        if (userId!=null){
            criteria.andUserIdEqualTo(userId);
        }
        List<Footprint> footprints = footprintMapper.selectByExample(footprintExample);

        PageInfo<Footprint> footprintPageInfo = new PageInfo<>(footprints);
        long total = footprintPageInfo.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("total", total);
        map.put("footprints", footprints);
        return map;
    }

    @Override
    public Map<String, Object> selectFootPrintBy(Integer page, Integer size,Integer id) {
        //分页
        PageHelper.startPage(page,size);

        List<FootPrintBean> footprintList = footprintMapper.selectFootprintBy(id);
        HashMap<String,Object> map = new HashMap<>();
        map.put("footprintList",footprintList);
        return map;
    }


}
