package com.cskaoyan.mall.bean.jsonbean;

import com.cskaoyan.mall.bean.generator.Footprint;
import lombok.Data;

import java.util.List;

@Data
public class FootprintData {
    private Long total;

    private List<Footprint> items;
}
