package com.cskaoyan.mall.bean.jsonbean;
import lombok.Data;

@Data
public class BaseReqVo<T> {
    T data;
    String errmsg;
    int errno;
}
