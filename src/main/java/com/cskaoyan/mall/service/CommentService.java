package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.CommentAlter;
import com.cskaoyan.mall.bean.jsonbean.CommentData;
import com.cskaoyan.mall.bean.jsonbean.CommentQueryParameters;

import java.util.Map;

/**
 * @author 杨盛
 * @date 2019/11/17 22:21
 */
public interface CommentService {


    Map<String, Object> queryComment(CommentQueryParameters commentQueryParameters);

    int deleteCommentById(int id);

    CommentAlter queryCommentById(int commentId);

    int updateCommentById(CommentData commentData);
}
