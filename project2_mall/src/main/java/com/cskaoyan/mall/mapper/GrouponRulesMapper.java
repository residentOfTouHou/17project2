package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.generator.GrouponRules;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GrouponRulesMapper {
    List<GrouponRules> getGrouponRules(@Param("goodsId") Integer goodsId,
                                       @Param("sort") String sort,
                                       @Param("order") String order);

    void updateGrouponRules(@Param("grouponRules") GrouponRules grouponRules);

    void deleteGrouponRules(int id);
}
