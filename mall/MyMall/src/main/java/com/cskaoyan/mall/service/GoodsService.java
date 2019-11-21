package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.Goods;
import com.cskaoyan.mall.bean.generator.GoodsAlter;
import com.cskaoyan.mall.bean.jsonbean.GoodsData;
import com.cskaoyan.mall.bean.jsonbean.GoodsQueryParameters;

import java.util.HashMap;
import java.util.List;


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

    List<Goods> findAll();

    Goods queryGoodsByGoodsSn(String goodsSn);

    Goods queryGoodsByName(String name);

    Goods queryGoodsBySnAndName(GoodsAlter goods);

    int queryGoodsCount();

    List<Goods> queryNewGoods();

    List<Goods> queryHotGoods();

    List<Goods> queryGoodsList(Integer categoryId, Integer page, Integer size);

    HashMap<String, Object> queryGoodsDetail(Integer id,Integer userId);

    HashMap<String, Object> queryGoodsRelated(Integer id);

}
