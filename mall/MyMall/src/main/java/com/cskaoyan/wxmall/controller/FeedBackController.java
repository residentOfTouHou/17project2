/**
 *
 */
package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.generator.Feedback;
import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.FeedbackService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/wx/feedback")
public class FeedBackController {
    @Autowired
    FeedbackService feedbackService;

    @RequestMapping("submit")
    public BaseReqVo submit(@RequestBody Feedback feedback){
        BaseReqVo baseReqVo = new BaseReqVo();
        //不认证无法获取到该页面所以无需判空
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        User user = (User) principals.getPrimaryPrincipal();
        feedback.setAddTime(new Date());
        feedback.setUserId(user.getId());
        feedback.setUpdateTime(new Date());
        int insert = feedbackService.insert(feedback);
        if(insert == 1){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("success");
            return baseReqVo;
        }
        baseReqVo.setErrmsg("failed");
        baseReqVo.setErrno(-1);
        return baseReqVo;
    }
}
