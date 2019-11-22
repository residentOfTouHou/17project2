/**
 *
 */
package com.cskaoyan.wxmall.controller;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.bean.jsonbean.LoginVo;
import com.cskaoyan.mall.bean.jsonbean.reqVo.UserInfoVo;
import com.cskaoyan.mall.component.AliyunComponent;
import com.cskaoyan.mall.service.UserService;
import com.cskaoyan.mall.shiro.CustomToken;
import com.cskaoyan.wxmall.bean.WxLoginReqVo;
import com.cskaoyan.wxmall.utils.WxLoginUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/wx/auth")
@RestController
public class AuthWxController {

    @Autowired
    UserService userService;

    @Autowired
    AliyunComponent aliyunComponent;

    @RequestMapping("login_by_weixin")
    public BaseReqVo loginByWx(@RequestBody WxLoginReqVo wxLoginReqVo,HttpServletRequest request){
        BaseReqVo baseReqVo = new BaseReqVo();
        HashMap<String, Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> wxUserOpenid = WxLoginUtils.getWxUserOpenid(wxLoginReqVo.getCode(), "wx888079b67834368a", "c3d28500eb06fb1a24c47aeabbf97313");
        String openid = (String) wxUserOpenid.get("openid");
        User user = userService.getUSerByOpenId(openid);
        if(user != null){//登录
            CustomToken token = new CustomToken(user.getUsername(), user.getPassword(), "wx");
            try {
                subject.login(token);
            } catch (AuthenticationException e) {
                baseReqVo.setErrmsg("failed");
                baseReqVo.setErrno(-1);
                return baseReqVo;
            }
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
        //注册
        User userRegist = new User();
        userRegist.setWeixinOpenid(openid);
        userRegist.setLastLoginTime(new Date());
        userRegist.setLastLoginIp(request.getRemoteAddr());
        userRegist.setNickname(wxLoginReqVo.getUserInfo().getNickName());
        String password = UUID.randomUUID().toString();
        userRegist.setUsername(wxLoginReqVo.getUserInfo().getNickName());
        userRegist.setPassword(password);
        userRegist.setAddTime(new Date());
        userRegist.setAvatar(wxLoginReqVo.getUserInfo().getAvatarUrl());
        int insert = userService.insertUser(userRegist);
        if(insert == 1){
            CustomToken token = new CustomToken(userRegist.getUsername(), userRegist.getPassword(), "wx");
            SecurityUtils.getSubject().login(token);
            Session session = subject.getSession();
            map.put("userInfo",new UserInfoVo(userRegist.getNickname(),userRegist.getAvatar()));
            map.put("tokenExpire",session.getLastAccessTime());
            map.put("token", session.getId());
            baseReqVo.setData(map);
            baseReqVo.setErrmsg("成功");
            baseReqVo.setErrno(0);
            return baseReqVo;
        }
        baseReqVo.setErrmsg("登陆失败");
        baseReqVo.setErrno(-1);
        return baseReqVo;

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
            baseReqVo.setErrno(-1);
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
    public BaseReqVo register(@RequestBody UserInfoVo userInfoVo,HttpServletRequest request) {
        //需要设置 add time 以及 updatetime
        //判断用户名是否存在
        BaseReqVo baseReqVo = new BaseReqVo();
        User userByUsername = userService.getUserByUsername(userInfoVo.getUsername());
        if(userByUsername != null){
            baseReqVo.setErrmsg("当前用户名已存在");
            baseReqVo.setErrno(-1);
            return  baseReqVo;
        }
        String realCode = (String) request.getServletContext().getAttribute(userInfoVo.getMobile());
        String code = userInfoVo.getCode();
        if(code != null && code.equals(realCode)){
            User user = new User();
            user.setUsername(userInfoVo.getUsername());
            user.setPassword(userInfoVo.getPassword());
//            user.setGender((byte)0);
//            user.setBirthday(new Date());
//            user.setLastLoginTime(new Date());
//            user.setLastLoginIp("");
//            user.setUserLevel((byte)0);
            user.setNickname(user.getUsername());
            user.setMobile(userInfoVo.getMobile());
           // user.setAvatar("");
            //user.setWeixinOpenid();
            user.setStatus((byte)0);
            user.setAddTime(new Date());
            user.setUpdateTime(new Date());
            user.setDeleted(false);
            int insert = userService.insertUser(user);
            //注册成功转发login
            if(insert == 1){
                CustomToken token = new CustomToken(userInfoVo.getUsername(), userInfoVo.getPassword(), "wx");

                try {
                    Subject subject = SecurityUtils.getSubject();
                    subject.login(token);
                    HashMap<String, Object> map = new HashMap<>();
                    userInfoVo.setNickName(userInfoVo.getUsername());
                    Session session = subject.getSession();
                    map.put("userInfo",new UserInfoVo(user.getNickname(),user.getAvatar()));
                    map.put("tokenExpire",session.getLastAccessTime());
                    map.put("token", session.getId());
                    baseReqVo.setData(map);
                    baseReqVo.setErrmsg("成功");
                    baseReqVo.setErrno(0);
                    return baseReqVo;
                } catch (AuthenticationException e) {
                    baseReqVo.setErrmsg("failed");
                    baseReqVo.setErrno(-1);
                    return baseReqVo;
                }
            }
        }
        baseReqVo.setErrmsg("验证码错误");
        baseReqVo.setErrno(-1);
        return baseReqVo;
    }

    @RequestMapping("regCaptcha")
    public BaseReqVo message(@RequestBody Map map, HttpServletRequest request) {
        String mobile = (String) map.get("mobile");
        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++)
        {
            result+=random.nextInt(10);
        }
        System.out.println(result);
        IAcsClient client = aliyunComponent.getiacsClient();
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setMethod(MethodType.POST);
        commonRequest.setDomain("dysmsapi.aliyuncs.com");
        commonRequest.setVersion("2017-05-25");
        commonRequest.setAction("SendSms");
        commonRequest.putQueryParameter("RegionId",aliyunComponent.getSms().getRegionId());
        commonRequest.putQueryParameter("PhoneNumbers",mobile);
        commonRequest.putQueryParameter("SignName", "王道训练营");
        commonRequest.putQueryParameter("TemplateCode","SMS_173765187");
        commonRequest.putQueryParameter("TemplateParam","{\"code\": \"" + result+ "\"}");
        try {
            CommonResponse response = client.getCommonResponse(commonRequest);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        request.getServletContext().setAttribute(mobile,result);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @RequestMapping("reset")
    public BaseReqVo reset(@RequestBody Map map,HttpServletRequest request){
        BaseReqVo baseReqVo = new BaseReqVo();
        String code = (String) map.get("code");
        String mobile = (String) map.get("mobile");
        String password = (String) map.get("password");
        String realCode = (String) request.getServletContext().getAttribute(mobile);
        if(code.equals(realCode)){
            int reset = userService.updatePasswordByMobile(mobile,password);
            if(reset == 1){
                baseReqVo.setErrno(0);
                baseReqVo.setErrmsg("修改成功");
            }else {
                baseReqVo.setErrmsg("异常");
                baseReqVo.setErrno(-1);
            }
            return baseReqVo;
        }
        baseReqVo.setErrno(-1);
        baseReqVo.setErrmsg("验证码错误");
        return baseReqVo;
    }
}
