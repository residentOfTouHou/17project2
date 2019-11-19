package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.GoodsSpecification;
import com.cskaoyan.mall.bean.generator.GoodsSpecificationExample;
import com.cskaoyan.mall.mapper.GoodsSpecificationMapper;
import com.cskaoyan.mall.service.GoodsSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 杨盛
 * @date 2019/11/16 17:11
 */

@Service
public class GoodsSpecificationServiceImpl implements GoodsSpecificationService {

    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Override
    public int insertSpecifications(List<GoodsSpecification> specifications, int goodsId) {
        for (GoodsSpecification specification : specifications) {
            specification.setGoodsId(goodsId);
        }
        int insert = goodsSpecificationMapper.insertSpecifications(specifications);
        return insert;
    }

    @Override
    public List<GoodsSpecification> queryByGoodsId(Integer goodsId) {
        GoodsSpecificationExample example = new GoodsSpecificationExample();
        example.createCriteria().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        List<GoodsSpecification> specifications = goodsSpecificationMapper.selectByExample(example);
        return specifications;
    }

    @Override
    public int updateSpecifications(List<GoodsSpecification> specifications) {
        int update = 0;
        for (GoodsSpecification specification : specifications) {
            int alter = goodsSpecificationMapper.updateSpecifications(specification);
            update += alter;
        }
        return update;
    }

    @Override
    public int deleteSpecifications(int goodsId) {
        GoodsSpecificationExample example = new GoodsSpecificationExample();
        example.createCriteria().andGoodsIdEqualTo(goodsId);
        int delete = goodsSpecificationMapper.deleteByExample(example);
        return delete;
    }
}
