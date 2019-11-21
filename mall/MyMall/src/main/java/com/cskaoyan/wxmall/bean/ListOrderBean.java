package com.cskaoyan.wxmall.bean;

import com.cskaoyan.mall.bean.generator.OrderGoods;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/20
 * @time 15:25
 */
@Data
public class ListOrderBean {
    private String orderStatusTest;

    private boolean isGroupin;

    private String orderSn;

    private BigDecimal acturalPrice;

    private List<OrderGoods> goodsList;

    private Integer id;

    private Map<String,Boolean> handleOption;
}
