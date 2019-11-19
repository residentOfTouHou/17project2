package com.cskaoyan.mall.bean.generator;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 杨盛
 * @date 2019/11/16 21:32
 */

@Data
public class GoodsProductAlter {

    private Integer id;

    private Integer goodsId;

    private String[] specifications;

    private BigDecimal price;

    private Integer number;

    private String url;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private boolean deleted;

    public Date getAddTime() {
        return new Date();
    }

    public Date getUpdateTime() {
        return new Date();
    }
}
