package com.cskaoyan.mall.bean.jsonbean;

import lombok.Data;

/**
 * @author 杨盛
 * @date 2019/11/17 22:57
 */

@Data
public class CommentQueryParameters {

    Integer page;

    Integer limit;

    String sort;

    String order;

    Integer userId;

    Integer valueId;
}
