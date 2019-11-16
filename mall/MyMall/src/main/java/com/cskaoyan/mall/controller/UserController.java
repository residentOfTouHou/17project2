package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.bean.jsonbean.AllUserData;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;
import com.cskaoyan.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public BaseReqVo list(PageSplit pageSplit) {
        BaseReqVo baseReqVo = new BaseReqVo();
        Map<String, Object> allUser = userService.findAllUser(pageSplit);
        Long total = (Long) allUser.get("total");
        List<User> items = (List<User>) allUser.get("users");
        AllUserData data = new AllUserData();
        data.setTotal(total);
        data.setItems(items);

        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(data);
        return baseReqVo;
    }
}
