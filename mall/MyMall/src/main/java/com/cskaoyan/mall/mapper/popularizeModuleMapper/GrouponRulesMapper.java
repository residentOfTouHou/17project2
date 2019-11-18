package com.cskaoyan.mall.mapper.popularizeModuleMapper;


import com.cskaoyan.mall.bean.generator.Goods;
import com.cskaoyan.mall.bean.generator.popularizeModule.GrouponRules;
import com.cskaoyan.mall.bean.jsonbean.popularizeModuleJsonBean.GrouponRulesJson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface GrouponRulesMapper {
    List<GrouponRules> getGrouponRules(@Param("goodsId") Integer goodsId,
                                       @Param("sort") String sort,
                                       @Param("order") String order);

    void updateGrouponRules(@Param("grouponRules") GrouponRules grouponRules);

    void deleteGrouponRules(int id);

    Integer queryGoodsId(Integer goodsId);

    int createGrouponRule(@Param("discount") BigDecimal discount, @Param("discountMember") int dicMember, @Param("expireTime") String expireTime, @Param("groupRulesJson") GrouponRulesJson grouponRulesJson);

    GrouponRules queryGrouponRulesById(int id);
}