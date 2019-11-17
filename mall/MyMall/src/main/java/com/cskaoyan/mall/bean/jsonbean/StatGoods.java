package com.cskaoyan.mall.bean.jsonbean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class StatGoods {
    private Double amount;
    private int orders;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date day;   //添加时间
    private int products;
}
