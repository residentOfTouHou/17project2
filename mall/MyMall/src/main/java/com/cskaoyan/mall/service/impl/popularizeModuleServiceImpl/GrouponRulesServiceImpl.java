package com.cskaoyan.mall.service.impl.popularizeModuleServiceImpl;


import com.cskaoyan.mall.bean.generator.GoodsAlter;
import com.cskaoyan.mall.bean.generator.popularizeModule.Groupon;
import com.cskaoyan.mall.bean.generator.popularizeModule.GrouponRules;
import com.cskaoyan.mall.bean.generator.popularizeModule.ListRecord;
import com.cskaoyan.mall.bean.generator.popularizeModule.SubGroupons;
import com.cskaoyan.mall.bean.jsonbean.popularizeModuleJsonBean.GrouponRulesJson;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.popularizeModuleMapper.GrouponRulesMapper;
import com.cskaoyan.mall.service.popularizeModuleService.GrouponRulesService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class GrouponRulesServiceImpl implements GrouponRulesService {

    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    @Autowired
    GoodsMapper goodsMapper;

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

    @Override
    public Integer queryGoodsId(Integer goodsId) {
        return grouponRulesMapper.queryGoodsId(goodsId);
    }


    @Override
    public GrouponRules createGrouponRule(GrouponRulesJson grouponRulesJson) {
        String discountS = grouponRulesJson.getDiscount();
        BigDecimal discount = new BigDecimal(discountS);
        String discountMember = grouponRulesJson.getDiscountMember();
        int dicMember = Integer.parseInt(discountMember);
        String expireTime = grouponRulesJson.getExpireTime();
        String goodsIdJson = grouponRulesJson.getGoodsId();
        int goodsId = Integer.parseInt(goodsIdJson);
        GoodsAlter goods = goodsMapper.queryGoodsById(goodsId);
        int flag = grouponRulesMapper.createGrouponRule(discount,dicMember,expireTime,grouponRulesJson,goods);
        int id = grouponRulesJson.getId();
        if(flag == 1){
            return grouponRulesMapper.queryGrouponRulesById(id);
        }
        return null;
    }

    @Override
    public List<ListRecord> queryListRecords(Integer page, Integer limit, String sort, String order, Integer goodsId) {
        PageHelper.startPage(page,limit);
        List<GrouponRules> grouponRules = grouponRulesMapper.queryGrouponRulesByGoodsId(goodsId);
        List<ListRecord> listRecords = new ArrayList<>();
        for (GrouponRules grouponRule : grouponRules) {
            int ruleId = grouponRule.getId();
            int goodsid = grouponRule.getGoodsId();
            GoodsAlter goods = goodsMapper.queryGoodsById(goodsid);
            Groupon groupon = grouponRulesMapper.queryGrouponByRuleId(ruleId);
            List<SubGroupons> subGrouponsList = new ArrayList<>();
            int grouponId = groupon.getGrouponId();
            if(grouponId != 0){
                subGrouponsList = grouponRulesMapper.querySubGroupons(grouponId);
            }
            ListRecord listRecord = new ListRecord(goods,groupon,grouponRule,subGrouponsList);
            listRecords.add(listRecord);
        }
        return listRecords;
    }
}
