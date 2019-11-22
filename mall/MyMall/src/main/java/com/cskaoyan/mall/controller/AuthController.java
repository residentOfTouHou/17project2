package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.bean.jsonbean.InfoData;
import com.cskaoyan.mall.bean.jsonbean.LoginVo;
import com.cskaoyan.mall.shiro.CustomToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin/auth")
public class AuthController {

    @RequestMapping("/login")
    public BaseReqVo login(@RequestBody LoginVo loginVo) {
        BaseReqVo baseReqVo = new BaseReqVo();
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        CustomToken token = new CustomToken(username, password, "admin");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            baseReqVo.setErrmsg("failed");
            baseReqVo.setErrno(501);
            return baseReqVo;
        }

        baseReqVo.setData(subject.getSession().getId());
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @RequestMapping("/logout")
    public BaseReqVo logout() {
        BaseReqVo baseReqVo = new BaseReqVo();

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(501);
        return baseReqVo;
    }
    @RequestMapping("/info")
    public BaseReqVo info(String token){
        BaseReqVo baseReqVo = new BaseReqVo();
        InfoData data = new InfoData();
        data.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.setName("songge");
        ArrayList<String> perms = new ArrayList<>();
        perms.add("*");
        data.setPerms(perms);
        ArrayList<String> roles = new ArrayList<>();
        roles.add("超级管理员");
        data.setRoles(roles);

        baseReqVo.setData(data);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);


        return baseReqVo;
    }


}
