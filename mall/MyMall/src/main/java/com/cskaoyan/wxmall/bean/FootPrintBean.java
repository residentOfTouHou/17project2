package com.cskaoyan.wxmall.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FootPrintBean {

    private String brief;

    private String name;

    private String picUrl;

    private BigDecimal retailPrice;

    private Integer id;

    private Integer goodsId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
}
