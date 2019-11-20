package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.wxmall.bean.SubmitOrderBean;
import com.cskaoyan.wxmall.service.OrderWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    OrderWxService orderService;

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
        Integer id = orderService.submitOrder(orderBean);
        Map<String,Object> map = new HashMap<>();
        map.put("orderId",id);
        baseReqVo.setErrno(0);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
