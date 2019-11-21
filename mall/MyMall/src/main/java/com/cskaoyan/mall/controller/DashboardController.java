/**
 *
 */
package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.GoodsProductService;
import com.cskaoyan.mall.service.GoodsService;
import com.cskaoyan.mall.service.UserService;
import com.cskaoyan.wxmall.service.OrderWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/admin")
public class DashboardController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    UserService userService;

    @Autowired
    OrderWxService orderWxService;

    @Autowired
    GoodsProductService goodsProductService;

    @RequestMapping("dashboard")
    public BaseReqVo dashboard(){
        BaseReqVo baseReqVo = new BaseReqVo();
        HashMap<String, Object> map = new HashMap<>();
        map.put("goodsTotal",goodsService.findAll().size());
        map.put("userTotal",userService.getUserNumber());
        map.put("orderTotal",orderWxService.getOrderNumber());
        map.put("productTotal",goodsProductService.getProductNumber());
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(map);
        return baseReqVo;
    }
}
