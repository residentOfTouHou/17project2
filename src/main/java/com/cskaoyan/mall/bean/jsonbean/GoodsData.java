package com.cskaoyan.mall.bean.jsonbean;

import com.cskaoyan.mall.bean.generator.GoodsAlter;
import lombok.Data;

import java.util.List;

/**
 * @author 杨盛
 * @date 2019/11/15 20:36
 */

@Data
public class GoodsData {

    Long total;

    List<GoodsAlter> items;
}

