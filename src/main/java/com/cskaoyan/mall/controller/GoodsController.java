package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.generator.*;
import com.cskaoyan.mall.bean.jsonbean.*;
import com.cskaoyan.mall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


/**
 * @author 杨盛
 * @date 2019/11/15 19:07
 */

@RestController
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    BrandService brandService;

    @Autowired
    GoodsSpecificationService goodsSpecificationService;

    @Autowired
    GoodsProductService goodsProductService;

    @Autowired
    GoodsAttributeService goodsAttributeService;

    @Autowired
    CommentService commentService;

    /**
     * 商品列表
     * 查询: 商品编号精准查询，商品名称模糊查询
     *
     * @param goodsQueryParameters
     * @return
     */
    @RequestMapping("admin/goods/list")
    public BaseReqVo goods(GoodsQueryParameters goodsQueryParameters) {
        GoodsData goodsData = goodsService.queryGoods(goodsQueryParameters);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setData(goodsData);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 所有类别
     *
     * @return
     */
    @RequestMapping("admin/goods/catAndBrand")
    public BaseReqVo catAndBrand() {
        List<Map<String, Object>> categoryList = new ArrayList<>();
        categoryList = categoryService.queryByLevel();
        Map<String, Object> categoryMap = new HashMap<>();
        categoryMap.put("categoryList", categoryList);
        List<BrandVo> brandList = brandService.queryBrands();
        categoryMap.put("brandList", brandList);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setData(categoryMap);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 添加商品
     *
     * @param goodsCreate
     * @return
     */
    @RequestMapping("admin/goods/create")
    public BaseReqVo create(@RequestBody GoodsCreate goodsCreate) {
        GoodsAlter goods = goodsCreate.getGoods();
        List<GoodsSpecification> specifications = goodsCreate.getSpecifications();
        List<GoodsProductAlter> products = goodsCreate.getProducts();
        List<GoodsAttribute> attributes = goodsCreate.getAttributes();
        BaseReqVo baseReqVo = new BaseReqVo();
        //  商品编号、商品名称不能为空
        if (goods.getGoodsSn() == null || goods.getName() == null || attributes.size() == 0) {
            baseReqVo.setErrno(401);
            baseReqVo.setErrmsg("参数为空");
            return baseReqVo;
        }
        int goodsId = Integer.parseInt(goods.getGoodsSn());
        goods.setId(goodsId);
        int insertGoods = goodsService.insertGoods(goods);
        int insertSpecifications = goodsSpecificationService.insertSpecifications(specifications, goodsId);
        int insertProducts = goodsProductService.insertProducts(products, goodsId);
        int insertAttributes = goodsAttributeService.insertProducts(attributes, goodsId);
        if (insertGoods == 1 && insertSpecifications == specifications.size() &&
                insertProducts == products.size() && insertAttributes == attributes.size()) {
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
            return baseReqVo;
        }
        return baseReqVo;
    }

    /**
     * 商品详情
     *
     * @param id
     * @return
     */
    @RequestMapping("admin/goods/detail")
    public BaseReqVo detail(int id) {
        Map<String, Object> dataMap = new HashMap<>();
        GoodsAlter goods = goodsService.queryGoodsById(id);
        List<Integer> categoryIds = categoryService.queryById(goods.getCategoryId());
        List<GoodsAttribute> attributes = goodsAttributeService.queryByGoodsId(goods.getId());
        List<GoodsSpecification> specifications = goodsSpecificationService.queryByGoodsId(goods.getId());
        List<GoodsProductAlter> products = goodsProductService.queryByGoodsId(goods.getId());
        dataMap.put("categoryIds", categoryIds);
        dataMap.put("goods", goods);
        dataMap.put("attributes", attributes);
        dataMap.put("specifications", specifications);
        dataMap.put("products", products);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setData(dataMap);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 修改商品
     *
     * @param goodsCreate
     * @return
     */
    @RequestMapping("admin/goods/update")
    public BaseReqVo update(@RequestBody GoodsCreate goodsCreate) {
        GoodsAlter goods = goodsCreate.getGoods();
        List<GoodsSpecification> specifications = goodsCreate.getSpecifications();
        List<GoodsAttribute> attributes = goodsCreate.getAttributes();
        List<GoodsProductAlter> products = goodsCreate.getProducts();

        BaseReqVo baseReqVo = new BaseReqVo();
        //  商品编号、商品名称不能为空
        if ("".equals(goods.getGoodsSn()) || "".equals(goods.getName()) || attributes.size() == 0) {
            baseReqVo.setErrmsg("参数为空");
            baseReqVo.setErrno(401);
            return baseReqVo;
        }

        int updateGoods = goodsService.updateGoods(goods);
        int updateSpecifications = goodsSpecificationService.updateSpecifications(specifications);
        int updateProducts = goodsProductService.updateProducts(products);
        int updateAttributes = goodsAttributeService.updateAttributes(attributes);
        if (updateGoods == 1 && updateSpecifications == specifications.size() &&
                updateProducts == products.size() && updateAttributes == attributes.size()) {
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
            return baseReqVo;
        }
        return baseReqVo;
    }

    /**
     * 删除商品并删除商品相关
     *
     * @param goods
     * @return
     */
    @RequestMapping("admin/goods/delete")
    public BaseReqVo delete(@RequestBody GoodsAlter goods) {
        int goodsId = goods.getId();
        int deleteGoods = goodsService.deleteGoods(goods.getId());
        int deleteSpecifications = goodsSpecificationService.deleteSpecifications(goodsId);
        int deleteProducts = goodsProductService.deleteProducts(goodsId);
        int deleteAttributes = goodsAttributeService.deleteAttributes(goodsId);
        int deleteComment = commentService.deleteCommentById(goodsId);
        BaseReqVo baseReqVo = new BaseReqVo();
        if (deleteGoods != 0 && deleteAttributes != 0 && deleteSpecifications != 0
                && deleteProducts != 0 && deleteComment != 0) {
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
            return baseReqVo;
        }
        return baseReqVo;
    }


}
