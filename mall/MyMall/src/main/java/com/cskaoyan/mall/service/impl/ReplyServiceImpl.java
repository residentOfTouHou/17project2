package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.Reply;
import com.cskaoyan.mall.mapper.ReplyMapper;
import com.cskaoyan.mall.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 杨盛
 * @date 2019/11/18 22:39
 */

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    ReplyMapper replyMapper;

    @Override
    public Reply queryByCommentId(int commentId) {
        Reply reply = replyMapper.queryByCommentId(commentId);
        return reply;
    }

    @Override
    public int insertByCommentId(Reply reply) {
        int insert = replyMapper.insertByCommentId(reply);
        return insert;
    }
}
