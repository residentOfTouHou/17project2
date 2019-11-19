package com.cskaoyan.mall.bean.generator;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 杨盛
 * @date 2019/11/15 23:22
 */

@Data
public class GoodsAlter {

    private Integer id;

    private String goodsSn;

    private String name;

    private Integer categoryId;

    private Integer brandId;

    private String[] gallery;

    private String keywords;

    private String brief;

    private Boolean isOnSale;

    private Short sortOrder;

    private String picUrl;

    private String shareUrl;

    private Boolean isNew;

    private Boolean isHot;

    private String unit;

    private BigDecimal counterPrice;

    private BigDecimal retailPrice;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private boolean deleted;

    private String detail;

    public void setAddTime(Date addTime) {
        this.addTime = new Date();
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = new Date();
    }

    public Date getAddTime() {
        return new Date();
    }

    public Date getUpdateTime() {
        return new Date();
    }

    public Integer getId() {
        return id;
    }
}
