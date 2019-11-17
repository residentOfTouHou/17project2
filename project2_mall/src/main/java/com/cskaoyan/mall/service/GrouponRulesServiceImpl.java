package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.Groupon;
import com.cskaoyan.mall.bean.generator.GrouponRules;
import com.cskaoyan.mall.mapper.GrouponMapper;
import com.cskaoyan.mall.mapper.GrouponRulesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrouponRulesServiceImpl implements GrouponRulesService {

    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    @Override
    public List<GrouponRules> getGrouponRules(Integer page, Integer limit, Integer goodsId, String sort, String order) {
        PageHelper.startPage(page,limit);

        List<GrouponRules> grouponRules = grouponRulesMapper.getGrouponRules(goodsId,sort,order);
        return grouponRules;
    }

    @Override
    public void updateGrouponRules(GrouponRules grouponRules) {
        grouponRulesMapper.updateGrouponRules(grouponRules);
    }

    @Override
    public void deleteGrouponRules(GrouponRules grouponRules) {
        int id = grouponRules.getId();
        grouponRulesMapper.deleteGrouponRules(id);
    }
}
