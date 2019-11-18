package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.CommentAlter;
import com.cskaoyan.mall.bean.jsonbean.CommentData;
import com.cskaoyan.mall.bean.jsonbean.CommentQueryParameters;
import com.cskaoyan.mall.mapper.CommentMapper;
import com.cskaoyan.mall.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
