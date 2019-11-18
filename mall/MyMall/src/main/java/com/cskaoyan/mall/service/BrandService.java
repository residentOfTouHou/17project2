package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.Brand;
import com.cskaoyan.mall.bean.jsonbean.BrandVo;

import java.util.List;

/**
 * @author 杨盛
 * @date 2019/11/16 16:26
 */
public interface BrandService {

    List<BrandVo> queryBrands();
}
