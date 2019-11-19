package com.cskaoyan.mall.bean.generator;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author 杨盛
 * @date 2019/11/18 22:30
 */

@Data
public class Reply {

    private int id;

    private Integer commentId;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public Date getAddTime() {
        return new Date();
    }

    public Date getUpdateTime() {
        return new Date();
    }
}
