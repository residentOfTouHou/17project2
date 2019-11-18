package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.*;
import com.cskaoyan.mall.mapper.GoodsProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 杨盛
 * @date 2019/11/16 17:35
 */

@Service
public class GoodsProductServiceImpl implements GoodsProductService {

    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Override
    public int insertProducts(List<GoodsProductAlter> products, int goodsId) {
        int insert = goodsProductMapper.insertProducts(products, goodsId);
        return insert;
    }

    @Override
    public List<GoodsProductAlter> queryByGoodsId(Integer goodsId) {
        List<GoodsProductAlter> products = goodsProductMapper.queryByGoodsId(goodsId);
        return products;
    }

    @Override
    public int updateProducts(List<GoodsProductAlter> products) {
        int update = 0;
        for (GoodsProductAlter product : products) {
            int alter = goodsProductMapper.updateProducts(product);
            update += alter;
        }
        return update;
    }

    @Override
    public int deleteProducts(int goodsId) {
        GoodsProductExample example = new GoodsProductExample();
        example.createCriteria().andGoodsIdEqualTo(goodsId);
        int delete = goodsProductMapper.deleteByExample(example);
        return delete;
    }
}
