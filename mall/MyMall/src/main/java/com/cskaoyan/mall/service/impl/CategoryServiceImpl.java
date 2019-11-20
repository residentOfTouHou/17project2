package com.cskaoyan.mall.service.impl;


import com.cskaoyan.mall.bean.generator.Category;
import com.cskaoyan.mall.bean.generator.CategoryExample;
import com.cskaoyan.mall.bean.jsonbean.GoodsChildren;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author 杨盛
 * @date 2019/11/16 11:58
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    BrandMapper brandMapper;

    @Override
    public List<Map<String, Object>> queryByLevel() {
        List<Map<String, Object>> categoryList = new ArrayList<>();
        CategoryExample example = new CategoryExample();
        example.createCriteria().andLevelEqualTo("L1");
        List<Category> categories = categoryMapper.selectByExample(example);
        for (Category category : categories) {
            Map<String, Object> categoryMap = new HashMap<>();
            categoryMap.put("value", category.getId());
            categoryMap.put("label", category.getName());
            //  查询子表
            List<GoodsChildren> goodsChildren = categoryMapper.queryChildrenByLevel(category.getId());
            categoryMap.put("children", goodsChildren);
            categoryList.add(categoryMap);
        }
        return categoryList;
    }

    @Override
    public List queryById(Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        List<Integer> categoryIdList = new ArrayList<>();
        //  L1 id
        categoryIdList.add(category.getPid());
        //  L2 id
        categoryIdList.add(categoryId);
        return categoryIdList;
    }

    @Override
    public HashMap<String, Object> queryGoodsCategory(Integer id) {
        HashMap<String,Object> map = new HashMap<>();
        //获取当前分类
        Category currentCategory = categoryMapper.selectByPrimaryKey(id);
        //判空
        if(currentCategory != null){
            //获取兄弟分类
            List<Category> brotherCategory = categoryMapper.selectByPid(currentCategory.getPid());
            //获取父分类
            Category parentCategory = categoryMapper.selectByPrimaryKey(currentCategory.getPid());
            //封装
            map.put("currentCategory",currentCategory);
            map.put("brotherCategory",brotherCategory);
            map.put("parentCategory",parentCategory);
        }
        return map;
    }
}
