package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;

import java.util.Map;

public interface UserService {
    Map<String,Object> findAllUser(PageSplit pageSplit);

    Map<String,Object> findUserByCondition(PageSplit pageSplit);

    User getUserByUsername(String username);

    int updateUser(User user);

    int insertUser(User user);

    int updatePasswordByMobile(String mobile, String password);

    int getUserNumber();
}
