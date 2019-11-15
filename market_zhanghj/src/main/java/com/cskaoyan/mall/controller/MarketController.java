package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.generator.Brand;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.bean.jsonbean.RegionSegment;
import com.cskaoyan.mall.service.MarketService;
import com.cskaoyan.mall.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/15
 * @time 19:44
 */
@RestController
@RequestMapping("admin")
public class MarketController {

    @Autowired
    MarketService marketService;

    /**
     * 查询出行政区域
     * {
     * 	"errno": 0,
     * 	"data": [{
     * 		"id": 1,
     * 		"name": "北京市",
     * 		"type": 1,
     * 		"code": 11,
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
    @RequestMapping("region/list")
    public BaseReqVo getRegion(){
        BaseReqVo baseReqVo = new BaseReqVo();
        List<RegionSegment> regionList=marketService.queryRegion();
        baseReqVo.setErrno(0);
        baseReqVo.setData(regionList);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 查询品牌制造商
     * 总页
     * page=1&limit=20&sort=add_time&order=desc
     * 精准查询id
     * page=1&limit=20&id=1&name=&sort=add_time&order=desc
     * 模糊查询name
     * page=1&limit=20&id=&name=O&sort=add_time&order=desc
     *
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"total": 46,
     * 		"items": [{
     * 			"id": 1024001,
     * 			"name": "OBH制造商",
     * 			"desc": "哈哈哈",
     * 			"picUrl": "http://yanxuan.nosdn.127.net/bf3499ac17a11ffb9bb7caa47ebef2dd.png",
     * 			"sortOrder": 42,
     * 			"floorPrice": 30.00,
     * 			"addTime": "2018-01-31 19:00:00",
     * 			"updateTime": "2019-11-15 09:43:59",
     * 			"deleted": false
     *                }, {
     * 			"id": 1024003,
     * 			"name": "Stoneline制造商",
     * 			"desc": "严选找寻德国经典品牌Stoneline的制造商，\n追踪工艺，考量细节，亲自试用，\n为你甄选出最合心意的锅具和陶瓷刀，下厨如神。",
     * 			"picUrl": "http://yanxuan.nosdn.127.net/3a44ae7db86f3f9b6e542720c54cc349.png",
     * 			"sortOrder": 28,
     * 			"floorPrice": 9.90,
     * 			"addTime": "2018-01-31 19:00:00",
     * 			"updateTime": "2018-01-31 19:00:00",
     * 			"deleted": false
     *        }]* 	},
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("brand/list")
    public BaseReqVo getBrand(Integer page ,Integer list ,String id ,String name ,String sort ,String order){
        BaseReqVo baseReqVo = new BaseReqVo();
        List<Brand> brandList;
        if(StringUtil.isEmpty(id)&&StringUtil.isEmpty(name)){
            brandList=marketService.queryBrand(page,list,sort,order);
        }else{
            brandList=marketService.queryBrand(page,list,id,name,sort,order);
        }
        baseReqVo.setErrno(0);
        baseReqVo.setData(brandList);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
