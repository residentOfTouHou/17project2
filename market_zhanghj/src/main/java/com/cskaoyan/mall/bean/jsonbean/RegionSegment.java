package com.cskaoyan.mall.bean.jsonbean;

import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/15
 * @time 20:05
 */
@Data
public class RegionSegment {
    private Integer id;

    private String name;

    private Integer type;

    private Integer code;

    List<RegionSegment> children;
}
