package com.cskaoyan.wxmall.bean;

import com.cskaoyan.mall.bean.generator.Goods;
import com.cskaoyan.mall.bean.generator.popularizeModule.Groupon;
import com.cskaoyan.mall.bean.generator.popularizeModule.GrouponRules;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MyGroupon {
    String orderStatusText;
    String creator;
    Groupon groupon;
    Integer orderId;
    String orderSn;
    BigDecimal actualPrice;
    Integer joinerCount;
    List<GrouponGoodsWxBean> goods;
    GrouponRules rules;
    Integer id;
    boolean isCreator;
    HandleOption handleOption;
    Integer count;

}
