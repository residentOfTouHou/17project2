package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.GoodsAttribute;

import java.util.List;

/**
 * @author 杨盛
 * @date 2019/11/16 17:36
 */
public interface GoodsAttributeService {

    int insertProducts(List<GoodsAttribute> attributes, int goodsId);

    List<GoodsAttribute> queryByGoodsId(int goodsId);

    int updateAttributes(List<GoodsAttribute> attributes);

    int deleteAttributes(int goodsId);
}
