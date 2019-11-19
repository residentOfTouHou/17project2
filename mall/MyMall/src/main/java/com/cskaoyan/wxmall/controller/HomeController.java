package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/wx/home/")
public class HomeController {

    @Autowired
    private GoodsService goodsService;

    public BaseReqVo inedx(){
//        封装返回前端数据
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();

        Map<String, Object> allGoods = goodsService.findAll();

        return baseReqVo;

    }
}
