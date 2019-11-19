package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.Reply;

/**
 * @author 杨盛
 * @date 2019/11/18 22:38
 */
public interface ReplyService {


    Reply queryByCommentId(int commentId);

    int insertByCommentId(Reply reply);
}
