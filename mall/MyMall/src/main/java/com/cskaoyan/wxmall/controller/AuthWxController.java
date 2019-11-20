/**
 *
 */
package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.bean.jsonbean.LoginVo;
import com.cskaoyan.mall.bean.jsonbean.reqVo.UserInfoVo;
import com.cskaoyan.mall.service.UserService;
import com.cskaoyan.mall.shiro.CustomToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/wx/auth")
@RestController
public class AuthWxController {

    @Autowired
    UserService userService;


    @RequestMapping("login_by_weixin")
    public BaseReqVo loginByWx(){
        return null;
    }

    @RequestMapping("login")
    public BaseReqVo login(@RequestBody LoginVo loginVo, HttpServletRequest request){
        BaseReqVo baseReqVo = new BaseReqVo();
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        CustomToken token = new CustomToken(username, password, "wx");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            baseReqVo.setErrmsg("failed");
            baseReqVo.setErrno(500);
            return baseReqVo;
        }
        HashMap<String, Object> map = new HashMap<>();
        User user = (User) subject.getPrincipals().getPrimaryPrincipal();
        user.setLastLoginTime(new Date());
        user.setLastLoginIp(request.getRemoteAddr());
        userService.updateUser(user);
        Session session = subject.getSession();
        map.put("userInfo",new UserInfoVo(user.getNickname(),user.getAvatar()));
        map.put("tokenExpire",session.getLastAccessTime());
        map.put("token", session.getId());
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @RequestMapping("logout")
    public BaseReqVo logout() {
        BaseReqVo baseReqVo = new BaseReqVo();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @RequestMapping("register")
    public BaseReqVo register(@RequestBody UserInfoVo userInfoVo) {
        //需要设置 add time 以及 updatetime
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @RequestMapping("regCaptcha")
    public BaseReqVo message(@RequestBody Map map) {
        String mobile = (String) map.get("mobile");

        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
}
