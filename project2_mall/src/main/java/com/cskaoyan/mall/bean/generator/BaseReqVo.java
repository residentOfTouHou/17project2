package com.cskaoyan.mall.bean.generator;

import lombok.Data;

@Data
public class BaseReqVo<T> {
    T data;
    String errmsg;
    int errno;

    public BaseReqVo() {
    }

    public BaseReqVo(T data, String errmsg, int errno) {
        this.data = data;
        this.errmsg = errmsg;
        this.errno = errno;
    }

    public BaseReqVo(String errmsg, int errno) {
        this.errmsg = errmsg;
        this.errno = errno;
    }
}
