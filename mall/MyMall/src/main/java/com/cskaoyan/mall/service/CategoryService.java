package com.cskaoyan.mall.service;


import java.util.ArrayList;
import java.util.HashMap;
import com.cskaoyan.mall.bean.generator.Category;
import com.cskaoyan.mall.bean.jsonbean.CategorySegment;

import java.util.List;
import java.util.Map;

/**
 * @author 杨盛
 * @date 2019/11/16 11:58
 */
public interface CategoryService {

    List<Map<String, Object>> queryByLevel();

    List<Integer> queryById(Integer categoryId);

    HashMap<String, Object> queryGoodsCategory(Integer id);

    List<Category> queryCategoryLevelOne();

    List<Category> getCategoryByPid(Integer id);

    Category getCategoryById(int id);

    List<Category> selectAllCategoryById(ArrayList<Integer> categoryIdList);
}
