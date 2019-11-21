package com.cskaoyan.wxmall.bean;

import com.cskaoyan.mall.bean.generator.Brand;
import lombok.Data;

import java.util.List;

/**
 * @author 杨盛
 * @date 2019/11/19 22:37
 */

@Data
public class BrandData {

    int totalPages;

    List<Brand> brandList;
}
