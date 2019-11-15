package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.Brand;
import com.cskaoyan.mall.bean.generator.Region;
import com.cskaoyan.mall.bean.generator.RegionExample;
import com.cskaoyan.mall.bean.jsonbean.RegionSegment;
import com.cskaoyan.mall.mapper.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/15
 * @time 20:15
 */
@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    RegionMapper regionMapper;

    /**
     * 查询出行政区域
     * {
     * 	"errno": 0,
     * 	"data": [{
     * 		"id": 1,            id
     * 		"name": "北京市",    name
     * 		"type": 1,          type
     * 		"code": 11,         code
     * 		"children": [{
     * 			"id": 32,
     * 			"name": "市辖区",
     * 			"type": 2,
     * 			"code": 1101,
     * 			"children": [{
     * 				"id": 376,
     * 				"name": "东城区",
     * 				"type": 3,
     * 				"code": 110101
     *             }, {
     * 				"id": 377,
     * 				"name": "西城区",
     * 				"type": 3,
     * 				"code": 110102
     *            }, {
     * 				"id": 378,
     * 				"name": "朝阳区",
     * 				"type": 3,
     * 				"code": 110105
     *            }, {
     * 				"id": 379,
     * 				"name": "丰台区",
     * 				"type": 3,
     * 				"code": 110106
     *            }, {
     * 				"id": 380,
     * 				"name": "石景山区",
     * 				"type": 3,
     * 				"code": 110107
     *            }]
     *        }]
     *    }],
     * 	"errmsg": "成功"
     * }
     */
    @Override
    public List<RegionSegment> queryRegion() {
        //查出所有type=1 的一级区域
        RegionExample regionExample = new RegionExample();
        regionExample.createCriteria().andTypeEqualTo((byte) 1);
        List<Region> regionsLevelOne = regionMapper.selectByExample(regionExample);

        //封装返回的bean
        List<RegionSegment> regionSegments = new ArrayList<>();
        for (Region regionLo : regionsLevelOne) {
            RegionSegment regionSegment = new RegionSegment();
            regionSegment.setId(regionLo.getId());
            regionSegment.setCode(regionLo.getCode());
            regionSegment.setName(regionLo.getName());
            regionSegment.setType(1);

            //查询 type=2 且 code前缀为当前code 的当前二级区域 依次查询三级区域
            List<RegionSegment> regionSegmentsLevelTwo=regionMapper.queryLevelTwoRegion(2,regionLo.getCode());
            regionSegment.setChildren(regionSegmentsLevelTwo);
            regionSegments.add(regionSegment);
        }
        return regionSegments;
    }

    @Override
    public List<Brand> queryBrand(Integer page, Integer list, String sort, String order) {

        return null;
    }

    @Override
    public List<Brand> queryBrand(Integer page, Integer list, String id, String name, String sort, String order) {
        return null;
    }
}
