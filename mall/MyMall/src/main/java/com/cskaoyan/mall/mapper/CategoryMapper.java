package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.generator.Category;
import com.cskaoyan.mall.bean.generator.CategoryExample;

import java.util.ArrayList;
import java.util.List;

import com.cskaoyan.mall.bean.jsonbean.CategorySegment;
import com.cskaoyan.mall.bean.jsonbean.GoodsChildren;
import org.apache.ibatis.annotations.Param;

public interface CategoryMapper {
    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<CategorySegment> selectAllCategory();

    int getLastInsertId();

    List<GoodsChildren> queryChildrenByLevel(int id);

    List<Category> selectByPid(Integer pid);

    List<Category> selectAllCategoryById(@Param("categoryList") ArrayList<Integer> categoryIdList);
}

