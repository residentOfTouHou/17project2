package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.Goods;
import com.cskaoyan.mall.bean.generator.GoodsAlter;
import com.cskaoyan.mall.bean.jsonbean.GoodsData;
import com.cskaoyan.mall.bean.jsonbean.GoodsQueryParameters;



/**
 * @author 杨盛
 * @date 2019/11/15 19:34
 */
public interface GoodsService {

    GoodsData queryGoods(GoodsQueryParameters goodsQueryParameters);

    int insertGoods(GoodsAlter goods);

    GoodsAlter queryGoodsById(int id);

    int updateGoods(GoodsAlter goods);

    int deleteGoods(Integer id);
}