package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.generator.GrouponRules;

import java.util.List;

public interface GrouponRulesService {
    List<GrouponRules> getGrouponRules(Integer page, Integer limit, Integer goodsId, String sort, String order);

    void updateGrouponRules(GrouponRules grouponRules);

    void deleteGrouponRules(GrouponRules grouponRules);
}
