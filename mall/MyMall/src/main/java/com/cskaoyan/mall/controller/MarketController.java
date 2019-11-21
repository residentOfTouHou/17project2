package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.generator.Brand;
import com.cskaoyan.mall.bean.generator.Category;
import com.cskaoyan.mall.bean.generator.Issue;
import com.cskaoyan.mall.bean.generator.Keyword;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.bean.jsonbean.CategoryL1Segment;
import com.cskaoyan.mall.bean.jsonbean.CategorySegment;
import com.cskaoyan.mall.bean.jsonbean.RegionSegment;
import com.cskaoyan.mall.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        List<RegionSegment> regionList=marketService.queryRegion();
        baseReqVo.setErrno(0);
        baseReqVo.setData(regionList);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 查询品牌制造商
     *
     *
     * 总页
     * page=1&limit=20&sort=add_time&order=desc
     * 精准查询id
     * page=1&limit=20&id=1&name=&sort=add_time&order=desc
     * 模糊查询name
     * page=1&limit=20&id=&name=O&sort=add_time&order=desc 测试有问题
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
     *        }]
     *  },
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("brand/list")
    public BaseReqVo getBrand(@RequestParam Map<String,String> map){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map<String,Object> result= marketService.queryBrand(map);
        baseReqVo.setErrno(0);
        baseReqVo.setData(result);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

//    /**
//     * 上传图片
//     * 这部分应该直接调用刘铭的接口 暂时先放着
//     *
//     * ------WebKitFormBoundaryPuWQRqed7fO4h4XQ
//     * Content-Disposition: form-data; name="file"; filename="猫車.jpg"
//     * Content-Type: image/jpeg
//     *
//     * {
//     * 	"errno": 0,
//     * 	"data": {
//     * 		"id": 1559,
//     * 		"key": "680sam90vgytydnb24zs.jpg",
//     * 		"name": "猫車.jpg",
//     * 		"type": "image/jpeg",
//     * 		"size": 293410,
//     * 		"url": "http://192.168.2.100:8081/wx/storage/fetch/680sam90vgytydnb24zs.jpg",
//     * 		"addTime": "2019-11-16 01:18:14",
//     * 		"updateTime": "2019-11-16 01:18:14"
//     *   },
//     * 	"errmsg": "成功"
//     * }
//     * @return
//     */
//    @RequestMapping("storage/create")
//    public BaseReqVo createImg(HttpServletRequest request, @RequestParam("file") MultipartFile file){
//        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
//        Map<String,Object> result = null;
//        try {
//            result = marketService.createImg(request,file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        baseReqVo.setErrno(0);
//        baseReqVo.setData(result);
//        baseReqVo.setErrmsg("成功");
//        return baseReqVo;
//    }


    /**
     * 添加品牌
     * postman 测试通过
     *
     * 请求报文
     * {
     * 	"name": "⑨",
     * 	"desc": "999",
     * 	"floorPrice": "9999",
     * 	"picUrl": "http://192.168.2.100:8081/wx/storage/fetch/680sam90vgytydnb24zs.jpg"
     * }
     *
     * 响应报文
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"id": 1046048,
     * 		"name": "⑨",
     * 		"desc": "999",
     * 		"picUrl": "http://192.168.2.100:8081/wx/storage/fetch/680sam90vgytydnb24zs.jpg",
     * 		"floorPrice": 9999,
     * 		"addTime": "2019-11-16 01:23:25",
     * 		"updateTime": "2019-11-16 01:23:25"
     *   },
     * 	"errmsg": "成功"
     * }
     *
     * @return
     */
    @RequestMapping("brand/create")
    public BaseReqVo addBrand(@RequestBody Brand brand){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map<String,Object> result = marketService.addBrand(brand);
        if(result.get("err")!=null&&"noPic".equals(result.get("err"))){
            baseReqVo.setErrno(511);
            baseReqVo.setErrmsg("商品图片不能为空！");
            return baseReqVo;
        }
        if(result.get("err")!=null&&"noNumber".equals(result.get("err"))){
            baseReqVo.setErrno(512);
            baseReqVo.setErrmsg("底价只能为数字，最多8位整数，最多两位小数！");
            return baseReqVo;
        }
        baseReqVo.setErrno(0);
        baseReqVo.setData(result);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 更新品牌
     * 测试通过
     *
     * 请求报文
     * {
     * 	"id": 1046048,
     * 	"name": "⑨",
     * 	"desc": "999",
     * 	"picUrl": "http://192.168.2.100:8081/wx/storage/fetch/680sam90vgytydnb24zs.jpg",
     * 	"floorPrice": 9999,
     * 	"addTime": "2019-11-16 01:23:25",
     * 	"updateTime": "2019-11-16 01:23:25"
     * }
     *
     * 响应报文
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"id": 1046048,
     * 		"name": "⑨",
     * 		"desc": "999",
     * 		"picUrl": "http://192.168.2.100:8081/wx/storage/fetch/680sam90vgytydnb24zs.jpg",
     * 		"floorPrice": 9999,
     * 		"addTime": "2019-11-16 01:23:25",
     * 		"updateTime": "2019-11-16 01:43:17"
     *        },
     * 	"errmsg": "成功"
     * }
     * @param brand
     * @return
     */
    @RequestMapping("brand/update")
    public BaseReqVo updateBrand(@RequestBody Brand brand){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Brand result = marketService.updateBrand(brand);
        if(result == null){
            baseReqVo.setErrno(512);
            baseReqVo.setErrmsg("底价只能为数字，最多8位整数，最多两位小数！");
            return baseReqVo;
        }
        baseReqVo.setErrno(0);
        baseReqVo.setData(result);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 删除品牌
     *
     * {
     * 	"id": 1046047,
     * 	"name": "来啊",
     * 	"desc": "快活啊",
     * 	"picUrl": "http://192.168.2.100:8081/wx/storage/fetch/w77ekk78w7cqn8ypoikq.jpg",
     * 	"sortOrder": 50,
     * 	"floorPrice": 100,
     * 	"addTime": "2019-11-16 01:01:11",
     * 	"updateTime": "2019-11-16 01:01:11",
     * 	"deleted": false
     * }
     *
     * {
     * 	"errno": 0,
     * 	"errmsg": "成功"
     * }
     * @param brand
     * @return
     */
    @RequestMapping("brand/delete")
    public BaseReqVo deleteBrand(@RequestBody Brand brand){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        int code = marketService.deleteBrand(brand);
        if(code == 0){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }else if(code ==-1){

        }
        return  baseReqVo;
    }

    /**
     * {
     * 	"errno": 0,
     * 	"data": [{
     * 		"id": 1011000,
     * 		"name": "婴童",
     * 		"keywords": "[1,2,3,4]",
     * 		"desc": "爱，从心开始",
     * 		"iconUrl": "http://yanxuan.nosdn.127.net/1ba9967b8de1ac50fad21774a4494f5d.png",
     * 		"picUrl": "http://yanxuan.nosdn.127.net/9cc0b3e0d5a4f4a22134c170f10b70f2.png",
     * 		"level": "L1",
     * 		"children": [{
     * 			"id": 1020003,
     * 			"name": "服饰",
     * 			"keywords": "",
     * 			"desc": "萌宝穿搭，柔软舒适触感",
     * 			"iconUrl": "http://yanxuan.nosdn.127.net/4e50f3c4e4d0a64cd0ad14cfc0b6bd17.png",
     * 			"picUrl": "http://yanxuan.nosdn.127.net/004f5f96df4aeb0645abbd70c0637239.png",
     * 			"level": "L2"
     *                }, {
     * 			"id": 1020004,
     * 			"name": "婴童洗护",
     * 			"keywords": "",
     * 			"desc": "天然，呵护宝宝肌肤",
     * 			"iconUrl": "http://yanxuan.nosdn.127.net/c55338691ebd46bee9ebf225f80363ce.png",
     * 			"picUrl": "http://yanxuan.nosdn.127.net/f2e301b189befff1d99adf917ba8ce20.png",
     * 			"level": "L2"
     *        }, {
     * 			"id": 1020005,
     * 			"name": "寝居",
     * 			"keywords": "",
     * 			"desc": "无荧光剂，婴幼儿A类标准",
     * 			"iconUrl": "http://yanxuan.nosdn.127.net/0f3c5ad63139096fd0760219e12149af.png",
     * 			"picUrl": "http://yanxuan.nosdn.127.net/476995896abea91d3f2e9ec20d56bd8d.png",
     * 			"level": "L2"
     *        }]
     *   }],
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("category/list")
    public BaseReqVo getCategoryList(){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        List<CategorySegment> categoryList = marketService.getCategoryList();
        baseReqVo.setErrno(0);
        baseReqVo.setData(categoryList);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 查询所有L1级类目
     *
     * {
     * 	"errno": 0,
     * 	"data": [{
     * 		"value": 1011000,
     * 		"label": "婴童"
     *        }, {
     * 		"value": 1012000,
     * 		"label": "杂货"
     *    }, {
     * 		"value": 1013001,
     * 		"label": "洗护"
     *    }],
     * 	"errmsg": "成功"
     * }
     */
    @RequestMapping("category/l1")
    public BaseReqVo getCategoryL1(){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        List<CategoryL1Segment> categoryList = marketService.getCategoryL1();
        baseReqVo.setErrno(0);
        baseReqVo.setData(categoryList);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 添加类目
     *
     * 一级
     * {
     * 	"name": "渣渣",
     * 	"keywords": "渣",
     * 	"level": "L1",
     * 	"pid": 0,
     * 	"desc": "阿萨多斯",
     * 	"iconUrl": "http://192.168.2.100:8081/wx/storage/fetch/ggcg4q1xjbe4uzcmgnci.jpg",
     * 	"picUrl": "http://192.168.2.100:8081/wx/storage/fetch/1pokhw0o7pe2qga6bpdk.jpg"
     * }
     *
     * 二级
     * {
     * 	"name": "孙渣",
     * 	"keywords": "孙渣",
     * 	"level": "L2",
     * 	"pid": 1036073,
     * 	"desc": "孙渣",
     * 	"iconUrl": "http://192.168.2.100:8081/wx/storage/fetch/cqv2qc1qxxzyieq84fxp.jpg",
     * 	"picUrl": "http://192.168.2.100:8081/wx/storage/fetch/0w72qusbcnpk4ynzaqbd.jpg"
     * }
     *
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"id": 1036074,
     * 		"name": "孙渣",
     * 		"keywords": "孙渣",
     * 		"desc": "孙渣",
     * 		"pid": 1036073,
     * 		"iconUrl": "http://192.168.2.100:8081/wx/storage/fetch/cqv2qc1qxxzyieq84fxp.jpg",
     * 		"picUrl": "http://192.168.2.100:8081/wx/storage/fetch/0w72qusbcnpk4ynzaqbd.jpg",
     * 		"level": "L2",
     * 		"addTime": "2019-11-16 08:34:39",
     * 		"updateTime": "2019-11-16 08:34:39"
     *        },
     * 	"errmsg": "成功"
     * }
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"id": 1036075,
     * 		"name": "阿达",
     * 		"keywords": "阿达",
     * 		"desc": "阿萨德",
     * 		"pid": 0,
     * 		"iconUrl": "",
     * 		"picUrl": "",
     * 		"level": "L1",
     * 		"addTime": "2019-11-16 08:35:46",
     * 		"updateTime": "2019-11-16 08:35:46"
     *        },
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("category/create")
    public BaseReqVo addCategory(@RequestBody Category category){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Category result = marketService.addCategory(category);
        baseReqVo.setErrno(0);
        baseReqVo.setData(result);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 编辑类目
     *
     * 一级
     *  {
     * 	"id": 1011000,
     * 	"name": "婴童",
     * 	"keywords": "阿萨德",
     * 	"desc": "爱，从心开始",
     * 	"iconUrl": "http://yanxuan.nosdn.127.net/1ba9967b8de1ac50fad21774a4494f5d.png",
     * 	"picUrl": "http://yanxuan.nosdn.127.net/9cc0b3e0d5a4f4a22134c170f10b70f2.png",
     * 	"level": "L1",
     * 	"children": [{
     * 		"id": 1020003,
     * 		"name": "服饰",
     * 		"keywords": "",
     * 		"desc": "萌宝穿搭，柔软舒适触感",
     * 		"iconUrl": "http://yanxuan.nosdn.127.net/4e50f3c4e4d0a64cd0ad14cfc0b6bd17.png",
     * 		"picUrl": "http://yanxuan.nosdn.127.net/004f5f96df4aeb0645abbd70c0637239.png",
     * 		"level": "L2"
     *      }, {
     * 		"id": 1020004,
     * 		"name": "婴童洗护",
     * 		"keywords": "",
     * 		"desc": "天然，呵护宝宝肌肤",
     * 		"iconUrl": "http://yanxuan.nosdn.127.net/c55338691ebd46bee9ebf225f80363ce.png",
     * 		"picUrl": "http://yanxuan.nosdn.127.net/f2e301b189befff1d99adf917ba8ce20.png",
     * 		"level": "L2"
     *    }]
     * }
     *
     * 二级 变换所属一级
     * {
     * 	"id": 1020003,
     * 	"name": "服饰",
     * 	"keywords": "asda",
     * 	"desc": "萌宝穿搭，柔软舒适触感",
     * 	"iconUrl": "http://yanxuan.nosdn.127.net/4e50f3c4e4d0a64cd0ad14cfc0b6bd17.png",
     * 	"picUrl": "http://yanxuan.nosdn.127.net/004f5f96df4aeb0645abbd70c0637239.png",
     * 	"level": "L2",
     * 	"pid": 1012000
     * }
     *
     * 一级 降级为二级 所属二级默认删除
     * {
     * 	"id": 1011000,
     * 	"name": "婴童",
     * 	"keywords": "阿萨德",
     * 	"desc": "爱，从心开始",
     * 	"iconUrl": "http://yanxuan.nosdn.127.net/1ba9967b8de1ac50fad21774a4494f5d.png",
     * 	"picUrl": "http://yanxuan.nosdn.127.net/9cc0b3e0d5a4f4a22134c170f10b70f2.png",
     * 	"level": "L2",
     * 	"children": [{
     * 		"id": 1020004,
     * 		"name": "婴童洗护",
     * 		"keywords": "",
     * 		"desc": "天然，呵护宝宝肌肤",
     * 		"iconUrl": "http://yanxuan.nosdn.127.net/c55338691ebd46bee9ebf225f80363ce.png",
     * 		"picUrl": "http://yanxuan.nosdn.127.net/f2e301b189befff1d99adf917ba8ce20.png",
     * 		"level": "L2"
     *        }, {
     * 		"id": 1020005,
     * 		"name": "寝居",
     * 		"keywords": "",
     * 		"desc": "无荧光剂，婴幼儿A类标准",
     * 		"iconUrl": "http://yanxuan.nosdn.127.net/0f3c5ad63139096fd0760219e12149af.png",
     * 		"picUrl": "http://yanxuan.nosdn.127.net/476995896abea91d3f2e9ec20d56bd8d.png",
     * 		"level": "L2"
     *    }],
     * 	"pid": 1036075
     * }
     *
     * {"errno":0,"errmsg":"成功"}
     * @return
     */
    @RequestMapping("category/update")
    public BaseReqVo updateCategory(@RequestBody CategorySegment categorySegment) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        marketService.updateCategory(categorySegment);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 删除条目
     *
     * {
     * 	"id": 1036075,
     * 	"name": "阿达",
     * 	"keywords": "阿达",
     * 	"desc": "阿萨德",
     * 	"iconUrl": "",
     * 	"picUrl": "",
     * 	"level": "L1",
     * 	"children": [{
     * 		"id": 1011000,
     * 		"name": "婴童",
     * 		"keywords": "阿萨德",
     * 		"desc": "爱，从心开始",
     * 		"iconUrl": "http://yanxuan.nosdn.127.net/1ba9967b8de1ac50fad21774a4494f5d.png",
     * 		"picUrl": "http://yanxuan.nosdn.127.net/9cc0b3e0d5a4f4a22134c170f10b70f2.png",
     * 		"level": "L2"
     *        }]
     * }
     *
     * {"errno":0,"errmsg":"成功"}
     * @return
     */
    @RequestMapping("category/delete")
    public BaseReqVo deleteCategory(@RequestBody CategorySegment categorySegment){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        marketService.deleteCategory(categorySegment);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 查询订单信息
     *
     * page=1&limit=20&sort=add_time&order=desc
     * page=1&limit=20&sort=add_time&order=desc&userId=1
     * page=1&limit=20&orderStatusArray=202&orderStatusArray=301&sort=add_time&order=desc&userId=&orderSn=1
     *
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"total": 474,
     * 		"items": [{
     * 			"id": 498,
     * 			"userId": 1,
     * 			"orderSn": "20191008947283",
     * 			"orderStatus": 103,
     * 			"consignee": "啦啦啦",
     * 			"mobile": "17845451212",
     * 			"address": "河北省 秦皇岛市 抚宁区 1313",
     * 			"message": "",
     * 			"goodsPrice": 12.90,
     * 			"freightPrice": 9.00,
     * 			"couponPrice": 0.00,
     * 			"integralPrice": 0.00,
     * 			"grouponPrice": 0.00,
     * 			"orderPrice": 21.90,
     * 			"actualPrice": 21.90,
     * 			"comments": 0,
     * 			"endTime": "2019-10-08 09:17:51",
     * 			"addTime": "2019-10-08 08:20:53",
     * 			"updateTime": "2019-10-08 09:17:51",
     * 			"deleted": false
     *           }, {
     * 			"id": 497,
     * 			"userId": 1,
     * 			"orderSn": "20191008064881",
     * 			"orderStatus": 103,
     * 			"consignee": "啦啦啦",
     * 			"mobile": "17845451212",
     * 			"address": "河北省 秦皇岛市 抚宁区 1313",
     * 			"message": "",
     * 			"goodsPrice": 12.90,
     * 			"freightPrice": 9.00,
     * 			"couponPrice": 0.00,
     * 			"integralPrice": 0.00,
     * 			"grouponPrice": 0.00,
     * 			"orderPrice": 21.90,
     * 			"actualPrice": 21.90,
     * 			"comments": 0,
     * 			"endTime": "2019-10-08 08:17:50",
     * 			"addTime": "2019-10-08 07:25:04",
     * 			"updateTime": "2019-10-08 08:17:50",
     * 			"deleted": false
     *        }]
     *   },
     * 	"errmsg": "成功"
     * }
     */
    @RequestMapping("order/list")
    public BaseReqVo getOrderList(int page, int limit, Integer[] orderStatusArray
            , String sort, String order, Integer userId, String orderSn){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map<String,Object> result = marketService.getOrderList(page,limit,orderStatusArray,sort,order,userId,orderSn);
        baseReqVo.setErrno(0);
        baseReqVo.setData(result);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 获取订单详情
     *
     * id=498
     *
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"orderGoods": [{
     * 			"id": 629,
     * 			"orderId": 498,
     * 			"goodsId": 1155015,
     * 			"goodsName": "绿豆糕 80克（4枚入）",
     * 			"goodsSn": "1155015",
     * 			"productId": 242,
     * 			"number": 1,
     * 			"price": 12.90,
     * 			"specifications": ["标准"],
     * 			"picUrl": "http://yanxuan.nosdn.127.net/66b9f1638c0517d179262f14ed1345f9.png",
     * 			"comment": 0,
     * 			"addTime": "2019-10-08 08:20:53",
     * 			"updateTime": "2019-10-08 08:20:53",
     * 			"deleted": false
     *       }],
     * 		"user": {
     * 			"nickname": "dr lan",
     * 			"avatar": ""
     *       },
     * 		"order": {
     * 			"id": 498,
     * 			"userId": 1,
     * 			"orderSn": "20191008947283",
     * 			"orderStatus": 103,
     * 			"consignee": "啦啦啦",
     * 			"mobile": "17845451212",
     * 			"address": "河北省 秦皇岛市 抚宁区 1313",
     * 			"message": "",
     * 			"goodsPrice": 12.90,
     * 			"freightPrice": 9.00,
     * 			"couponPrice": 0.00,
     * 			"integralPrice": 0.00,
     * 			"grouponPrice": 0.00,
     * 			"orderPrice": 21.90,
     * 			"actualPrice": 21.90,
     * 			"comments": 0,
     * 			"endTime": "2019-10-08 09:17:51",
     * 			"addTime": "2019-10-08 08:20:53",
     * 			"updateTime": "2019-10-08 09:17:51",
     * 			"deleted": false
     *        }
     *   },
     * 	"errmsg": "成功"
     * }
     */
    @RequestMapping("order/detail")
    public BaseReqVo getOrderDetail(Integer id){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map<String,Object> result = marketService.getOrderDetail(id);
        baseReqVo.setErrno(0);
        baseReqVo.setData(result);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 订单发货
     *
     * {
     * 	"orderId": 1,
     * 	"shipChannel": "1",
     * 	"shipSn": "abcd"
     * }
     *
     *
     */
    @RequestMapping("order/ship")
    public BaseReqVo shipOrder(Integer orderId ,String shipChannel ,String shipSn){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        marketService.shipOrder(orderId,shipChannel,shipSn);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 系统退款
     *
     * {"orderId":3,"refundMoney":1500}
     */
    @RequestMapping("order/refund")
    public BaseReqVo refundOrder(@RequestBody Map<String,Integer> map){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Integer orderId = map.get("orderId");
        marketService.refundOrder(orderId);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 查询问题
     *
     * page=1&limit=20&sort=add_time&order=desc
     * page=1&limit=20&question=1&sort=add_time&order=desc
     *
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"total": 10,
     * 		"items": [{
     * 			"id": 27,
     * 			"question": "自定义问题",
     * 			"answer": "自定义",
     * 			"addTime": "2019-11-16 21:47:36",
     * 			"updateTime": "2019-11-16 21:47:36",
     * 			"deleted": false
     *                }, {
     * 			"id": 24,
     * 			"question": "1111",
     * 			"answer": "11112",
     * 			"addTime": "2019-11-16 03:21:00",
     * 			"updateTime": "2019-11-16 06:15:52",
     * 			"deleted": false
     *        }]* 	},
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("issue/list")
    public BaseReqVo getIssueList(Integer page, Integer limit, String question, String sort, String order){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map<String,Object> result = marketService.getIssueList(page,limit,question,sort,order);
        baseReqVo.setErrno(0);
        baseReqVo.setData(result);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 更新问题
     *
     * {
     * 	"id": 27,
     * 	"question": "自定义问题",
     * 	"answer": "1",
     * 	"addTime": "2019-11-16 21:47:36",
     * 	"updateTime": "2019-11-16 21:47:36",
     * 	"deleted": false
     * }
     *
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"id": 27,
     * 		"question": "自定义问题",
     * 		"answer": "1",
     * 		"addTime": "2019-11-16 21:47:36",
     * 		"updateTime": "2019-11-17 02:24:48",
     * 		"deleted": false
     *   },
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("issue/update")
    public BaseReqVo updateIssue(@RequestBody Issue issue){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Issue result = marketService.updateIssue(issue);
        baseReqVo.setErrno(0);
        baseReqVo.setData(result);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 删除问题
     *
     * {
     * 	"id": 27,
     * 	"question": "自定义问题",
     * 	"answer": "1",
     * 	"addTime": "2019-11-16 21:47:36",
     * 	"updateTime": "2019-11-16 21:47:36",
     * 	"deleted": false
     * }
     *
     * {"errno":0,"errmsg":"成功"}
     */
    @RequestMapping("issue/delete")
    public BaseReqVo deleteIssue(@RequestBody Issue issue){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        marketService.deleteIssue(issue);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 获取关键词
     *
     * page=1&limit=20&sort=add_time&order=desc
     * page=1&limit=20&url=1&sort=add_time&order=desc
     * page=1&limit=20&keyword=%E9%98%BF&url=&sort=add_time&order=desc
     *
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"total": 2,
     * 		"items": [{
     * 			"id": 53,
     * 			"keyword": "阿萨德",
     * 			"url": "阿萨德",
     * 			"isHot": true,
     * 			"isDefault": true,
     * 			"sortOrder": 100,
     * 			"addTime": "2019-11-16 08:31:05",
     * 			"updateTime": "2019-11-16 08:31:05",
     * 			"deleted": false
     *                }, {
     * 			"id": 51,
     * 			"keyword": "阿达",
     * 			"url": "奥德赛",
     * 			"isHot": true,
     * 			"isDefault": true,
     * 			"sortOrder": 100,
     * 			"addTime": "2019-11-16 07:53:29",
     * 			"updateTime": "2019-11-16 07:53:29",
     * 			"deleted": false
     *        }]
     *   },
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("keyword/list")
    public BaseReqVo getKeywords(Integer page, Integer limit, String url, String keyword, String sort, String order){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map<String ,Object> result = marketService.getKeywords(page,limit,url,keyword,sort,order);
        baseReqVo.setErrno(0);
        baseReqVo.setData(result);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 添加关键词
     *
     * {
     * 	"keyword": "阿萨德",
     * 	"url": "阿萨德a",
     * 	"isHot": true,
     * 	"isDefault": true
     * }
     *
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"id": 57,
     * 		"keyword": "阿萨德",
     * 		"url": "阿萨德a",
     * 		"isHot": true,
     * 		"isDefault": true,
     * 		"addTime": "2019-11-17 04:00:46",
     * 		"updateTime": "2019-11-17 04:00:46"
     *        },
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("keyword/create")
    public BaseReqVo addKeyword(@RequestBody Keyword keyword){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Keyword result= marketService.addKeyword(keyword);
        baseReqVo.setErrno(0);
        baseReqVo.setData(result);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 更新关键词
     *
     * {
     * 	"id": 56,
     * 	"keyword": "222",
     * 	"url": "2222",
     * 	"isHot": false,
     * 	"isDefault": true,
     * 	"sortOrder": 100,
     * 	"addTime": "2019-11-17 03:27:33",
     * 	"updateTime": "2019-11-17 03:27:33",
     * 	"deleted": false
     * }
     *
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"id": 56,
     * 		"keyword": "222",
     * 		"url": "2222",
     * 		"isHot": false,
     * 		"isDefault": true,
     * 		"sortOrder": 100,
     * 		"addTime": "2019-11-17 03:27:33",
     * 		"updateTime": "2019-11-17 03:41:01",
     * 		"deleted": false
     *        },
     * 	"errmsg": "成功"
     * }
     *
     * @return
     */
    @RequestMapping("keyword/update")
    public BaseReqVo updateKeyword(@RequestBody Keyword keyword){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Keyword result= marketService.updateKeyword(keyword);
        baseReqVo.setErrno(0);
        baseReqVo.setData(result);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 删除关键词
     *
     * {
     * 	"id": 56,
     * 	"keyword": "222",
     * 	"url": "2222",
     * 	"isHot": false,
     * 	"isDefault": true,
     * 	"sortOrder": 100,
     * 	"addTime": "2019-11-17 03:27:33",
     * 	"updateTime": "2019-11-17 03:27:33",
     * 	"deleted": false
     * }
     *
     * {"errno":0,"errmsg":"成功"}
     */
    @RequestMapping("keyword/delete")
    public BaseReqVo deleteKeyword(@RequestBody Keyword keyword){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        marketService.deleteKeyword(keyword);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }


}
