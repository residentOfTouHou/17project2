package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.Brand;
import com.cskaoyan.mall.bean.generator.BrandExample;
import com.cskaoyan.mall.bean.jsonbean.BrandVo;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.cskaoyan.mall.service.BrandService;
import com.cskaoyan.wxmall.bean.BrandData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    public BrandData queryBrandsWx(int page, int size) {
        PageHelper.startPage(page, size);
        BrandExample example = new BrandExample();
        example.createCriteria().andDeletedEqualTo(false);
        List<Brand> brandList = brandMapper.selectByExample(example);
        PageInfo<Brand> goodsPageInfo = new PageInfo<>(brandList);
        long total = goodsPageInfo.getTotal();
        int totalPages = (int) Math.ceil((double) total / size);
        BrandData brandData = new BrandData();
        brandData.setTotalPages(totalPages);
        brandData.setBrandList(brandList);
        return brandData;
    }

    @Override
    public Brand queryBrandById(int id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        return brand;
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandMapper.selectByExample(new BrandExample());
    }
}
