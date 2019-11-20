package com.cskaoyan.mall.wxmall.controller;

import com.cskaoyan.mall.bean.generator.Goods;
import com.cskaoyan.mall.bean.generator.GoodsAlter;
import com.cskaoyan.mall.bean.generator.GoodsProduct;
import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.GoodsProductService;
import com.cskaoyan.mall.service.GoodsService;
import com.cskaoyan.mall.wxmall.bean.CartAddReq;
import com.cskaoyan.mall.wxmall.service.CartService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杨盛
 * @date 2019/11/20 11:20
 */

@RestController
@RequestMapping("wx/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsProductService goodsProductService;

    @RequestMapping("add")
    public BaseReqVo add(@RequestBody CartAddReq cartAddReq) {
        int goodsId = cartAddReq.getGoodsId();
        int number = cartAddReq.getNumber();
        int productId = cartAddReq.getProductId();
        // 查询商品
        GoodsAlter goods = goodsService.queryGoodsById(goodsId);
        GoodsProduct goodsProduct = goodsProductService.queryProductById(productId);
        // 查询购物车数量
        long total = cartService.queryTotalNumber();
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        int insert = cartService.insertCart(principal, goods, goodsProduct, number);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setData(total);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

}
