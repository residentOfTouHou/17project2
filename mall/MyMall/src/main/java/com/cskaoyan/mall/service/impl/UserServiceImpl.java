package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.bean.generator.UserExample;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String,Object> findAllUser(PageSplit pageSplit) {
//分页操作
        PageHelper.startPage(pageSplit.getPage(),pageSplit.getLimit());

        List<User> users = userMapper.selectAll();
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        long total = userPageInfo.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("total", total);
        map.put("users", users);
        return map;
    }
}