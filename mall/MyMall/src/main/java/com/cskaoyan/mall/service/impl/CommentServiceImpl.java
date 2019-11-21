package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.*;
import com.cskaoyan.mall.bean.jsonbean.CommentData;
import com.cskaoyan.mall.bean.jsonbean.CommentQueryParameters;
import com.cskaoyan.mall.mapper.CommentMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.CommentService;
import com.cskaoyan.wxmall.bean.WXComment;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.MapAccessor;
import org.springframework.stereotype.Service;

import java.lang.System;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 杨盛
 * @date 2019/11/17 22:22
 */

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> queryComment(CommentQueryParameters commentQueryParameters) {
        Map<String, Object> commentMap = new HashMap<>();
        PageHelper.startPage(commentQueryParameters.getPage(), commentQueryParameters.getLimit());
        List<CommentAlter> comments = commentMapper.queryCommentAndSort(commentQueryParameters);
        PageInfo<CommentAlter> commentPageInfo = new PageInfo<>(comments);
        long total = commentPageInfo.getTotal();
        commentMap.put("total", total);
        commentMap.put("items", comments);
        return commentMap;
    }

    @Override
    public int deleteCommentById(int id) {
        int delete = commentMapper.deleteByPrimaryKey(id);
        return delete;
    }

    @Override
    public CommentAlter queryCommentById(int commentId) {
        CommentAlter comment = commentMapper.queryCommentById(commentId);
        return comment;
    }

    @Override
    public int updateCommentById(CommentData commentData) {
        int update = commentMapper.updateCommentById(commentData);
        return update;
    }

    @Override
    public List<Map<String, Object>> findCommentByCondition(Integer valueId, Byte type, Integer page, Integer size) {
        List<Map<String,Object>> maps = new ArrayList<>();
        Map<String, Object> commentMap = new HashMap<>();
        PageHelper.startPage(page,size);

      /*  CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andTypeEqualTo(type).andValueIdEqualTo(valueId);
        List<WXComment> comments = commentMapper.selectByExample(commentExample);*/

        List<CommentAlter> comments = commentMapper.findCommentByCondition(type, valueId);
        PageInfo<Comment> commentPageInfo = new PageInfo<>();

        for (CommentAlter comment : comments) {
            Map<String,Object> map = new HashMap<>();
            Integer userId = comment.getUserId();
            String[] picUrls = comment.getPicUrls();
            for (String picUrl : picUrls) {
                System.out.println(picUrl);
            }
           /* UserExample userExample = new UserExample();
            UserExample.Criteria criteria1 = userExample.createCriteria();
            criteria1.andIdEqualTo(userId);*/
            User user = userMapper.selectByPrimaryKey(userId);
         /*   User user = null;
            if(!users.isEmpty()){
              user  = users.get(0);
            }*/
            map.put("comment", comment);
            map.put("user", user);
            maps.add(map);
        }




        return maps;
    }
}
