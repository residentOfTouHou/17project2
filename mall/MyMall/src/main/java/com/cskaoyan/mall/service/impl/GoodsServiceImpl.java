package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.Goods;
import com.cskaoyan.mall.bean.generator.GoodsAlter;
import com.cskaoyan.mall.bean.generator.GoodsExample;
import com.cskaoyan.mall.bean.generator.GoodsProductExample;
import com.cskaoyan.mall.bean.jsonbean.GoodsData;
import com.cskaoyan.mall.bean.jsonbean.GoodsQueryParameters;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 杨盛
 * @date 2019/11/15 19:36
 */

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public GoodsData queryGoods(GoodsQueryParameters goodsQueryParameters) {
        PageHelper.startPage(goodsQueryParameters.getPage(), goodsQueryParameters.getLimit());
        if (goodsQueryParameters.getName() != null) {
            goodsQueryParameters.setName("%" + goodsQueryParameters.getName() + "%");
        }
        List<GoodsAlter> goodsAlters = goodsMapper.queryGoodsAndSort(goodsQueryParameters);
        PageInfo<GoodsAlter> goodsPageInfo = new PageInfo<>(goodsAlters);
        long total = goodsPageInfo.getTotal();
        GoodsData goodsData = new GoodsData();
        goodsData.setTotal(total);
        goodsData.setItems(goodsAlters);
        return goodsData;
    }

    @Override
    public int insertGoods(GoodsAlter goods) {
        int insert = goodsMapper.insertGoodAlter(goods);
        return insert;
    }

    @Override
    public GoodsAlter queryGoodsById(int id) {
        GoodsAlter goodsAlter = goodsMapper.queryGoodsById(id);
        return goodsAlter;
    }

    @Override
    public int updateGoods(GoodsAlter goods) {
        int update = goodsMapper.updateGoodAlter(goods);
        return update;
    }

    @Override
    public int deleteGoods(Integer id) {
        GoodsExample example = new GoodsExample();
        int delete = goodsMapper.deleteByPrimaryKey(id);
        return delete;
    }

    @Override
    public Goods queryGoodsByGoodsSn(String goodsSn) {
       Goods goods = goodsMapper.queryGoodsByGoodsSn(goodsSn);
       return goods;
    }

    @Override
    public Goods queryGoodsByName(String name) {
        Goods goods = goodsMapper.queryGoodsByName(name);
        return goods;
    }

    @Override
    public Goods queryGoodsBySnAndName(GoodsAlter goods) {
        Goods queryGoods = goodsMapper.queryGoodsBySnAndName(goods);
        return queryGoods;
    }
}
