package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.wxmall.bean.GrouponWxBean;
import com.cskaoyan.wxmall.service.GrouponWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/groupon")
public class GrouponWxController {

    @Autowired
    GrouponWxService grouponWxService;

    @RequestMapping("list")
    public BaseReqVo getGrouponList(Integer page,Integer size){
        List<GrouponWxBean> grouponWxBeanList = grouponWxService.queryWxGrouponsList(page,size);
        Map<String,Object> map = new HashMap<>();
        map.put("data",grouponWxBeanList);
        return BaseReqVo.ok(map);
    }
}
