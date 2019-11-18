package com.cskaoyan.mall.bean.jsonbean;

import com.cskaoyan.mall.bean.generator.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/16
 * @time 20:03
 */
@Data
public class CategorySegment {
    private Integer id;

    private String name;

    private String keywords;

    private String desc;

    private Integer pid;

    private String iconUrl;

    private String picUrl;

    private String level;

    private Byte sortOrder;

    @JsonFormat(pattern = "yyyy-MM-dd HH:dd:ss")
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:dd:ss")
    private Date updateTime;

    private Boolean deleted;

    private List<Category> children;
}
