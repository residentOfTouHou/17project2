package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.generator.BaseReqVo;
import com.cskaoyan.mall.bean.jsonbean.InfoData;
import com.cskaoyan.mall.bean.jsonbean.LoginVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin/auth")
public class AuthController {

    @RequestMapping("login")
    public BaseReqVo login(@RequestBody LoginVo loginVo) {

        return new BaseReqVo("4b7d719e-53b7-4019-9677-6309b2445b45","成功",0);
    }
    @RequestMapping("info")
    public BaseReqVo info(String token){
        InfoData data = new InfoData();
        data.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.setName("skrskr");
        ArrayList<String> perms = new ArrayList<>();
        perms.add("*");
        data.setPerms(perms);
        ArrayList<String> roles = new ArrayList<>();
        roles.add("超级管理员");
        data.setRoles(roles);

        return new BaseReqVo(data,"成功",0);
    }
}
