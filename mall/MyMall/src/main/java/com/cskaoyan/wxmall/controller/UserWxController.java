/**
 *
 */
package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/user")
public class UserWxController {
    @Autowired
    UserService userService;

    @RequestMapping("index")
    public BaseReqVo index(){
        return null;
    }
}
