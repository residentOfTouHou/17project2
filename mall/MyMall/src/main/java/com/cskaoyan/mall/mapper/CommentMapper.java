package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.generator.Comment;
import com.cskaoyan.mall.bean.generator.CommentAlter;
import com.cskaoyan.mall.bean.generator.CommentExample;
import com.cskaoyan.mall.bean.jsonbean.CommentData;
import com.cskaoyan.mall.bean.jsonbean.CommentQueryParameters;
import com.cskaoyan.wxmall.bean.WXComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    long countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<CommentAlter> queryCommentAndSort(CommentQueryParameters commentQueryParameters);

    CommentAlter queryCommentById(int commentId);

    int updateCommentById(CommentData commentData);


    List<CommentAlter> findCommentByCondition(@Param("type") Byte type, @Param("valueId") Integer valueId);


    List<Comment> selectCommentsByGoodsIdAndType(Integer id);


