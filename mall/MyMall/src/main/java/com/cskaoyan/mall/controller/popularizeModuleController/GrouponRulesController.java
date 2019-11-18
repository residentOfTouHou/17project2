package com.cskaoyan.mall.controller.popularizeModuleController;


import com.cskaoyan.mall.bean.generator.popularizeModule.GrouponRules;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;

import com.cskaoyan.mall.bean.jsonbean.popularizeModuleJsonBean.GrouponRulesJson;
import com.cskaoyan.mall.service.popularizeModuleService.GrouponRulesService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/groupon")
public class GrouponRulesController {

    @Autowired
    GrouponRulesService grouponRulesService;

    @RequestMapping("list")
    public BaseReqVo getGrouponRules(Integer page, Integer limit, Integer goodsId, String sort, String order){
        List<GrouponRules> grouponRules = grouponRulesService.getGrouponRules(page,limit,goodsId,sort,order);
        PageInfo<GrouponRules> adPageInfo = new PageInfo<>(grouponRules);
        long total = adPageInfo.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("items",grouponRules);
        map.put("total",total);
        return new BaseReqVo(map,"成功",0);
    }

    @RequestMapping("update")
    public BaseReqVo updateGrouponRules(@RequestBody GrouponRules grouponRules){
        grouponRulesService.updateGrouponRules(grouponRules);
        return new BaseReqVo("成功",0);
    }

    @RequestMapping("delete")
    public BaseReqVo deleteGrouponRules(@RequestBody GrouponRules grouponRules){
        grouponRulesService.deleteGrouponRules(grouponRules);
        return new BaseReqVo("成功",0);
    }

    @RequestMapping("create")
    public BaseReqVo createGrouponRules(@RequestBody GrouponRulesJson grouponRulesJson){
        String goodsid = grouponRulesJson.getGoodsId();
        Integer goodsId = Integer.parseInt(goodsid);
        Integer queryGoodsId = grouponRulesService.queryGoodsId(goodsId);
        if(queryGoodsId == null){
            return new BaseReqVo("商品不存在,请重新输入商品ID",402);
        }
        GrouponRules insertedGrouponRules = grouponRulesService.createGrouponRule(grouponRulesJson);
        return new BaseReqVo(insertedGrouponRules,"成功“”",0);
    }
}
