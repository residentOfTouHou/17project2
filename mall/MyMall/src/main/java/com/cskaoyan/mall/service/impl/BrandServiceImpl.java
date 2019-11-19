package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.Brand;
import com.cskaoyan.mall.bean.generator.BrandExample;
import com.cskaoyan.mall.bean.jsonbean.BrandVo;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.cskaoyan.mall.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 杨盛
 * @date 2019/11/16 16:27
 */

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandMapper brandMapper;


    @Override
    public List<BrandVo> queryBrands() {
        List<BrandVo> brandList = brandMapper.queryBrands();
        return brandList;
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandMapper.selectByExample(new BrandExample());
    }
}
