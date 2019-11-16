package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String,Object> findAllUser(PageSplit pageSplit);
}
