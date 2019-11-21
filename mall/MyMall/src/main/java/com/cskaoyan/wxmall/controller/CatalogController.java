/**
 *
 */
package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.generator.Category;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.CategoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/catalog")
public class CatalogController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("current")
    public BaseReqVo current(@Param("id") int id){
        List<Category> categoryByPid = categoryService.getCategoryByPid(id);
        Category category = categoryService.getCategoryById(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("currentSubCategory",categoryByPid);
        map.put("currentCategory",category);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(map);
        return baseReqVo;
    }

    @RequestMapping("index")
    public BaseReqVo index(){
        HashMap<String, Object> map = new HashMap<>();
        List<Category> categoryList = categoryService.queryCategoryLevelOne();
        map.put("categoryList",categoryList);
        map.put("currentCategory",categoryList.get(0));
        List<Category> currentSubCategory = categoryService.getCategoryByPid(categoryList.get(0).getId());
        map.put("currentSubCategory",currentSubCategory);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(map);
        return baseReqVo;
    }
}
