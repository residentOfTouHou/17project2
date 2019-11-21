/**
 *
 */
package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/wx/user")
public class UserWxController {
    @Autowired
    UserService userService;

    /**
     * 个人页查询订单状态
     *
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"order": {
     * 			"unrecv": 0,
     * 			"uncomment": 1,
     * 			"unpaid": 28,
     * 			"unship": 0
     *      }
     *  },
     * 	"errmsg": "成功"
     * }
     * @return
     */
    @RequestMapping("index")
    public BaseReqVo indexOrder(){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        Map<String,Object> result = userService.indexOrder();
        baseReqVo.setErrno(0);
        baseReqVo.setData(result);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
