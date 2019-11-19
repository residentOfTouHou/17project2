package com.cskaoyan.mall.bean.generator.popularizeModule;

import com.cskaoyan.mall.bean.generator.GoodsAlter;
import lombok.Data;

@Data
public class ListRecord {
    GoodsAlter goodsAlter;
    Groupon groupon;
    GrouponRules grouponRules;

}
