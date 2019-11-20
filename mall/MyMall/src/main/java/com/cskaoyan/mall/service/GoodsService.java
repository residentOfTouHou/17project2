package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.Goods;
import com.cskaoyan.mall.bean.generator.GoodsAlter;
import com.cskaoyan.mall.bean.jsonbean.GoodsData;
import com.cskaoyan.mall.bean.jsonbean.GoodsQueryParameters;

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

<<<<<<< HEAD


=======
    List<Goods> findAll();
>>>>>>> 06a48e41c585bd6a1578566548ad9d6838deeef9

    Goods queryGoodsByGoodsSn(String goodsSn);

    Goods queryGoodsByName(String name);

    Goods queryGoodsBySnAndName(GoodsAlter goods);

<<<<<<< HEAD
=======
    int queryGoodsCount();

    List<Goods> queryNewGoods();

    List<Goods> queryHotGoods();

>>>>>>> 06a48e41c585bd6a1578566548ad9d6838deeef9
}
