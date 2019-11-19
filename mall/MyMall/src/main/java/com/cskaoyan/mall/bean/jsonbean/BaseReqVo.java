package com.cskaoyan.mall.bean.jsonbean;
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

    public static BaseReqVo ok(){
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    public static BaseReqVo ok(Object data){
        BaseReqVo baseReqVo = BaseReqVo.ok();
        baseReqVo.setData(data);
        return baseReqVo;
    }

    public static BaseReqVo fail(int errno){
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(errno);
        return baseReqVo;
    }

}
