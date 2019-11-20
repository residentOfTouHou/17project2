package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.GoodsProduct;
import com.cskaoyan.mall.bean.generator.GoodsProductAlter;

import java.util.List;

/**
 * @author 杨盛
 * @date 2019/11/16 17:35
 */
public interface GoodsProductService {

    int insertProducts(List<GoodsProductAlter> products, int goodsId);

    List<GoodsProductAlter> queryByGoodsId(Integer id);

    int updateProducts(List<GoodsProductAlter> products);

    int deleteProducts(int goodsId);

    GoodsProduct queryProductById(int productId);
}
