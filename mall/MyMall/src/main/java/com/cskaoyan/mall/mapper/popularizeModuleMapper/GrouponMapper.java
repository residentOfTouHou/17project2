package com.cskaoyan.mall.mapper.popularizeModuleMapper;


import com.cskaoyan.mall.bean.generator.popularizeModule.Groupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GrouponMapper {
    List<Groupon> getGroupons(@Param("goodsId") Integer goodsId,
                              @Param("sort") String sort,
                              @Param("order") String order);
}
