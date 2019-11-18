package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.generator.Category;
import com.cskaoyan.mall.bean.generator.CategoryExample;
import com.cskaoyan.mall.bean.jsonbean.GoodsChildren;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.cskaoyan.mall.mapper.CategoryMapper;
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
}
