package com.cskaoyan.mall.bean.generator;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author 杨盛
 * @date 2019/11/17 23:06
 */

@Data
public class CommentAlter {

    private Integer id;

    private Integer valueId;

    private Byte type;

    private String content;

    private Integer userId;

    private Boolean hasPicture;

    private String[] picUrls;

    private Short star;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Boolean deleted;

    public Date getAddTime() {
        return new Date();
    }

    public Date getUpdateTime() {
        return new Date();
    }
}
