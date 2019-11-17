package com.cskaoyan.mall.service;

import java.util.List;
import java.util.Map;

/**
 * @author 杨盛
 * @date 2019/11/16 11:58
 */
public interface CategoryService {

    List<Map<String, Object>> queryByLevel();

    List<Integer> queryById(Integer categoryId);
}
