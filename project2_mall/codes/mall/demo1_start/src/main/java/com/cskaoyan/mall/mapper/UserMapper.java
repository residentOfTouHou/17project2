package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {


    List<User> queryUsers();

    int insertUser(@Param("user")User user);
}
