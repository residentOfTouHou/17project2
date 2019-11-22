/**
 *
 */
package com.cskaoyan.mall.controller;


import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/timeout")
@RestController
public class TimeOutController {

    @RequestMapping("logout")
    public BaseReqVo timeout(){
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(501);
        baseReqVo.setErrmsg("请登录");
        return  baseReqVo;
    }
}
