package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.mapper.FootprintMapper;
import com.cskaoyan.mall.service.FootprintService;
import com.cskaoyan.mall.shiro.CustomSessionManager;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wx/footprint")
public class FootPrintWxController {

    @Autowired
    FootprintService footprintService;

    /**
     * 获取足迹列表
     * @return
     */
    @RequestMapping("list")
    public BaseReqVo GetFootPrint(Integer page,Integer size){
        //获取userId
        User primaryPrincipal = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Integer id = 1;
        if (primaryPrincipal != null){
             id = primaryPrincipal.getId();
        }
        Map<String,Object> map = footprintService.selectFootPrintBy(page,size,id);
        return BaseReqVo.ok(map);
    }
}
