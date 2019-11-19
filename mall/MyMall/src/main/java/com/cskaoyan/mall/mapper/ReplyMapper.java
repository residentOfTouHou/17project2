package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.generator.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 杨盛
 * @date 2019/11/18 22:45
 */

@Mapper
public interface ReplyMapper {

    Reply queryByCommentId(int commentId);

    int insertByCommentId(Reply reply);
}
