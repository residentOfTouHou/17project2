package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.GoodsSpecification;

import java.util.List;

/**
 * @author 杨盛
 * @date 2019/11/16 17:09
 */
public interface GoodsSpecificationService {

    int insertSpecifications(List<GoodsSpecification> specifications, int goodsId);

    List<GoodsSpecification> queryByGoodsId(Integer goodsId);

    int updateSpecifications(List<GoodsSpecification> specifications);

    int deleteSpecifications(int goodsId);
}
