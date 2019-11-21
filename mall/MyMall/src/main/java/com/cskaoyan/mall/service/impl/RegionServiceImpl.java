/**
 *
 */
package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.Region;
import com.cskaoyan.mall.bean.generator.RegionExample;
import com.cskaoyan.mall.mapper.RegionMapper;
import com.cskaoyan.mall.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    RegionMapper regionMapper;

    @Override
    public List<Region> getRegionsByPid(int pid) {
        RegionExample regionExample = new RegionExample();
        regionExample.createCriteria().andPidEqualTo(pid);
        return regionMapper.selectByExample(regionExample);
    }
}
