package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.generator.Category;
import com.cskaoyan.mall.bean.generator.Goods;
import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.CategoryService;
import com.cskaoyan.mall.service.GoodsService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/wx/goods")
public class GoodsWxController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    CategoryService categoryService;

    /**
     * 统计商品总数
     */
    @RequestMapping("count")
    public BaseReqVo GetGoodsCount(){
        int i = goodsService.queryGoodsCount();
        HashMap<String,Integer> map = new HashMap<>();
        map.put("goodsCount",i);
        return BaseReqVo.ok(map);
    }

    /**
     * 获得商品列表
     */
    @RequestMapping("list")
    public BaseReqVo GetGoodsList(Integer categoryId,Integer page,Integer size){
        List<Goods> goodsList = goodsService.queryGoodsList(categoryId,page,size);
        HashMap<String,Object> map = new HashMap<>();
        map.put("goodsList",goodsList);
        return BaseReqVo.ok(map);
    }

    /**
     * 获得分类数据
     */
    @RequestMapping("category")
    public BaseReqVo GetGoodsCategory(Integer id){
        HashMap<String,Object> map = categoryService.queryGoodsCategory(id);
        return BaseReqVo.ok(map);
    }

    /**
     * 获得商品的详情
     * 添加足迹
     */
    @RequestMapping("detail")
    public BaseReqVo GetGoodsDetail(Integer id){
        //获取UserId
        User primaryPrincipal = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Integer userId = primaryPrincipal.getId();

        HashMap<String,Object> map = goodsService.queryGoodsDetail(id,userId);
        return BaseReqVo.ok(map);
    }

    /**
     * 商品详情页的关联商品（大家都在看）
     */
    @RequestMapping("related")
    public BaseReqVo GetGoodsRelated(Integer id){
        HashMap<String,Object> map = goodsService.queryGoodsRelated(id);
        return BaseReqVo.ok(map);
    }

}
