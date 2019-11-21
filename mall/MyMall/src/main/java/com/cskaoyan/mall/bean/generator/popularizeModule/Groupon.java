package com.cskaoyan.mall.bean.generator.popularizeModule;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Groupon {
    private Integer id;

    private Integer orderId;

    private int grouponId;

    private Integer rulesId;

    private Integer userId;

    private Integer creatorUserId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String shareUrl;

    private Boolean payed;

    private Boolean deleted;


}