package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.generator.Category;
import com.cskaoyan.mall.bean.generator.Goods;
import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.CategoryService;
import com.cskaoyan.mall.service.GoodsService;
import com.cskaoyan.wxmall.utils.UserIdUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
     * 获得商品列表 2种情况
     * 首页搜索：keyword=sadj & page=1 & size=20 & sort=name & order=desc & categoryId=0
     * categoryId=1036099&page=1&size=100
     */
/*    @RequestMapping("list")
    public BaseReqVo GetGoodsList(Integer categoryId,Integer page,Integer size,String keyword,String sort,String order){
        List<Goods> goodsList = null;
        if(keyword == null){
            goodsList = goodsService.queryGoodsList(categoryId,page,size);
        }else {
            goodsList = goodsService.queryGoodsListByName(keyword,page,size,sort,order);
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("goodsList",goodsList);
        return BaseReqVo.ok(map);
    }*/
    @RequestMapping("list")
    public BaseReqVo listGoods(String keyword,Boolean isNew, Boolean isHot,Integer page, int size,String sort, String order,Integer categoryId){

        if (keyword != null) {
            goodsService.insertKeyWord(keyword);
        }
        List<Goods> goodsList = new ArrayList<>();
        ArrayList<Integer> categoryIdList = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>();
        if (categoryId == 0) {
            goodsList = goodsService.selectAllGoods(keyword, isNew, isHot, page, size, sort, order, categoryId);
            HashSet<Integer> hashSet = new HashSet<>();
            for (Goods goods : goodsList) {
                Integer categoryId1 = goods.getCategoryId();
                hashSet.add(categoryId1);
            }
            Iterator<Integer> iterator = hashSet.iterator();
            categoryIdList = new ArrayList<>();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                categoryIdList.add(next);
            }
            categoryList = categoryService.selectAllCategoryById(categoryIdList);
            HashMap<String, Object> map = new HashMap<>();
            map.put("filterCategoryList",categoryList);
            map.put("goodsList",goodsList);
            map.put("count",goodsList.size());
            BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
            baseReqVo.setErrmsg("成功");
            baseReqVo.setErrno(0);
            baseReqVo.setData(map);
            return baseReqVo;
        }
        if(categoryId != 0 && sort != null) {
            goodsList = goodsService.selectAllGoods(keyword, isNew, isHot, page, size, sort, order, 0);
            HashSet<Integer> hashSet = new HashSet<>();
            for (Goods goods : goodsList) {
                Integer categoryId1 = goods.getCategoryId();
                hashSet.add(categoryId1);
            }
            Iterator<Integer> iterator = hashSet.iterator();
            categoryIdList = new ArrayList<>();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                categoryIdList.add(next);
            }
            categoryList = categoryService.selectAllCategoryById(categoryIdList);
            goodsList = goodsService.selectAllGoods(keyword, isNew, isHot, page, size, sort, order, categoryId);
            HashMap<String, Object> map = new HashMap<>();
            map.put("filterCategoryList",categoryList);
            map.put("goodsList",goodsList);
            map.put("count",goodsList.size());
            BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
            baseReqVo.setErrmsg("成功");
            baseReqVo.setErrno(0);
            baseReqVo.setData(map);
            return baseReqVo;
        }
        if(categoryId != 0 && sort == null){
            goodsList= goodsService.selectAllGoods(keyword, isNew, isHot, page, size, sort, order, categoryId);
            List<Category>  filterCategoryList=goodsService.queryAllCategory();
            BaseReqVo baseReqVo=new BaseReqVo();
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("success");
            Map map =new HashMap();
            map.put("goodsList",goodsList);
            map.put("filterCategoryList",filterCategoryList);
            map.put("count",goodsList.size());
            baseReqVo.setData(map);
            return baseReqVo;
        }
        return null;
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
        //调用工具类 获取UserId
        Integer userId = UserIdUtils.getCurrentUserId();
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
