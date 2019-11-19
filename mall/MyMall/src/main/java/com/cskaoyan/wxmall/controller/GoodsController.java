package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/wx/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping("count")
    public BaseReqVo GetGoodsCount(){
        int i = goodsService.queryGoodsCount();
        HashMap<String,Integer> map = new HashMap<>();
        map.put("goodsCount",i);
        return BaseReqVo.ok(map);
    }
}
