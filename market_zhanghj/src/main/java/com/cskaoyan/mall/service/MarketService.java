package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.Brand;
import com.cskaoyan.mall.bean.jsonbean.RegionSegment;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/15
 * @time 19:48
 */
public interface MarketService {
    List<RegionSegment> queryRegion();

    List<Brand> queryBrand(Integer page, Integer list, String sort, String order);

    List<Brand> queryBrand(Integer page, Integer list, String id, String name, String sort, String order);
}
