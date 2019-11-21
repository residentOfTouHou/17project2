package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.Region;

import java.util.List;

public interface RegionService {
    List<Region> getRegionsByPid(int pid);
}
