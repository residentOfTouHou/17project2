package com.cskaoyan.mall.bean.jsonbean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class StatOrder {
    private Double amount;     //订单总额
    private int orders;     //订单量
    private int customers;  //下单用户
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date day;   //添加时间
    private Double pcr;        //客单价
}
