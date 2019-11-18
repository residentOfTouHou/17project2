package com.cskaoyan.mall.service.popularizeModuleService;

import com.cskaoyan.mall.bean.generator.Goods;
import com.cskaoyan.mall.bean.generator.popularizeModule.GrouponRules;
import com.cskaoyan.mall.bean.generator.popularizeModule.ListRecord;
import com.cskaoyan.mall.bean.jsonbean.popularizeModuleJsonBean.GrouponRulesJson;

import java.util.List;
import java.util.Map;

public interface GrouponRulesService {
    List<GrouponRules> getGrouponRules(Integer page, Integer limit, Integer goodsId, String sort, String order);

    void updateGrouponRules(GrouponRules grouponRules);

    void deleteGrouponRules(GrouponRules grouponRules);

    Integer queryGoodsId(Integer goodsId);

    GrouponRules createGrouponRule(GrouponRulesJson grouponRulesJson);

    List<Map<String,Object>> queryListRecords(Integer page, Integer limit, String sort, String order, Integer goodsId);
}
