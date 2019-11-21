package com.cskaoyan.wxmall.bean;

import lombok.Data;

import java.util.Date;

@Data
public class AddressBean {
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

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

    private String detailedAddress;

}
