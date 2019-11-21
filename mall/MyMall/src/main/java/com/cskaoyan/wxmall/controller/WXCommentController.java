package com.cskaoyan.wxmall.controller;


import com.cskaoyan.mall.bean.generator.CommentAlter;
import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.service.CommentService;
import com.cskaoyan.wxmall.bean.BaseReqVo;
import com.cskaoyan.wxmall.bean.CommentJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/comment/")
public class WXCommentController {
    @Autowired
    private CommentService commentService;


    @RequestMapping("/list")
    public CommentJson list(Integer valueId, Byte type, Integer page, Integer size) {
        List<Map<String, Object>> commentByCondition = commentService.findCommentByCondition(valueId, type, page, size);

        CommentJson commentJson = new CommentJson();

        CommentJson.DataBeanX dataBeanX = new CommentJson.DataBeanX();


//        commentJson.setErrmsg("成功");


        List<CommentJson.DataBeanX.DataBean> dataBeans = new ArrayList<>();
        for (Map<String, Object> map : commentByCondition) {
            CommentJson.DataBeanX.DataBean dataBean = new CommentJson.DataBeanX.DataBean();
            CommentAlter comment = (CommentAlter) map.get("comment");
            CommentJson.DataBeanX.DataBean.UserInfoBean userInfoBean = new CommentJson.DataBeanX.DataBean.UserInfoBean();
            User user = (User) map.get("user");
            userInfoBean.setAvatarUrl(user.getAvatar());
            userInfoBean.setNickName(user.getNickname());
            dataBean.setAddTime(comment.getAddTime());
            dataBean.setContent(comment.getContent());
            String[] picUrls = comment.getPicUrls();
            System.out.println(picUrls);
            dataBean.setPicList(picUrls);
            dataBean.setUserInfo(userInfoBean);
            dataBeans.add(dataBean);
        }
        dataBeanX.setCount(commentByCondition.size());
        dataBeanX.setCurrentPage(1);
        dataBeanX.setData(dataBeans);

        commentJson.setErrmsg("成功");
        commentJson.setData(dataBeanX);
        commentJson.setErrno(0);
        return commentJson;

    }

    @RequestMapping("/count")
    public BaseReqVo count(Integer valueId, Byte type, Integer page, Integer size) {
        BaseReqVo baseReqVo = new BaseReqVo();
        List<Map<String, Object>> commentByCondition = commentService.findCommentByCondition(valueId, type, page, size);
        Integer total =commentByCondition.size();
        Integer hasPic = 0;
        for (Map<String, Object> map : commentByCondition) {
            CommentAlter comment = (CommentAlter) map.get("comment");
            Boolean hasPicture = comment.getHasPicture();
            if (hasPicture){
                hasPic++;
            }
        }
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        Map<String,Object> map = new HashMap<>();
        map.put("allCount", total);
        map.put("hasPicCount", hasPic);
        baseReqVo.setData(map);
        return baseReqVo;
    }
}
