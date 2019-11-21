package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.wxmall.bean.ListOrderBean;
import com.cskaoyan.wxmall.bean.SubmitOrderBean;
import com.cskaoyan.wxmall.service.OrderWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/19
 * @time 22:02
 */
@RestController
@RequestMapping("wx/order")
public class OrderWxController {

    @Autowired
    OrderWxService orderWxService;

    /**
     * 提交订单
     *
     * 团购规则未写
     *
     * {
     * 	"cartId": 1002,
     * 	"addressId": 82,
     * 	"couponId": 121,
     * 	"message": "杨盛快点写",
     * 	"grouponRulesId": 0,
     * 	"grouponLinkId": 0
     * }
     *
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"orderId": 536
     *        },
     * 	"errmsg": "成功"
     * }
     */
    @RequestMapping("submit")
    public BaseReqVo submitOrder(@RequestBody SubmitOrderBean orderBean){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Integer id = orderWxService.submitOrder(orderBean);
        Map<String,Object> map = new HashMap<>();
        map.put("orderId",id);
        baseReqVo.setErrno(0);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 订单的预支付会话
     *
     */
    @RequestMapping("prepay")
    public BaseReqVo prepayOrder(){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();

        return baseReqVo;
    }


    /**
     * 订单列表
     *
     * showType=1&page=1&size=10
     *
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"data": [{
     * 			"orderStatusText": "未付款",
     * 			"isGroupin": false,
     * 			"orderSn": "20191120579444",
     * 			"actualPrice": 167.00,
     * 			"goodsList": [{
     * 				"number": 1,
     * 				"picUrl": "http://yanxuan.nosdn.127.net/fc11a482efeece9630548d8b350e7f54.png",
     * 				"id": 750,
     * 				"goodsName": "阿瓦提长绒棉超柔弱捻浴巾"
     *           }, {
     * 				"number": 1,
     * 				"picUrl": "http://192.168.2.100:8081/wx/storage/fetch/nppnuob1202xw3ikr1to.jpg",
     * 				"id": 751,
     * 				"goodsName": "mmmm"
     *           }],
     * 			"id": 594,
     * 			"handleOption": {
     * 				"cancel": true,
     * 				"delete": false,
     * 				"pay": true,
     * 				"comment": false,
     * 				"confirm": false,
     * 				"refund": false,
     * 				"rebuy": false
     *            }
     *     }, {
     * 			"orderStatusText": "未付款",
     * 			"isGroupin": true,
     * 			"orderSn": "20191120690695",
     * 			"actualPrice": 56.00,
     * 			"goodsList": [{
     * 				"number": 1,
     * 				"picUrl": "http://yanxuan.nosdn.127.net/fc11a482efeece9630548d8b350e7f54.png",
     * 				"id": 749,
     * 				"goodsName": "阿瓦提长绒棉超柔弱捻浴巾"
     *            }],
     * 			"id": 593,
     * 			"handleOption": {
     * 				"cancel": true,
     * 				"delete": false,
     * 				"pay": true,
     * 				"comment": false,
     * 				"confirm": false,
     * 				"refund": false,
     * 				"rebuy": false
     *            }
     *        }],
     * 		"count": 2,
     * 		"totalPages": 1
     *    },
     * 	"errmsg": "成功"
     * }
     */
    @RequestMapping("list")
    public BaseReqVo listOrder(Integer showType,Integer page,Integer size){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map<String,Object> result = orderWxService.listOrder(showType,page,size);
//        List<ListOrderBean> result = orderWxService.listOrder(showType,page,size);
        baseReqVo.setErrno(0);
        baseReqVo.setData(result);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 获取订单详情
     *
     * orderId=648
     *
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"orderInfo": {
     * 			"consignee": "ffsf",
     * 			"address": "天津市 市辖区 和平区 fsfsfsfsfsd",
     * 			"addTime": "2019-11-20 08:45:55",
     * 			"orderSn": "20191120262422",
     * 			"actualPrice": 102.10,
     * 			"mobile": "13545645646",
     * 			"orderStatusText": "未付款",
     * 			"goodsPrice": 59.10,
     * 			"couponPrice": 0.00,
     * 			"id": 648,
     * 			"freightPrice": 43.00,
     * 			"handleOption": {
     * 				"cancel": true,
     * 				"delete": false,
     * 				"pay": true,
     * 				"comment": false,
     * 				"confirm": false,
     * 				"refund": false,
     * 				"rebuy": false
     *          }
     *      },
     * 		"orderGoods": [{
     * 			"id": 822,
     * 			"orderId": 648,
     * 			"goodsId": 1181185,
     * 			"goodsName": "110",
     * 			"goodsSn": "110",
     * 			"productId": 767,
     * 			"number": 6,
     * 			"price": 9.85,
     * 			"specifications": ["标准"],
     * 			"picUrl": "http://192.168.2.100:8081/wx/storage/fetch/hat74hv7jercsa6e8xcu.jpg",
     * 			"comment": 0,
     * 			"addTime": "2019-11-20 08:45:55",
     * 			"updateTime": "2019-11-20 08:45:55",
     * 			"deleted": false
     *        }]
     *    },
     * 	"errmsg": "成功"
     * }
     * @param orderId
     * @return
     */
    @RequestMapping("detail")
    public BaseReqVo detailOrder(Integer orderId){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map<String,Object> result = orderWxService.detailOrder(orderId);
        baseReqVo.setErrno(0);
        baseReqVo.setData(result);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 取消订单
     *
     * {"orderId":644}
     *
     * {"errno":0,"errmsg":"成功"}
     * @param map
     * @return
     */
    @RequestMapping("cancel")
    public BaseReqVo cancelOrder(@RequestBody Map<String,Integer> map){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Integer orderId = map.get("orderId");
        orderWxService.cancelOrder(orderId);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 用户退款取消订单
     *
     * {"orderId":27}
     *
     * {"errno":0,"errmsg":"成功"}
     * @param map
     * @return
     */
    @RequestMapping("refund")
    public BaseReqVo refundOrder(@RequestBody Map<String,Integer> map){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Integer orderId = map.get("orderId");
        orderWxService.refundOrder(orderId);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 删除订单
     *
     *
     * @param id
     * @return
     */
    @RequestMapping("delete")
    public BaseReqVo deleteOrder(Integer id){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        orderWxService.deleteOrder(id);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
