package com.cskaoyan.mall.bean.jsonbean.reqVo;
import lombok.Data;

@Data
public class BaseReqVo<T> {
    T data;
    String errmsg;
    int errno;
}
