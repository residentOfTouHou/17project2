package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.Order;
import com.cskaoyan.mall.bean.generator.OrderExample;
import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.bean.generator.UserExample;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;
import com.cskaoyan.mall.mapper.OrderMapper;
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

    @Autowired
    private OrderMapper orderMapper;

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

    /**
     * 获取订单状态
     * @return
     */
    @Override
    public Map<String, Object> indexOrder() {
        HashMap<String, Object> map = new HashMap<>();
        OrderExample unpaid = new OrderExample();
        unpaid.createCriteria().andOrderStatusEqualTo((short) 101).andDeletedEqualTo(false);
        List<Order> unpaidOrders = orderMapper.selectByExample(unpaid);
        if(unpaidOrders!=null){
            map.put("unpaid",unpaidOrders.size());
        }else{
            map.put("unpaid",0);
        }

        OrderExample unship = new OrderExample();
        unship.createCriteria().andOrderStatusEqualTo((short) 201).andDeletedEqualTo(false);
        List<Order> unshipOrders = orderMapper.selectByExample(unpaid);
        if(unshipOrders!=null){
            map.put("unship",unshipOrders.size());
        }else{
            map.put("unship",0);
        }

        OrderExample unrecv = new OrderExample();
        unship.createCriteria().andOrderStatusEqualTo((short) 301).andDeletedEqualTo(false);
        List<Order> unrecvOrders = orderMapper.selectByExample(unpaid);
        if(unrecvOrders!=null){
            map.put("unrecv",unrecvOrders.size());
        }else{
            map.put("unrecv",0);
        }

        OrderExample uncomment = new OrderExample();
        unship.createCriteria().andOrderStatusEqualTo((short) 401).andDeletedEqualTo(false);
        List<Order> uncommentOrders = orderMapper.selectByExample(unpaid);
        if(uncommentOrders!=null){
            map.put("uncomment",uncommentOrders.size());
        }else{
            map.put("uncomment",0);
        }

        return map;
    }
}

