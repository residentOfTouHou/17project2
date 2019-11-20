package com.cskaoyan.mall.bean.generator.popularizeModule;

import com.cskaoyan.mall.bean.generator.GoodsAlter;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class ListRecord {

    GoodsAlter goods;

    Groupon groupon;

    GrouponRules rules;

    List<SubGroupons> subGroupons;



    public ListRecord() {
    }

    public ListRecord(GoodsAlter goods, Groupon groupon, GrouponRules rules, List<SubGroupons> subGroupons) {
        this.goods = goods;
        this.groupon = groupon;
        this.rules = rules;
        this.subGroupons = subGroupons;
    }
}
