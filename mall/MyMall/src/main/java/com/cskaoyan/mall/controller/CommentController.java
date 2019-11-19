package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.generator.CommentAlter;
import com.cskaoyan.mall.bean.generator.Reply;
import com.cskaoyan.mall.bean.jsonbean.*;
import com.cskaoyan.mall.service.CommentService;
import com.cskaoyan.mall.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 杨盛
 * @date 2019/11/17 22:19
 */

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    ReplyService replyService;

    /**
     * 商品回复列表
     *
     * @param commentQueryParameters
     * @return
     */
    @RequestMapping("admin/comment/list")
    public BaseReqVo list(CommentQueryParameters commentQueryParameters) {
        Map<String, Object> commentMap = commentService.queryComment(commentQueryParameters);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setData(commentMap);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 商品评论回复
     *
     * @param reply
     * @return
     */
    @RequestMapping("admin/order/reply")
    public BaseReqVo reply(@RequestBody Reply reply) {
        int commentId = reply.getCommentId();
        Reply queryReply = replyService.queryByCommentId(commentId);
        if (queryReply != null && queryReply.getContent() != null) {
            BaseReqVo baseReqVo = new BaseReqVo();
            baseReqVo.setErrno(622);
            baseReqVo.setErrmsg("订单商品已回复");
            return baseReqVo;
        }
        replyService.insertByCommentId(reply);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 删除回复
     *
     * @param comment
     * @return
     */
    @RequestMapping("admin/comment/delete")
    public BaseReqVo delete(@RequestBody CommentAlter comment) {
        int id = comment.getId();
        commentService.deleteCommentById(id);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
