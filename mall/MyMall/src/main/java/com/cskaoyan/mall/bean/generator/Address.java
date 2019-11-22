package com.cskaoyan.mall.bean.generator;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class Address {
    private Integer id;

    private String name;

    private Integer userId;

    private Integer provinceId;

    private String province;

    private Integer cityId;

    private String city;

    private Integer areaId;

    private String area;

    private String address;

    private String mobile;

    private Boolean isDefault;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Boolean deleted;


}