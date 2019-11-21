package com.cskaoyan.wxmall.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/20
 * @time 21:58
 */
@Data
public class OrderInfoBean {
    private String consignee;

    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    private String orderSn;

    private BigDecimal actualPrice;

    private String mobile;

    private String orderStatusTest;

    private BigDecimal goodsPrice;

    private BigDecimal couponPrice;

    private Integer id;

    private BigDecimal freightPrice;

    private Map<String,Boolean> handleOption;
}
