package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.Brand;
import com.cskaoyan.mall.bean.jsonbean.BrandVo;
import com.cskaoyan.mall.wxmall.bean.BrandData;

import java.util.List;

/**
 * @author 杨盛
 * @date 2019/11/16 16:26
 */
public interface BrandService {

    List<BrandVo> queryBrands();


    BrandData queryBrandsWx(int page, int size);

    Brand queryBrandById(int id);

    List<Brand> getAllBrands();

}
