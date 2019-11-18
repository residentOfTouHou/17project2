package com.cskaoyan.mall.bean.jsonbean;

import com.cskaoyan.mall.bean.generator.GoodsAlter;
import com.cskaoyan.mall.bean.generator.GoodsAttribute;
import com.cskaoyan.mall.bean.generator.GoodsProductAlter;
import com.cskaoyan.mall.bean.generator.GoodsSpecification;
import lombok.Data;

import java.util.List;

/**
 * @author 杨盛
 * @date 2019/11/16 22:24
 */

@Data
public class GoodsCreate {

    GoodsAlter goods;

    List<GoodsSpecification> specifications;

    List<GoodsProductAlter> products;

    List<GoodsAttribute> attributes;
}
