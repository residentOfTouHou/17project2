package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.GoodsAttribute;
import com.cskaoyan.mall.bean.generator.GoodsAttributeExample;
import com.cskaoyan.mall.mapper.GoodsAttributeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 杨盛
 * @date 2019/11/16 17:37
 */

@Service
public class GoodsAttributeServiceImpl implements GoodsAttributeService {

    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;

    @Override
    public int insertProducts(List<GoodsAttribute> attributes, int goodsId) {
        for (GoodsAttribute attribute : attributes) {
            attribute.setGoodsId(goodsId);
        }
        int insert = goodsAttributeMapper.insertAttributes(attributes);
        return insert;
    }

    @Override
    public List<GoodsAttribute> queryByGoodsId(int goodsId) {
        GoodsAttributeExample example = new GoodsAttributeExample();
        example.createCriteria().andGoodsIdEqualTo(goodsId);
        List<GoodsAttribute> attributes = goodsAttributeMapper.selectByExample(example);
        return attributes;
    }

    @Override
    public int updateAttributes(List<GoodsAttribute> attributes) {
        int update = 0;
        for (GoodsAttribute attribute : attributes) {
             int alter = goodsAttributeMapper.updateAttributes(attribute);
             update += alter;
        }

        return update;
    }

    @Override
    public int deleteAttributes(int goodsId) {
        GoodsAttributeExample example = new GoodsAttributeExample();
        example.createCriteria().andGoodsIdEqualTo(goodsId);
        int delete = goodsAttributeMapper.deleteByExample(example);
        return delete;
    }
}
