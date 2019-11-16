package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.generator.Brand;
import com.cskaoyan.mall.bean.generator.Category;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    /**
     * 上传图片
     * 这部分应该直接调用刘铭的接口 暂时先放着
     *
     * ------WebKitFormBoundaryPuWQRqed7fO4h4XQ
     * Content-Disposition: form-data; name="file"; filename="猫車.jpg"
     * Content-Type: image/jpeg
     *
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"id": 1559,
     * 		"key": "680sam90vgytydnb24zs.jpg",
     * 		"name": "猫車.jpg",
     * 		"type": "image/jpeg",
     * 		"size": 293410,
     * 		"url": "http://192.168.2.100:8081/wx/storage/fetch/680sam90vgytydnb24zs.jpg",
     * 		"addTime": "2019-11-16 01:18:14",
     * 		"updateTime": "2019-11-16 01:18:14"
     *   },
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("storage/create")
    public BaseReqVo createImg(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map<String,Object> result = null;
        try {
            result = marketService.createImg(request,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        baseReqVo.setErrno(0);
        baseReqVo.setData(result);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }


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
}
