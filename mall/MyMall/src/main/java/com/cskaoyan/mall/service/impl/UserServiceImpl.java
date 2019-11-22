package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.bean.generator.UserExample;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.UserService;
import com.cskaoyan.mall.utils.StringUtil;
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
    public Map<String, Object> findAllUser(PageSplit pageSplit) {
//分页操作
        PageHelper.startPage(pageSplit.getPage(), pageSplit.getLimit());

        List<User> users = userMapper.selectAll();
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        long total = userPageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("users", users);
        return map;
    }

    @Override
    public Map<String, Object> findUserByCondition(PageSplit pageSplit) {
        PageHelper.startPage(pageSplit.getPage(), pageSplit.getLimit());
        String username = pageSplit.getUsername();
        String mobile = pageSplit.getMobile();
        UserExample userExample = new UserExample();
//        升序降序
        userExample.setOrderByClause(pageSplit.getSort() + " " + pageSplit.getOrder());
        UserExample.Criteria criteria = userExample.createCriteria();
        if (!StringUtil.isBlank(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        if (!com.github.pagehelper.util.StringUtil.isEmpty(mobile)) {
            criteria.andMobileEqualTo(mobile);
        }
        List<User> users = userMapper.selectByExample(userExample);

        PageInfo<User> userPageInfo = new PageInfo<>(users);
        long total = userPageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("users", users);
        return map;
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int updatePasswordByMobile(String mobile, String password) {

        return userMapper.updatePasswordByMobile(mobile, password);
    }

    @Override
    public int getUserNumber() {
        return userMapper.selectAll().size();
    }

    @Override
    public User getUSerByOpenId(String openid) {

        return userMapper.selectByOpenid(openid);
    }
}

