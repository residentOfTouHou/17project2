package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.generator.Cart;
import com.cskaoyan.mall.bean.generator.GoodsAlter;
import com.cskaoyan.mall.bean.generator.GoodsProduct;
import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.GoodsProductService;
import com.cskaoyan.mall.service.GoodsService;
import com.cskaoyan.wxmall.bean.CartAddReq;
import com.cskaoyan.wxmall.bean.CartCheckedReq;
import com.cskaoyan.wxmall.bean.CartCheckoutReq;
import com.cskaoyan.wxmall.service.CartService;
import lombok.Data;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    /**
     * 添加商品到购物车
     * 同一个人的相同规格商品会合并
     *
     * @param cartAddReq
     * @return
     */
    @RequestMapping("add")
    public BaseReqVo add(@RequestBody CartAddReq cartAddReq) {
        int goodsId = cartAddReq.getGoodsId();
        int number = cartAddReq.getNumber();
        int productId = cartAddReq.getProductId();
        // 查询商品
        GoodsAlter goods = goodsService.queryGoodsById(goodsId);
        GoodsProduct goodsProduct = goodsProductService.queryProductById(productId);
        BaseReqVo baseReqVo = new BaseReqVo();
        if (goodsProduct.getNumber() < number) {
            baseReqVo.setErrno(711);
            baseReqVo.setErrmsg("库存不足");
            return baseReqVo;
        }
        // 获取用户信息
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        // 查询购物车数量
        long total = cartService.queryTotalNumber(principal);
        int insert = cartService.insertCart(principal, goods, goodsProduct, number);
        // 更新商品库存
        int produceNumber = goodsProduct.getNumber() - number;
        goodsProduct.setNumber(produceNumber);
        goodsService.updateNumberById(goodsProduct);
        baseReqVo.setErrno(0);
        baseReqVo.setData(total);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 购物车列表
     *
     * @return
     */
    @RequestMapping("index")
    public BaseReqVo index() {
        Map<String, Object> dataMap = cartService.queryCarts();
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setData(dataMap);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 选择或者取消选择商品
     *
     * @return
     */
    @RequestMapping("checked")
    public BaseReqVo check(@RequestBody CartCheckedReq checkedReq) {
        int update = cartService.updateCheckeds(checkedReq);
        BaseReqVo baseReqVo = new BaseReqVo();
        Map<String, Object> dataMap = cartService.queryCarts();
        if (update == checkedReq.getProductIds().size()) {
            baseReqVo.setErrno(0);
            baseReqVo.setData(dataMap);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }

    /**
     * 更新购物车商品
     *
     * @param reqMap
     * @return
     */
    @RequestMapping("update")
    public BaseReqVo update(@RequestBody Map<String, Object> reqMap) {
        Integer id = (Integer) reqMap.get("id");
        Integer number = (Integer) reqMap.get("number");
        Integer productId = (Integer) reqMap.get("productId");
        GoodsProduct goodsProduct = goodsProductService.queryProductById(productId);
        BaseReqVo baseReqVo = new BaseReqVo();
        if (goodsProduct.getNumber() < number) {
            baseReqVo.setErrno(711);
            baseReqVo.setErrmsg("库存不足");
            return baseReqVo;
        }
        int update = cartService.updateCart(id, number);
        // 更新商品库存
        int produceNumber = goodsProduct.getNumber() - number;
        goodsProduct.setNumber(produceNumber);
        goodsService.updateNumberById(goodsProduct);
        if (update != 0) {
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }

    /**
     * 删除购物车的商品
     *
     * @param reqMap
     * @return
     */
    @RequestMapping("delete")
    public BaseReqVo delete(@RequestBody Map<String, List<Integer>> reqMap) {
        List<Integer> productIds = reqMap.get("productIds");
        int delete = cartService.deleteById(productIds);
        Map<String, Object> dataMap = cartService.queryCarts();
        BaseReqVo baseReqVo = new BaseReqVo();
        if (delete == productIds.size()) {
            baseReqVo.setErrno(0);
            baseReqVo.setData(dataMap);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }

    /**
     * 获取购物车商品件数
     *
     * @return
     */
    @RequestMapping("goodscount")
    public BaseReqVo countGoods() {
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        long total = cartService.queryTotalNumber(principal);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setData(total);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 立即购买商品
     * 先添加到购物车，若购物车有此商品，则更新
     *
     * @param cartAddReq
     * @return
     */
    @RequestMapping("fastadd")
    public BaseReqVo fastAdd(@RequestBody CartAddReq cartAddReq) {
        int goodsId = cartAddReq.getGoodsId();
        int number = cartAddReq.getNumber();
        int productId = cartAddReq.getProductId();
        GoodsProduct goodsProduct = goodsProductService.queryProductById(productId);
        BaseReqVo baseReqVo = new BaseReqVo();
        if (goodsProduct.getNumber() < number) {
            baseReqVo.setErrno(711);
            baseReqVo.setErrmsg("库存不足");
            return baseReqVo;
        }
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        GoodsAlter goods = goodsService.queryGoodsById(goodsId);
        int cartId = cartService.fastAddCart(principal, goods, goodsProduct, number);
        int produceNumber = goodsProduct.getNumber() - number;
        goodsProduct.setNumber(produceNumber);
        goodsService.updateNumberById(goodsProduct);
        baseReqVo.setErrno(0);
        baseReqVo.setData(cartId);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 下单前确认信息
     * 新疆、西藏、内蒙古地区邮费30元，其他地区邮费10元
     *
     * @param cartCheckoutReq
     * @return
     */
    @RequestMapping("checkout")
    public BaseReqVo checkout(CartCheckoutReq cartCheckoutReq) {
        Map<String, Object> dataMap = cartService.checkoutOrder(cartCheckoutReq);
        List<Cart> checkedGoodsList = (List<Cart>) dataMap.get("checkedGoodsList");
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setData(dataMap);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
